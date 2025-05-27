package cat.itacademy.project.business_logic.reservation.application;

import cat.itacademy.project.business_logic.customer.domain.CustomerRepository;
import cat.itacademy.project.business_logic.puzzle.domain.PuzzleRepository;
import cat.itacademy.project.business_logic.reservation.domain.Reservation;
import cat.itacademy.project.business_logic.reservation.domain.ReservationRepository;
import cat.itacademy.project.business_logic.room.domain.RoomRepository;
import cat.itacademy.project.shared.domain.dtos.reservation.ReservationDTO;
import cat.itacademy.project.shared.domain.dtos.reservation.UpdateReservationDTO;
import cat.itacademy.project.shared.domain.exceptions.NotFoundException;
import cat.itacademy.project.shared.domain.exceptions.PuzzleWithoutRoomException;

import java.util.Optional;

public class UpdateReservationService {
    private final ReservationRepository repo;
    private final CustomerRepository customerRepo;
    private final PuzzleRepository puzzleRepo;
    private final RoomRepository roomRepo;

    public UpdateReservationService(ReservationRepository repo, CustomerRepository customerRepo, PuzzleRepository puzzleRepo, RoomRepository roomRepo) {
        this.repo = repo;
        this.customerRepo = customerRepo;
        this.puzzleRepo = puzzleRepo;
        this.roomRepo = roomRepo;
    }

    public Optional<Reservation> execute(UpdateReservationDTO request) {
        // 1. Validar el ID de la reserva a actualizar (siempre debe ser positivo)
        if (request.id() == null || request.id() <= 0) {
            throw new IllegalArgumentException("Reservation ID must be provided and positive for update.");
        }

        // 2. Buscar la reserva existente
        Optional<ReservationDTO> existingReservationOpt = repo.findById(request.id());

        if (existingReservationOpt.isEmpty()) {
            throw new NotFoundException("Reservation with ID " + request.id() + " not found for update.");
        }

        // 3. Convertir DTO a entidad de dominio para poder modificarla
        // ¡Importante!: Asegúrate de que Reservation.fromDatabase sea capaz de construir una entidad Reservation completa
        // con los datos del DTO, incluyendo el ID.
        Reservation existingReservation = Reservation.fromDatabase(existingReservationOpt.get());

        // 4. Aplicar actualizaciones condicionales

        // Actualizar Customer ID
        // Solo si se proporciona un nuevo Customer ID (no es null)
        if (request.id_customer() != null) {
            if (request.id_customer() <= 0) { // Ahora sí es seguro el <=0 porque ya sabemos que no es null
                throw new IllegalArgumentException("Customer ID must be positive if provided for update.");
            }
            // Verificar si el cliente existe
            customerRepo.findById(request.id_customer())
                    .orElseThrow(() -> new NotFoundException("Customer with ID " + request.id_customer() + " not found."));

            // Actualizar solo si el ID del cliente es diferente al actual
            if (!request.id_customer().equals(existingReservation.getCustomerId())) {
                existingReservation.setCustomerId(request.id_customer());
            }
        }

        // Actualizar Puzzle ID y Recalcular Precio Total
        // Solo si se proporciona un nuevo Puzzle ID (no es null)
        if (request.id_puzzle() != null) {
            if (request.id_puzzle() <= 0) { // Ahora sí es seguro el <=0 porque ya sabemos que no es null
                throw new IllegalArgumentException("Puzzle ID must be positive if provided for update.");
            }
            // Solo actualizamos si el ID del puzle es diferente al actual, para evitar recálculos innecesarios
            if (!request.id_puzzle().equals(existingReservation.getPuzzleId())) {
                existingReservation.setPuzzleId(request.id_puzzle());
                // *** AQUÍ VA LA INVOCACIÓN AL MÉTODO DE RECÁLCULO DEL PRECIO ***
                // Asumo que la lógica de calculateTotalPrice está en la entidad Reservation o se puede llamar desde aquí
                // utilizando los repositorios que el servicio ya tiene.
                try {
                    // LLAMADA AL MÉTODO QUE RECALCULA EL PRECIO
                    // Si tu Reservation tiene un método interno que ya puede hacer esto:
                    existingReservation.recalculateTotalPrice(puzzleRepo, roomRepo);
                    // O si es un método estático o auxiliar que toma los datos:
                    // double newPrice = Reservation.calculatePriceForPuzzle(existingReservation.getPuzzleId(), puzzleRepo, roomRepo);
                    // existingReservation.setTotalPrice(newPrice);
                } catch (NotFoundException e) {
                    throw new NotFoundException("Could not find puzzle or room details for new puzzle ID " + existingReservation.getPuzzleId() + ": " + e.getMessage());
                } catch (PuzzleWithoutRoomException e) {
                    throw new PuzzleWithoutRoomException("The new puzzle ID " + existingReservation.getPuzzleId() + " does not have an associated room, cannot calculate price: " + e.getMessage());
                }
            }
        }

        // Actualizar Completion Date
        if (request.completionDate() != null) {
            // Solo actualizamos si la fecha es diferente
            if (!request.completionDate().equals(existingReservation.getCompletionDate())) {
                existingReservation.setCompletionDate(request.completionDate());
            }
        }

        // 5. Realizar la actualización en la base de datos
        repo.update(existingReservation);

        return Optional.of(existingReservation);
    }
}

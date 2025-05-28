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

        if (request.id() == null) {
            throw new IllegalArgumentException("Reservation ID must be provided for update.");
        }
        if (request.id() <= 0) {
            throw new IllegalArgumentException("Reservation ID must be positive for update.");
        }

        Optional<ReservationDTO> existingReservationOpt = repo.findById(request.id());

        if (existingReservationOpt.isEmpty()) {
            throw new NotFoundException("Reservation with ID " + request.id() + " not found for update.");
        }

        Reservation existingReservation = Reservation.fromDatabase(existingReservationOpt.get());

        if (request.id_customer() != null) {
            if (request.id_customer() <= 0) {
                throw new IllegalArgumentException("Customer ID must be positive if provided for update.");
            }

            customerRepo.findById(request.id_customer())
                    .orElseThrow(() -> new NotFoundException("Customer with ID " + request.id_customer() + " not found."));

            if (!request.id_customer().equals(existingReservation.getCustomerId())) {
                existingReservation.setCustomerId(request.id_customer());
            }
        }

        if (request.id_puzzle() != null) {
            if (request.id_puzzle() <= 0) {
                throw new IllegalArgumentException("Puzzle ID must be positive if provided for update.");
            }

            if (!request.id_puzzle().equals(existingReservation.getPuzzleId())) {
                existingReservation.setPuzzleId(request.id_puzzle());
                try {
                    existingReservation.recalculateTotalPrice(puzzleRepo, roomRepo);
                } catch (NotFoundException e) {
                    throw new NotFoundException("Could not find puzzle or room details for new puzzle ID " + existingReservation.getPuzzleId() + ": " + e.getMessage());
                } catch (PuzzleWithoutRoomException e) {
                    throw new PuzzleWithoutRoomException("The new puzzle ID " + existingReservation.getPuzzleId() + " does not have an associated room, cannot calculate price: " + e.getMessage());
                }
            }
        }

        if (request.completionDate() != null) {
            if (!request.completionDate().equals(existingReservation.getCompletionDate())) {
                existingReservation.setCompletionDate(request.completionDate().atStartOfDay());
            }
        }
        repo.update(existingReservation);

        return Optional.of(existingReservation);
    }
}
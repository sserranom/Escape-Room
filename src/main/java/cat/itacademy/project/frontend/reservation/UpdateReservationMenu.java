package cat.itacademy.project.frontend.reservation;

import cat.itacademy.project.api.reservation.UpdateReservationController;
import cat.itacademy.project.business_logic.reservation.application.UpdateReservationService;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.frontend.shared.MenuScanner;
import cat.itacademy.project.shared.domain.dtos.reservation.ReservationDTO;
import cat.itacademy.project.shared.domain.dtos.reservation.UpdateReservationDTO;
import cat.itacademy.project.shared.domain.exceptions.DatabaseException;
import cat.itacademy.project.shared.domain.exceptions.NotFoundException;
import cat.itacademy.project.shared.domain.exceptions.PuzzleWithoutRoomException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class UpdateReservationMenu  extends MenuCommand<Void> {
    private final UpdateReservationController updateReservationController;
    private final UpdateReservationService reservationService;

    // Constructor que recibe las dependencias
    public UpdateReservationMenu(UpdateReservationController updateReservationController, UpdateReservationService reservationService) {
        this.updateReservationController = updateReservationController;
        this.reservationService = reservationService;
    }

    @Override
    public Optional<Void> execute() {
        info("\n--- Update Reservation ---"); // Usamos info()
        try {
            // getInfo ahora lanza excepciones si el ID es inválido o la reserva no existe
            UpdateReservationDTO dto = getInfo();

            // Si llegamos aquí, dto no es null y la reserva fue encontrada
            updateReservationController.execute(dto);
            info("Reservation with ID '" + dto.id() + "' updated successfully.");

        } catch (IllegalArgumentException e) {
            error("Input error: " + e.getMessage());
        } catch (NotFoundException e) { // Capturadas si la reserva no existe o el cliente/puzzle no se encuentra
            error("Update failed: " + e.getMessage());
        } catch (PuzzleWithoutRoomException e) { // Capturada si el Puzzle no tiene Room al recalcular precio
            error("Update failed: " + e.getMessage());
        } catch (DatabaseException e) {
            error("A database error occurred during update: " + e.getMessage());
        } catch (Exception e) { // Para capturar cualquier otra excepción inesperada
            error("An unexpected error occurred during update: " + e.getMessage());
            // e.printStackTrace(); // Considera quitar esto en producción
        }
        return Optional.empty();
    }

    private UpdateReservationDTO getInfo() {
        int reservationId;
        // Pedir el ID de la reserva a actualizar y validar
        try {
            reservationId = MenuScanner.readInt("Enter the Reservation ID to update: ");
            if (reservationId <= 0) {
                throw new IllegalArgumentException("Reservation ID must be a positive number.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid Reservation ID format. Please enter a number.");
        }

        // Buscar la reserva existente. Si no se encuentra, NotFoundException se propagará.
        ReservationDTO existingReservation = reservationService.findById(reservationId)
                .orElseThrow(() -> new NotFoundException("Reservation with ID " + reservationId + " not found."));

        // Mostrar detalles actuales usando info()
        info("\n--- Current Reservation Details ---");
        info("ID: " " + existingReservation.id());
                info("Customer ID: " + existingReservation.customer_id());
        info("Puzzle ID: " + existingReservation.puzzle_id());
        info("Completion Date: " + (existingReservation.completion_date() != null ? existingReservation.completion_date().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "Not set"));
        info("Total Price: " + String.format("%.2f", existingReservation.total_price()));
        info("-----------------------------------\n");
        info("Enter new values (leave empty to keep current value):");


        // --- Customer ID ---
        Integer newCustomerId = null;
        String customerIdInput = MenuScanner.readString("Enter new Customer ID (current: " + existingReservation.customer_id() + "): ");
        if (!customerIdInput.isEmpty()) {
            try {
                int parsedId = Integer.parseInt(customerIdInput);
                if (parsedId <= 0) {
                    throw new IllegalArgumentException("Customer ID must be a positive number.");
                }
                newCustomerId = parsedId;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid Customer ID format.");
            }
        }

        // --- Puzzle ID ---
        Integer newPuzzleId = null;
        String puzzleIdInput = MenuScanner.readString("Enter new Puzzle ID (current: " + existingReservation.puzzle_id() + "): ");
        if (!puzzleIdInput.isEmpty()) {
            try {
                int parsedId = Integer.parseInt(puzzleIdInput);
                if (parsedId <= 0) {
                    throw new IllegalArgumentException("Puzzle ID must be a positive number.");
                }
                newPuzzleId = parsedId;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid Puzzle ID format.");
            }
        }

        // --- Completion Date ---
        LocalDate newCompletionDate = null;
        String dateInput = MenuScanner.readString("Enter new Completion Date (DD/MM/YYYY, current: " + (existingReservation.completion_date() != null ? existingReservation.completion_date().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "Not set") + "): ");
        if (!dateInput.isEmpty()) {
            try {
                newCompletionDate = LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Invalid date format. Please use DD/MM/YYYY.");
            }
        }

        // Devolver el DTO con el ID de la reserva y los campos opcionales.
        return new UpdateReservationDTO(
                reservationId, // ID de la reserva es obligatorio y ya validado
                newCustomerId,
                newPuzzleId,
                newCompletionDate
        );
    }
}

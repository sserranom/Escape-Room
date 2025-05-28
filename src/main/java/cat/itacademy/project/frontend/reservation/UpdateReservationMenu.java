package cat.itacademy.project.frontend.reservation;

import cat.itacademy.project.api.reservation.UpdateReservationController;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.frontend.shared.MenuScanner;
import cat.itacademy.project.shared.domain.dtos.reservation.UpdateReservationDTO;
import cat.itacademy.project.shared.domain.exceptions.NotFoundException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class UpdateReservationMenu extends MenuCommand<Void> {

    public UpdateReservationMenu() {
    }

    @Override
    public Optional<Void> execute() {
        try {
            UpdateReservationDTO dto = getInfo();
            UpdateReservationController controller = new UpdateReservationController();
            controller.execute(dto);
            info("Reservation with ID '" + dto.id() + "' updated successfully.");

        } catch (NotFoundException e) {
            error("Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            error("Error: " + e.getMessage());
        } catch (DateTimeParseException e) {
            error("Error: Invalid date/time format. Please use YYYY-MM-DD HH:MM:SS.");
        } catch (Exception e) {
            error("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private UpdateReservationDTO getInfo() {
        int reservationId = MenuScanner.readInt("Enter the ID of the reservation to update: ");

        Integer newCustomerId = null;
        String customerIdInput = MenuScanner.readString("Enter new Customer ID (leave blank to keep current): ").trim();
        if (!customerIdInput.isEmpty()) {
            try {
                int parsedId = Integer.parseInt(customerIdInput);
                newCustomerId = parsedId <= 0 ? null : parsedId;
            } catch (NumberFormatException e) {
                error("Invalid input for Customer ID. Keeping current value.");
            }
        }

        Integer newPuzzleId = null;
        String puzzleIdInput = MenuScanner.readString("Enter new Puzzle ID (leave blank to keep current): ").trim();
        if (!puzzleIdInput.isEmpty()) {
            try {
                int parsedId = Integer.parseInt(puzzleIdInput);
                newPuzzleId = parsedId <= 0 ? null : parsedId;
            } catch (NumberFormatException e) {
                error("Invalid input for Puzzle ID. Keeping current value.");
            }
        }

        LocalDate newCompletionDate = null;
        String completionDateInput = MenuScanner.readString("Enter new Completion Date (YYYY-MM-DD HH:MM:SS, leave blank to keep current): ").trim();
        if (!completionDateInput.isEmpty()) {
            try {
                newCompletionDate = LocalDate.parse(completionDateInput);
            } catch (DateTimeParseException e) {
                error("Invalid date/time format. Please use YYYY-MM-DD HH:MM:SS. Keeping current value.");
            }
        }

        return new UpdateReservationDTO(
                reservationId,
                newCustomerId,
                newPuzzleId,
                null,
                newCompletionDate
        );
    }
}
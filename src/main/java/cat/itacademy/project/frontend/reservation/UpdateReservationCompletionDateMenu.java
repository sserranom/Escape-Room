package cat.itacademy.project.frontend.reservation;

import cat.itacademy.project.api.reservation.UpdateReservationController;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.frontend.shared.MenuScanner;
import cat.itacademy.project.shared.domain.dtos.reservation.UpdateReservationDTO;
import cat.itacademy.project.shared.domain.exceptions.NotFoundException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class UpdateReservationCompletionDateMenu extends MenuCommand<Void> {


    @Override
    public Optional<Void> execute() {
        try {
            UpdateReservationDTO dto = getInfo();
            UpdateReservationController controller = new UpdateReservationController();

            controller.execute(dto);
            info("Completion date for Reservation ID '" + dto.id() + "' updated successfully.");

        } catch (NotFoundException e) {
            error("Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            error("Error: " + e.getMessage());
        } catch (DateTimeParseException e) {
            error("Error: Invalid date format. Please use YYYY-MM-DD.");
        } catch (Exception e) {
            error("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private UpdateReservationDTO getInfo() {
        int reservationId = MenuScanner.readInt("Enter the ID of the reservation to update: ");

        LocalDate newCompletionDate = null;
        String completionDateInput = MenuScanner.readString("Enter new Completion Date (YYYY-MM-DD): ").trim();

        if (completionDateInput.isEmpty()) {
            error("Error: Completion date cannot be blank for this operation. Please enter a date.");
            throw new IllegalArgumentException("Completion date cannot be blank.");
        }

        try {
            newCompletionDate = LocalDate.parse(completionDateInput);
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException("Invalid date format. Please use YYYY-MM-DD.", completionDateInput, e.getErrorIndex(), e);
        }

        return new UpdateReservationDTO(
                reservationId,
                null,
                null,
                null,
                newCompletionDate
        );
    }
}
package cat.itacademy.project.frontend.reservation;

import cat.itacademy.project.api.reservation.CreateReservationController;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.frontend.shared.MenuScanner;
import cat.itacademy.project.shared.domain.dtos.reservation.CreateReservationDTO;
import cat.itacademy.project.shared.domain.exceptions.EmptyFieldException;

import java.time.LocalDate;
import java.util.Optional;

public class CreateReservationMenu extends MenuCommand<Void> {
    private final CreateReservationController createReservationController;

    public CreateReservationMenu() {
        this.createReservationController = new CreateReservationController();
    }

    @Override
    public Optional<Void> execute() {
        try {
            CreateReservationDTO request = getUserInfo();
            createReservationController.execute(request);
            System.out.println("Reservation created successfully!");
        } catch (IllegalArgumentException e) {
            error("Validation error: " + e.getMessage());
        } catch (EmptyFieldException e) {
            error("Input error: " + e.getMessage());
        } catch (Exception e) {
            error("An unexpected error occurred during reservation creation: " + e.getMessage());
        }
        return Optional.empty();
    }

    public CreateReservationDTO getUserInfo() {
        Integer customerId = null;
        Integer puzzleId = null;
        LocalDate completionDate = null;

        boolean validInput = false;
        while (!validInput) {
            try {
                customerId = MenuScanner.readInt("Enter the Customer ID: ");
                puzzleId = MenuScanner.readInt("Enter the Puzzle ID: ");

                completionDate = MenuScanner.readOptionalDate("Enter the Completion Date (YYYY/MM/DD, leave empty if not known): ");

                validInput = true;

            } catch (IllegalArgumentException e) {
                error("Input error: " + e.getMessage());
            } catch (EmptyFieldException e) {
                error("Field cannot be empty: " + e.getMessage());
            } catch (Exception e) {
                error("An unexpected error occurred while reading input: " + e.getMessage());
            }
        }
        return new CreateReservationDTO(customerId, puzzleId, completionDate);
    }

}

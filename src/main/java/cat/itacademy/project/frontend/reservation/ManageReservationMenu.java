package cat.itacademy.project.frontend.reservation;

import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.frontend.shared.MenuScanner;

import java.util.Optional;

public class ManageReservationMenu extends MenuCommand<Void> {

    @Override
    public Optional<Void> execute() {

        boolean isRunning = true;
        while (isRunning) {
            int choice = getUserInput();
            switch (choice) {
                case 1:
                    log("Create Reservation: ");
                    CreateReservationMenu createReservationMenu = new CreateReservationMenu();
                    createReservationMenu.execute();
                    break;

                case 2:
                    log("Set Reservation as Completed: ");
                    UpdateReservationCompletionDateMenu updateReservationCompletionDateMenu = new UpdateReservationCompletionDateMenu();
                    updateReservationCompletionDateMenu.execute();
                    break;

                case 3:
                    log("Update Reservation details: ");
                    UpdateReservationMenu updateReservationMenu = new UpdateReservationMenu();
                    updateReservationMenu.execute();
                    break;

                case 4:
                    log("Reservation Details: ");
                    FindAllReservationMenu findAllReservationMenu = new FindAllReservationMenu();
                    findAllReservationMenu.execute();
                    break;

                case 5:
                    log("5. Print Reservation Ticket: ");
                    PrintReservationTicketMenu printReservationTicketMenu = new PrintReservationTicketMenu();
                    printReservationTicketMenu.execute();
                    break;

                case 6:
                    log("Back: ");
                    isRunning = false;
                    break;


                default:
                    error("Invalid choice. Please try again.");
                    choice = getUserInput();

            }
        }
        return Optional.empty();
    }

    private int getUserInput() {
        log("1. Create new Reservation ");
        log("2. Set Reservation as Completed ");
        log("3. Update Reservation details ");
        log("4. View Reservation details");
        log("5. Print Reservation Ticket ");
        log("6. Back <-");

        return MenuScanner.readInt("Please enter your choice: ");
    }
}

package cat.itacademy.project.frontend.reservation;

import cat.itacademy.project.frontend.Room.CreateRoomMenu;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.frontend.shared.MenuScanner;

import java.util.Optional;

public class ManageReservationMenu extends MenuCommand<Void> {

    @Override
    public Optional<Void> execute() {
        int choice = getUserInput();
        boolean isRunning = true;
        while (isRunning) {

            switch (choice) {
                case 1:
                    log("Create Reservation: ");
                    CreateReservationMenu createReservationMenu = new CreateReservationMenu();
                    createReservationMenu.execute();
                    break;

                case 2:
//                    log("Update Room details: ");
//                    UpdateReservationMenu updateReservationMenu = new UpdateReservationMenu();
//                    updateReservationMenu.execute();
                    break;

                case 3:
//                    log("Room Details: ");
//                    FindAllReservationMenu findAllReservationMenu = new FindAllReservationMenu();
//                    findAllReservationMenu.execute();
                    break;

                case 4:
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
        log("2. Update Reservation details ");
        log("3. View Reservation details");
        log("4. Back <-");

        return MenuScanner.readInt("Please enter your choice: ");
    }
}

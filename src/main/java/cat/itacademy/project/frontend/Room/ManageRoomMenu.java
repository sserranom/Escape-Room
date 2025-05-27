package cat.itacademy.project.frontend.Room;

import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.frontend.shared.MenuScanner;

import java.util.Optional;

public class ManageRoomMenu extends MenuCommand<Void> {



    @Override
    public Optional<Void> execute() {
        int choice = getUserInput();
        boolean isRunning = true;
        while (isRunning) {

            switch (choice) {
                case 1:
                    log("Create Room: ");
                    CreateRoomMenu createRoomMenu = new CreateRoomMenu();
                    createRoomMenu.execute();
                    break;

                case 2:
                    log("Update Room details: ");
                    UpdateRoomMenu updateRoomMenu = new UpdateRoomMenu();
                    updateRoomMenu.execute();
                    break;

                case 3:
                    log("Room Details: ");
                    FindAllRoomMenu findAllRoomMenu = new FindAllRoomMenu();
                    findAllRoomMenu.execute();
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
        log("1. Create new Room ");
        log("2. Update Room details ");
        log("3. View Room details");
        log("4. Back <-");

        return MenuScanner.readInt("Please enter your choice: ");
    }
}

package cat.itacademy.project.frontend.Room;

import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.frontend.shared.MenuScanner;
import cat.itacademy.project.shared.domain.exceptions.CustomException;

import java.util.Optional;

public class ManageRoomMenu extends MenuCommand<Void> {


    @Override
    public Optional<Void> execute() {
        boolean isRunning = true;
        while (isRunning) {
            int choice = getUserInput();
            try {
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
                        log("Show all rooms: ");
                        FindAllRoomMenu findAllRoomMenu = new FindAllRoomMenu();
                        findAllRoomMenu.execute();
                        break;
                    case 4:
                        log("Add decorative item to room: ");
                        AddDecoToRoomMenu addDecoToRoomMenu = new AddDecoToRoomMenu();
                        addDecoToRoomMenu.execute();
                        break;
                    case 5:
                        log("Back: ");
                        isRunning = false;
                        break;
                    default:
                        error("Invalid choice. Please try again.");

                }
            } catch (CustomException e) {
                error(e.getMessage());
            }
        }
        return Optional.empty();
    }

    private int getUserInput() {
        log("1. Create new Room ");
        log("2. Update Room details ");
        log("3. Show all rooms ");
        log("4. Add decorative item to room ");
        log("5. Back <-");

        return MenuScanner.readInt("Please enter your choice: ");
    }
}

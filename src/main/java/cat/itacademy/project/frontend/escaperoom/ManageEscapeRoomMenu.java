package cat.itacademy.project.frontend.escaperoom;

import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.frontend.shared.MenuScanner;
import cat.itacademy.project.shared.domain.dtos.escape_room.EscapeRoomDTO;

import java.util.Optional;

public class ManageEscapeRoomMenu extends MenuCommand<EscapeRoomDTO> {
    private final EscapeRoomDTO escapeRoomDTO;

    public ManageEscapeRoomMenu(EscapeRoomDTO escapeRoomDTO) {
        this.escapeRoomDTO = escapeRoomDTO;
    }

    @Override
    public Optional<EscapeRoomDTO> execute() {
        int choice = getUserInput();

        switch (choice) {
            case 1:
                log("Escape room details:");
                EscapeRoomPrinter.print(escapeRoomDTO);
                break;
            case 2:
                log("Update escape room details:");
                UpdateEscapeRoomMenu updateEscapeRoomMenu = new UpdateEscapeRoomMenu(escapeRoomDTO);
                updateEscapeRoomMenu.execute();
                break;
            case 3:
                log("Delete escape room:");
                DeleteEscapeRoomMenu deleteEscapeRoomMenu = new DeleteEscapeRoomMenu(escapeRoomDTO);
                deleteEscapeRoomMenu.execute();
                break;
            default:
                error("Invalid choice. Please try again.");
                break;
        }
        return Optional.of(escapeRoomDTO);
    }

    private int getUserInput() {
        log("1. View escape room details");
        log("2. Update escape room details");
        log("3. Delete escape room");
        log("4. Manage Rooms"); // create new , edit,
        log("4. Manage Themes");
        log("5. Manage puzzles");
        log("6. Manage packs");
        log("7. Manage reservations");
        log("8. Show inventory");

        return MenuScanner.readInt("Please enter your choice: ");
    }
}

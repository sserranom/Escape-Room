package cat.itacademy.project.frontend.escaperoom;


import cat.itacademy.project.frontend.Room.ManageRoomMenu;
import cat.itacademy.project.frontend.deco.ManageDecoMenu;
import cat.itacademy.project.frontend.Menu;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.frontend.shared.MenuScanner;
import cat.itacademy.project.shared.domain.dtos.deco.DecoDTO;
import cat.itacademy.project.shared.domain.dtos.escape_room.EscapeRoomDTO;
import cat.itacademy.project.shared.domain.dtos.room.RoomDTO;

import java.util.Optional;

public class ManageEscapeRoomMenu extends MenuCommand<EscapeRoomDTO> {
    private final EscapeRoomDTO escapeRoomDTO;
    private RoomDTO roomDTO;
    private DecoDTO decoDTO;

    public ManageEscapeRoomMenu(EscapeRoomDTO escapeRoomDTO) {
        this.escapeRoomDTO = escapeRoomDTO;
    }

    public ManageEscapeRoomMenu(EscapeRoomDTO escapeRoomDTO, RoomDTO roomDTO, DecoDTO decoDTO) {
        this.escapeRoomDTO = escapeRoomDTO;
        this.roomDTO = roomDTO;
        this.decoDTO = decoDTO;
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
                Optional<EscapeRoomDTO> updated = updateEscapeRoomMenu.execute();
                updated.ifPresent(escapeRoomDTO -> {
                    Menu.setActiveRoom(escapeRoomDTO);
                    Menu.updateExistingRooms(escapeRoomDTO);
                });
                break;

            case 3:
                log("Manage rooms: ");
                ManageRoomMenu manageRoomMenu = new ManageRoomMenu(roomDTO);
                manageRoomMenu.execute();
                break;

            case 4:
                log("Manage Decorative Items: ");
                ManageDecoMenu manageDecoMenu = new ManageDecoMenu(decoDTO);
                manageDecoMenu.execute();
                break;

            case 10:
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
        log("3. Manage Rooms");
        log("4. Manage Decorative Items");
        log("5. Manage Themes");
        log("6. Manage puzzles");
        log("7. Manage packs");
        log("8. Manage reservations");
        log("9. Show inventory");
        log("10. Delete escape room");

        return MenuScanner.readInt("Please enter your choice: ");
    }
}

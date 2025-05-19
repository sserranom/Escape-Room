package cat.itacademy.project.frontend.escaperoom;

import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.frontend.shared.MenuScanner;
import cat.itacademy.project.shared.domain.dtos.EscapeRoomDTO;

import java.util.Optional;

public class ManageEscapeRoomMenu extends MenuCommand<EscapeRoomDTO> {
    private final EscapeRoomDTO escapeRoomDTO;

    public ManageEscapeRoomMenu(EscapeRoomDTO escapeRoomDTO) {
        this.escapeRoomDTO = escapeRoomDTO;
    }

    @Override
    public Optional<EscapeRoomDTO> execute() {
        EscapeRoomPrinter.print(escapeRoomDTO);
        return Optional.of(escapeRoomDTO);
    }
    private int getUserInput() {
        log("1. View escape room details");
        log("2. Update escape room details");
        log("3. Delete escape room");
        log("4. Manage rooms");
        log("4. manage themes");
        log("5. Manage puzzles");
        log("6. Manage packs");
        log("7. Manage reservations");

        return MenuScanner.readInt("Please enter your choice: ");
    }
}

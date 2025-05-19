package cat.itacademy.project.frontend;

import cat.itacademy.project.frontend.escaperoom.CreateEscapeRoomMenu;
import cat.itacademy.project.frontend.escaperoom.FindEscapeRoomsMenu;
import cat.itacademy.project.frontend.escaperoom.SelectActiveEscapeRoomMenu;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.shared.domain.dtos.EscapeRoomDTO;

import java.util.List;
import java.util.Optional;

public class Menu extends MenuCommand<Void> {
    private final CreateEscapeRoomMenu createEscapeRoomMenu = new CreateEscapeRoomMenu();
    FindEscapeRoomsMenu findEscapeRoomsMenu = new FindEscapeRoomsMenu();
    private EscapeRoomDTO activeRoom;
    private List<EscapeRoomDTO> existingRooms;

    private void getExistingEscapeRooms() {
        Optional<List<EscapeRoomDTO>> response = findEscapeRoomsMenu.execute();
        response.ifPresent(escapeRoomDTOS -> existingRooms = escapeRoomDTOS);

    }

    public Optional<Void> execute() {
        getExistingEscapeRooms();
        if (existingRooms.isEmpty()) {
            Optional<EscapeRoomDTO> response = createEscapeRoomMenu.execute();
            response.ifPresent(escapeRoomDTO -> activeRoom = escapeRoomDTO);
        } else {
            while (activeRoom == null) {
                SelectActiveEscapeRoomMenu selectActiveRoomMenu = new SelectActiveEscapeRoomMenu(existingRooms);
                Optional<EscapeRoomDTO> selected = selectActiveRoomMenu.execute();
                selected.ifPresent(escapeRoomDTO -> activeRoom = escapeRoomDTO);
            }
        }


        return Optional.empty();


    }
}

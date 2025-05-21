package cat.itacademy.project.frontend;

import cat.itacademy.project.frontend.escaperoom.CreateEscapeRoomMenu;
import cat.itacademy.project.frontend.escaperoom.FindAllEscapeRoomsMenu;
import cat.itacademy.project.frontend.escaperoom.ManageEscapeRoomMenu;
import cat.itacademy.project.frontend.escaperoom.SelectActiveEscapeRoomMenu;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.shared.domain.dtos.escape_room.EscapeRoomDTO;

import java.util.List;
import java.util.Optional;

public class Menu extends MenuCommand<Void> {
    private final CreateEscapeRoomMenu createEscapeRoomMenu = new CreateEscapeRoomMenu();

    FindAllEscapeRoomsMenu findAllEscapeRoomsMenu = new FindAllEscapeRoomsMenu();
    private EscapeRoomDTO activeRoom;
    private List<EscapeRoomDTO> existingRooms;
    private void getExistingEscapeRooms() {
        Optional<List<EscapeRoomDTO>> response = findAllEscapeRoomsMenu.execute();
        response.ifPresent(escapeRoomDTOS -> existingRooms = escapeRoomDTOS);

    }

    public void addRoom(EscapeRoomDTO escapeRoomDTO) {
        existingRooms.add(escapeRoomDTO);
    }
    public void setActiveRoom(EscapeRoomDTO activeRoom) {
        this.activeRoom = activeRoom;
    }

    public Optional<Void> execute() {
        setupCurrentRoom();
        while (true) {
            ManageEscapeRoomMenu manageEscapeRoomMenu = new ManageEscapeRoomMenu(activeRoom);
            manageEscapeRoomMenu.execute();

        }


    }

    private void setupCurrentRoom() {
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
    }
}

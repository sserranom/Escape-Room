package cat.itacademy.project.frontend;

import cat.itacademy.project.frontend.escaperoom.CreateEscapeRoomMenu;
import cat.itacademy.project.frontend.escaperoom.FindAllEscapeRoomsMenu;
import cat.itacademy.project.frontend.escaperoom.ManageEscapeRoomMenu;
import cat.itacademy.project.frontend.escaperoom.SelectActiveEscapeRoomMenu;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.shared.domain.dtos.escape_room.EscapeRoomDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Menu extends MenuCommand<Void> {
    private final CreateEscapeRoomMenu createEscapeRoomMenu = new CreateEscapeRoomMenu();

    FindAllEscapeRoomsMenu findAllEscapeRoomsMenu = new FindAllEscapeRoomsMenu();
    private static EscapeRoomDTO activeRoom;
    private static final List<EscapeRoomDTO> existingRooms = new ArrayList<>();
    private void getExistingEscapeRooms() {
        Optional<List<EscapeRoomDTO>> response = findAllEscapeRoomsMenu.execute();
        response.ifPresent(existingRooms::addAll);

    }

    public static void addRoom(EscapeRoomDTO escapeRoomDTO) {
        existingRooms.add(escapeRoomDTO);
    }
    public static void setActiveRoom(EscapeRoomDTO dto) {
        activeRoom = dto;
    }
    public static void updateExistingRooms(EscapeRoomDTO escapeRoomDTO) {
        existingRooms.forEach(room -> {
            if (room.id() == escapeRoomDTO.id()) {
                existingRooms.set(existingRooms.indexOf(room), escapeRoomDTO);
            }
        });
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

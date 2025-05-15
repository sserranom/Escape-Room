package cat.itacademy.project.frontend;

import cat.itacademy.project.api.escaperoom.application.FindEscapeRoomsController;
import cat.itacademy.project.shared.domain.dtos.EscapeRoomDTO;
import org.w3c.dom.ls.LSOutput;

import java.util.List;
import java.util.Optional;

public class Menu {
    private EscapeRoomDTO selected;
    private List<EscapeRoomDTO> existingRooms;
    private final FindEscapeRoomsController findEscapeRoomsController = new FindEscapeRoomsController();
    private void getExistingRooms(){
        Optional<List<EscapeRoomDTO>> response= findEscapeRoomsController.execute();
        response.ifPresent(escapeRoomDTOS -> existingRooms = escapeRoomDTOS);

    }
    public void execute(){
        getExistingRooms();
        if (existingRooms.isEmpty()){
            System.out.println("No escape rooms found");
        }
    }
}

package cat.itacademy.project.frontend.escaperoom;

import cat.itacademy.project.api.escaperoom.application.FindEscapeRoomsController;
import cat.itacademy.project.business_logic.escaperoom.domain.EscapeRoom;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.shared.domain.dtos.EscapeRoomDTO;

import java.util.List;
import java.util.Optional;

public class FindEscapeRoomsMenu extends MenuCommand<List<EscapeRoomDTO>> {

    @Override
    public Optional<List<EscapeRoomDTO>> execute() {

        FindEscapeRoomsController controller = new FindEscapeRoomsController();
        Optional<List<EscapeRoomDTO>> result = controller.execute();

        if (result.isPresent()){
            if (result.isEmpty()) {
                info("No escape Rooms found.");
            } else {
                info("List of escape Rooms");
                escapeRooms.forEach(room -> log(room.name() + " (" + room.url()+ ")"));
            return result;
            }
//            if (escapeRooms.isEmpty()){
//                info("No escape rooms found");
//            }else {
//                info ("List of escape Rooms.");
//            }
        }
        return Optional.empty();
    }
}

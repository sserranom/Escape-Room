package cat.itacademy.project.frontend.escaperoom;

import cat.itacademy.project.api.escaperoom.application.ListEscapeRoomInventoryController;
import cat.itacademy.project.shared.domain.dtos.escape_room.EscapeRoomDTO;
import cat.itacademy.project.shared.domain.dtos.escape_room.EscapeRoomInventoryDto;

import java.util.List;

public class ListEscapeRoomInventoryMenu  {
    public ListEscapeRoomInventoryMenu(EscapeRoomDTO escapeRoomDTO) {
        ListEscapeRoomInventoryController controller = new ListEscapeRoomInventoryController();
        EscapeRoomInventoryDto inventory = controller.execute(escapeRoomDTO.id());
        EscapeRoomPrinter.printInvetory(inventory);
    }
}

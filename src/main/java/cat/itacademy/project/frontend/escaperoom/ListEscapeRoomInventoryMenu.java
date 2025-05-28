package cat.itacademy.project.frontend.escaperoom;

import cat.itacademy.project.api.escaperoom.application.ListEscapeRoomInventoryController;
import cat.itacademy.project.frontend.Menu;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.shared.domain.dtos.escape_room.EscapeRoomInventoryDto;

import java.util.Optional;

public class ListEscapeRoomInventoryMenu extends MenuCommand<Void> {


    @Override
    public Optional<Void> execute() {
        ListEscapeRoomInventoryController controller = new ListEscapeRoomInventoryController();
        EscapeRoomInventoryDto inventory = controller.execute(Menu.activeRoom().id());
        EscapeRoomPrinter.printInvetory(inventory);
        return Optional.empty();
    }
}

package cat.itacademy.project.frontend.escaperoom;

import cat.itacademy.project.api.escaperoom.application.FindEscapeRoomsController;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.shared.domain.dtos.EscapeRoomDTO;

import java.util.List;
import java.util.Optional;

public class FindEscapeRoomsMenu extends MenuCommand<List<EscapeRoomDTO>> {

    @Override
    public Optional<List<EscapeRoomDTO>> execute() {

        FindEscapeRoomsController controller = new FindEscapeRoomsController();
        Optional<List<EscapeRoomDTO>> result = controller.execute();
            if (result.get().isEmpty()) {
                error("No escape Rooms found.");
            } else {
                info("List of escape Rooms");
                result.get().forEach(EscapeRoomPrinter::print);
        }
        return result;
    }
}

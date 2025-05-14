package cat.itacademy.project.frontend.escaperoom;

import cat.itacademy.project.api.escaperoom.application.FindEscapeRoomsController;
import cat.itacademy.project.business_logic.escaperoom.domain.EscapeRoom;
import cat.itacademy.project.frontend.shared.MenuCommand;

import java.util.List;
import java.util.Optional;

public class FindEscapeRoomsMenu extends MenuCommand<Void> {

    @Override
    public Optional<Void> execute() {

        FindEscapeRoomsController controller = new FindEscapeRoomsController();
        Optional<List<EscapeRoom>> result = controller.execute();

        if (result.isPresent()){
            List<EscapeRoom> escapeRooms = result.get();
            if (escapeRooms.isEmpty()){
                info("No escape rooms found");
            }else {
                info ("List of escape Rooms.");
            }
        }
        return Optional.empty();
    }
}

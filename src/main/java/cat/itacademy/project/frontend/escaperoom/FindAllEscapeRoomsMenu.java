package cat.itacademy.project.frontend.escaperoom;

import cat.itacademy.project.api.escaperoom.application.FindAllEscapeRoomsController;
import cat.itacademy.project.business_logic.escaperoom.domain.EscapeRoom;
import cat.itacademy.project.frontend.shared.MenuCommand;

import java.util.List;
import java.util.Optional;

public class FindAllEscapeRoomsMenu extends MenuCommand<Void> {

    @Override
    public Optional<Void> execute() {

        FindAllEscapeRoomsController controller = new FindAllEscapeRoomsController();
        Optional<List<EscapeRoom>> result = controller.execute();

        if (result.isPresent()){
            List<EscapeRoom> escapeRooms = result.get();
            if (escapeRooms.isEmpty()){
                info("No escape rooms found");
            }
        }
        return Optional.empty();
    }
}

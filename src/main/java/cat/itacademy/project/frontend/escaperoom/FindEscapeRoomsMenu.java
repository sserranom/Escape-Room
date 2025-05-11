package cat.itacademy.project.frontend.escaperoom;

import cat.itacademy.project.api.escaperoom.application.FindEscapeRoomsController;
import cat.itacademy.project.buissness_logic.escaperoom.application.FindEscapeRooms;
import cat.itacademy.project.frontend.shared.MenuCommand;

import java.util.Optional;

public class FindEscapeRoomsMenu extends MenuCommand<Void> {

    @Override
    public Optional<Void> execute() {
        FindEscapeRoomsController controller  = new FindEscapeRoomsController();
        controller.execute();
        return Optional.empty();
    }
}

package cat.itacademy.project.frontend.escaperoom;

import cat.itacademy.project.api.escaperoom.application.DeleteEscapeRoomController;
import cat.itacademy.project.frontend.Menu;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.shared.domain.exceptions.CustomException;

import java.util.Optional;

public class DeleteEscapeRoomMenu extends MenuCommand<Void> {


    @Override
    public Optional<Void> execute() {
        try {
            DeleteEscapeRoomController controller = new DeleteEscapeRoomController();
            controller.execute(Menu.activeRoom().id());
        } catch (CustomException e) {
            error(e.getMessage());
        }

        return Optional.empty();
    }
}

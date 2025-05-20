package cat.itacademy.project.frontend.escaperoom;

import cat.itacademy.project.api.escaperoom.application.DeleteEscapeRoomController;
import cat.itacademy.project.api.escaperoom.application.FindEscapeRoomByIdController;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.frontend.shared.MenuScanner;
import cat.itacademy.project.shared.domain.dtos.EscapeRoomDTO;
import cat.itacademy.project.shared.domain.exceptions.CustomException;

import java.util.Optional;

public class DeleteEscapeRoomMenu extends MenuCommand<Void> {

    @Override
    public Optional<Void> execute() {
try {
    int idToDelete = MenuScanner.readInt("Enter the ID of the escape room to delete: ");
    DeleteEscapeRoomController controller = new DeleteEscapeRoomController(idToDelete);
    controller.execute();
} catch (CustomException e) {
    error(e.getMessage());
}

        return Optional.empty();
    }
}

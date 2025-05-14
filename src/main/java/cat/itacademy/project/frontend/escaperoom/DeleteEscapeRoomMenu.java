package cat.itacademy.project.frontend.escaperoom;

import cat.itacademy.project.api.escaperoom.application.DeleteEscapeRoomController;
import cat.itacademy.project.api.escaperoom.application.FindEscapeRoomByIdController;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.frontend.shared.MenuScanner;
import cat.itacademy.project.shared.domain.dtos.EscapeRoomDTO;

import java.util.Optional;

public class DeleteEscapeRoomMenu extends MenuCommand<Void> {

    @Override
    public Optional<Void> execute() {

            int idToDelete = MenuScanner.readInt("Enter the ID of the escape room to delete: ");
            DeleteEscapeRoomController controller = new DeleteEscapeRoomController(idToDelete);
            Optional<Boolean> deletionResult = controller.execute();
            FindEscapeRoomByIdController findController = new FindEscapeRoomByIdController(idToDelete);
            Optional<Optional<EscapeRoomDTO>> deletedRoom = findController.execute();

        if (deletionResult.isPresent() && deletionResult.get()) {
            info("Escape room with ID " + idToDelete + " has been deleted.");
        } else {
            error("Escape room with ID " + idToDelete + " not found.");
        }

        return Optional.empty();
    }
}

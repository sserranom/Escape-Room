package cat.itacademy.project.frontend.escaperoom;

import cat.itacademy.project.api.escaperoom.application.FindEscapeRoomByIdController;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.frontend.shared.MenuScanner;
import cat.itacademy.project.shared.domain.dtos.escape_room.EscapeRoomDTO;

import java.util.Optional;

public class FindEscapeRoomByIdMenu extends MenuCommand<Void> {

    @Override
    public Optional<Void> execute() {
        try {
            int idToFind = MenuScanner.readInt("Enter the ID of the escape room to find: ");
            FindEscapeRoomByIdController controller = new FindEscapeRoomByIdController(idToFind);
            Optional<Optional<EscapeRoomDTO>> escapeRoomDTO = controller.execute();

            if (escapeRoomDTO.isPresent()) {
                Optional<EscapeRoomDTO> foundRoom = escapeRoomDTO.get();
                info("Found escape room:");
                info("ID: " + foundRoom.get().id());
                info("Name: " + foundRoom.get().name());
                info("URL: " + foundRoom.get().url());
            } else {
                error("Escape room with ID " + idToFind + " not found.");
            }

        } catch (Exception e) {
            error("An unexpected error occurred: " + e.getMessage());
        }
        return Optional.empty();
    }
}

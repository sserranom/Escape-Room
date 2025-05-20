package cat.itacademy.project.frontend.puzzle;

import cat.itacademy.project.api.room.FindRoomByIdController;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.frontend.shared.MenuScanner;
import cat.itacademy.project.shared.domain.dtos.RoomDTO;

import java.util.Optional;

public class FindRoomByIdMenu extends MenuCommand<Void> {
    @Override
    public Optional<Void> execute() {
        try {
            int idToFind = MenuScanner.readInt("Enter the ID of the room to find: ");
            FindRoomByIdController controller = new FindRoomByIdController(idToFind);
            Optional<Optional<RoomDTO>> roomDTO = controller.execute();

            if (roomDTO.isPresent()) {
                Optional<RoomDTO> foundRoom = roomDTO.get();
                info("Found room:");
                info("ID: " + foundRoom.get().id());
                info("Name: " + foundRoom.get().name());
                info("Price: " + foundRoom.get().price());
                info("EscapeRoomID: " + foundRoom.get().escapeRoomId());
            } else {
                error("Room with ID " + idToFind + " not found.");
            }

        } catch (Exception e) {
            error("An unexpected error occurred: " + e.getMessage());
        }
        return Optional.empty();
    }
}

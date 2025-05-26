package cat.itacademy.project.frontend.Room;

import cat.itacademy.project.api.room.FindRoomByIdController;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.frontend.shared.MenuScanner;
import cat.itacademy.project.shared.domain.dtos.room.RoomDTO;
import java.util.Optional;

public class FindRoomByIdMenu extends MenuCommand<Void> {
    @Override
    public Optional<Void> execute() {
        try {
            int idToFind = MenuScanner.readInt("Enter the ID of the room to find: ");
            FindRoomByIdController controller = new FindRoomByIdController();
            Optional<RoomDTO> roomDTO = controller.execute(idToFind);

            if (roomDTO.isPresent()) {
                RoomPrinter.print(roomDTO.get());
            } else {
                error("Room with ID " + idToFind + " not found.");
            }

        } catch (Exception e) {
            error("An unexpected error occurred: " + e.getMessage());
        }
        return Optional.empty();
    }
}

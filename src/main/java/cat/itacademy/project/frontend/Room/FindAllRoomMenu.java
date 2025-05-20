package cat.itacademy.project.frontend.Room;

import cat.itacademy.project.api.room.FindAllRoomsController;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.shared.domain.dtos.room.RoomDTO;

import java.util.List;
import java.util.Optional;

public class FindAllRoomMenu extends MenuCommand<List<RoomDTO>> {
    @Override
    public Optional<List<RoomDTO>> execute() {
        FindAllRoomsController controller = new FindAllRoomsController();
        Optional<List<RoomDTO>> result = controller.execute();
        if (result.isEmpty()) {
            info("No rooms Found.");
        } else {
            info("List of Rooms: ");
            result.get().forEach(room -> log("hola"));
        }

        return result;
    }
}

package cat.itacademy.project.frontend.Room;

import cat.itacademy.project.api.escaperoom.application.FindAllEscapeRoomsController;
import cat.itacademy.project.api.room.FindAllRoomsController;
import cat.itacademy.project.business_logic.escaperoom.domain.EscapeRoom;
import cat.itacademy.project.business_logic.room.domain.Room;
import cat.itacademy.project.frontend.shared.MenuCommand;

import java.util.List;
import java.util.Optional;

public class FindAllRoomMenu extends MenuCommand<Void> {
    @Override
    public Optional<Void> execute() {
        FindAllRoomsController controller = new FindAllRoomsController();
        Optional<List<Room>> result = controller.execute();

        if (result.isPresent()){
            List<Room> rooms = result.get();
            if (rooms.isEmpty()){
                info("No rooms found");
            }
        }
        return Optional.empty();
    }
}

package cat.itacademy.project.api.room;

import cat.itacademy.project.business_logic.room.application.FindAllRoomService;
import cat.itacademy.project.business_logic.room.domain.Room;
import cat.itacademy.project.business_logic.room.domain.RoomRepository;
import cat.itacademy.project.business_logic.room.infraestructure.RoomMySQLRepository;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.List;
import java.util.Optional;

public class FindAllRoomsController extends MenuCommand<List<Room>> {
    private final FindAllRoomService service;

    public FindAllRoomsController() {
        RoomRepository repo = new RoomMySQLRepository(MySqlConnection.getInstance());
        this.service = new FindAllRoomService(repo);
    }

    @Override
    public Optional<List<Room>> execute() {
        List<Room> rooms = service.findAllRaw();
        if(rooms.isEmpty()){
            info("No rooms Found.");
        } else {
            info("List of Rooms: ");
            rooms.forEach(room -> log(String.valueOf(room.getId()) + room.getName() +  String.valueOf(room.getPrice()) + String.valueOf(room.getEscapeRoomId())));
        }
        return Optional.of(rooms);
    }
}

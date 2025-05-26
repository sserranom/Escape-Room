package cat.itacademy.project.api.room;

import cat.itacademy.project.business_logic.room.application.FindAllRoomService;
import cat.itacademy.project.business_logic.room.domain.Room;
import cat.itacademy.project.business_logic.room.domain.RoomRepository;
import cat.itacademy.project.business_logic.room.infraestructure.RoomMySQLRepository;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.shared.domain.dtos.room.RoomDTO;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.List;
import java.util.Optional;

public class FindAllRoomsController {
    private final FindAllRoomService service;

    public FindAllRoomsController() {
        RoomRepository repo = new RoomMySQLRepository(MySqlConnection.getInstance());
        this.service = new FindAllRoomService(repo);
    }

    public Optional<List<RoomDTO>> execute() {
        List<RoomDTO> rooms = service.findAll();

        return Optional.of(rooms);
    }
}

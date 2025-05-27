package cat.itacademy.project.api.room;

import cat.itacademy.project.business_logic.room.application.FindRoomByIdService;
import cat.itacademy.project.business_logic.room.domain.RoomRepository;
import cat.itacademy.project.business_logic.room.infraestructure.RoomMySQLRepository;
import cat.itacademy.project.shared.domain.dtos.room.RoomDTO;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.Optional;

public class FindRoomByIdController {
    private final FindRoomByIdService service;


    public FindRoomByIdController() {
        RoomRepository repo = new RoomMySQLRepository(MySqlConnection.getInstance());
        this.service = new FindRoomByIdService(repo);

    }


    public Optional<RoomDTO> execute(int id) {

        return service.execute(id);

    }
}

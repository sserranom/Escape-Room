package cat.itacademy.project.api.room;

import cat.itacademy.project.business_logic.room.application.CreateRoomService;
import cat.itacademy.project.business_logic.room.domain.RoomRepository;
import cat.itacademy.project.business_logic.room.infraestructure.RoomMySQLRepository;
import cat.itacademy.project.shared.domain.dtos.room.CreateRoomDTO;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

public class CreateRoomController {
    private final CreateRoomService service;

    public CreateRoomController() {
        RoomRepository repo = new RoomMySQLRepository(MySqlConnection.getInstance());
        this.service = new CreateRoomService(repo);
    }


    public void execute(CreateRoomDTO createRoomDTO) {
        service.execute(createRoomDTO);

    }
}

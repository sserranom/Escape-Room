package cat.itacademy.project.api.room;

import cat.itacademy.project.business_logic.room.application.UpdateRoomService;
import cat.itacademy.project.business_logic.room.domain.RoomRepository;
import cat.itacademy.project.business_logic.room.infraestructure.RoomMySQLRepository;
import cat.itacademy.project.shared.domain.dtos.room.UpdateRoomDTO;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

public class UpdateRoomController {
    private final UpdateRoomService service;

    public UpdateRoomController() {
        RoomRepository repo = new RoomMySQLRepository(MySqlConnection.getInstance());
        this.service = new UpdateRoomService(repo);
    }


    public void execute(UpdateRoomDTO request) {
        service.execute(request);

    }
}

package cat.itacademy.project.api.room;

import cat.itacademy.project.business_logic.room.application.AssignDecoToRoomService;
import cat.itacademy.project.business_logic.room.infraestructure.RoomDecoMySQLRepository;
import cat.itacademy.project.shared.domain.dtos.room.AssignDecoToRoomDto;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

public class AssignDecoToRoomController {
    AssignDecoToRoomService assignDecoToRoomService;

    public AssignDecoToRoomController() {
        this.assignDecoToRoomService = new AssignDecoToRoomService(new RoomDecoMySQLRepository(MySqlConnection.getInstance()));
    }

    public void execute(AssignDecoToRoomDto request) {
        assignDecoToRoomService.execute(request);
    }
}

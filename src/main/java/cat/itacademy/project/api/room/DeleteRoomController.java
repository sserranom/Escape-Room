package cat.itacademy.project.api.room;

import cat.itacademy.project.business_logic.room.application.DeleteRoomService;
import cat.itacademy.project.business_logic.room.domain.RoomRepository;
import cat.itacademy.project.business_logic.room.infraestructure.RoomMySQLRepository;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.Optional;

public class DeleteRoomController {
    private final DeleteRoomService service;


    public DeleteRoomController() {
        RoomRepository repo = new RoomMySQLRepository(MySqlConnection.getInstance());
        this.service = new DeleteRoomService(repo);

    }

    public Optional<Void> execute(int id) {
        service.execute(id);
        return Optional.empty();
    }
}

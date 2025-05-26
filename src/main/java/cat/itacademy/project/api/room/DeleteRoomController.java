package cat.itacademy.project.api.room;

import cat.itacademy.project.business_logic.room.application.DeleteRoomService;
import cat.itacademy.project.business_logic.room.domain.RoomRepository;
import cat.itacademy.project.business_logic.room.infraestructure.RoomMySQLRepository;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.Optional;

public class DeleteRoomController implements Command<Void> {
    private final DeleteRoomService service;
    private final int idToDelete;

    public DeleteRoomController(int idToDelete) {
        RoomRepository repo = new RoomMySQLRepository(MySqlConnection.getInstance());
        this.service = new DeleteRoomService(idToDelete, repo);
        this.idToDelete = idToDelete;
    }

    @Override
    public Optional<Void> execute() {
        service.execute();
        return Optional.empty();
    }
}

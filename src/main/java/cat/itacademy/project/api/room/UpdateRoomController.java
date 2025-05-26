package cat.itacademy.project.api.room;

import cat.itacademy.project.business_logic.room.application.UpdateRoomService;
import cat.itacademy.project.business_logic.room.domain.RoomRepository;
import cat.itacademy.project.business_logic.room.infraestructure.RoomMySQLRepository;
import cat.itacademy.project.shared.domain.dtos.room.UpdateRoomDTO;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.Optional;

public class UpdateRoomController implements Command<Void> {
    private final UpdateRoomService service;

    public UpdateRoomController(UpdateRoomDTO updateRoomDTO) {
        RoomRepository repo = new RoomMySQLRepository(MySqlConnection.getInstance());
        this.service = new UpdateRoomService(updateRoomDTO, repo);
    }

    @Override
    public Optional<Void> execute() {
        service.execute();
        return Optional.empty();
    }
}

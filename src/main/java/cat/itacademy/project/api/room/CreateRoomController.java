package cat.itacademy.project.api.room;

import cat.itacademy.project.business_logic.room.application.CreateRoomService;
import cat.itacademy.project.business_logic.room.domain.RoomRepository;
import cat.itacademy.project.business_logic.room.infraestructure.RoomMySQLRepository;
import cat.itacademy.project.shared.domain.dtos.room.CreateRoomDTO;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.Optional;

public class CreateRoomController implements Command<Void> {
    private final CreateRoomService service;

    public CreateRoomController(CreateRoomDTO createRoomDTO) {
        RoomRepository repo = new RoomMySQLRepository(MySqlConnection.getInstance());
        this.service = new CreateRoomService(createRoomDTO, repo);
    }

    @Override
    public Optional<Void> execute() {
        service.execute();
        return Optional.empty();
    }
}

package cat.itacademy.project.api.escaperoom.application;

import cat.itacademy.project.business_logic.escaperoom.application.CreateEscapeRoomService;
import cat.itacademy.project.business_logic.escaperoom.domain.EscapeRoomRepository;
import cat.itacademy.project.business_logic.escaperoom.infrastructure.EscapeRoomMySQLRepository;
import cat.itacademy.project.shared.domain.dtos.escape_room.CreateEscapeRoomDTO;
import cat.itacademy.project.shared.domain.dtos.escape_room.EscapeRoomDTO;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.Optional;

public class CreateEscapeRoomController {
    private final CreateEscapeRoomService service;

    public CreateEscapeRoomController() {

        EscapeRoomRepository repo = new EscapeRoomMySQLRepository(MySqlConnection.getInstance());
        this.service = new CreateEscapeRoomService(repo);
    }

    public Optional<EscapeRoomDTO> execute(CreateEscapeRoomDTO createEscapeRoomDTO) {

        return service.execute(createEscapeRoomDTO);
    }
}

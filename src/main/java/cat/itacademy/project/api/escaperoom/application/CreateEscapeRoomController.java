package cat.itacademy.project.api.escaperoom.application;

import cat.itacademy.project.business_logic.escaperoom.application.CreateEscapeRoomService;
import cat.itacademy.project.business_logic.escaperoom.domain.EscapeRoomRepository;
import cat.itacademy.project.business_logic.escaperoom.infrastructure.EscapeRoomMySQLRepository;
import cat.itacademy.project.shared.domain.dtos.escape_room.CreateEscapeRoomDTO;
import cat.itacademy.project.shared.domain.dtos.escape_room.EscapeRoomDTO;
import cat.itacademy.project.shared.domain.events.EventManager;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.Optional;

public class CreateEscapeRoomController {
    private final CreateEscapeRoomService service;
    private final EventManager eventManager;

    public CreateEscapeRoomController() {

        EscapeRoomRepository repo = new EscapeRoomMySQLRepository(MySqlConnection.getInstance());
        this.service = new CreateEscapeRoomService(repo);
        this.eventManager = EventManager.getInstance();
    }

    public Optional<EscapeRoomDTO> execute(CreateEscapeRoomDTO createEscapeRoomDTO) {
        Optional<EscapeRoomDTO> response = service.execute(createEscapeRoomDTO);
        eventManager.publish("escape_room.created", response.orElse(null));
        return response;
    }
}

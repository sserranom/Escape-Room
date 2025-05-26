package cat.itacademy.project.api.escaperoom.application;

import cat.itacademy.project.business_logic.escaperoom.application.UpdateEscapeRoomService;
import cat.itacademy.project.business_logic.escaperoom.domain.EscapeRoomRepository;
import cat.itacademy.project.business_logic.escaperoom.infrastructure.EscapeRoomMySQLRepository;
import cat.itacademy.project.shared.domain.dtos.escape_room.EscapeRoomDTO;
import cat.itacademy.project.shared.domain.dtos.escape_room.UpdateEscapeRoomDTO;
import cat.itacademy.project.shared.domain.events.EventManager;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.Optional;

public class UpdateEscapeRoomController {
    private final UpdateEscapeRoomService service;
    private final EventManager eventManager;

    public UpdateEscapeRoomController(EventManager eventManager) {
        EscapeRoomRepository repo = new EscapeRoomMySQLRepository(MySqlConnection.getInstance());
        this.service = new UpdateEscapeRoomService(repo);
        this.eventManager = eventManager;

    }

    public Optional<EscapeRoomDTO> execute(UpdateEscapeRoomDTO updateEscapeRoomDTO) {
        Optional<EscapeRoomDTO> result = service.execute(updateEscapeRoomDTO);
        eventManager.publish("escape_room.updated", result.orElse(null));
        return result;

    }
}

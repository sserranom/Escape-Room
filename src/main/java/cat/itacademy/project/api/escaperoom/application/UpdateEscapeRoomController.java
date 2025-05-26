package cat.itacademy.project.api.escaperoom.application;

import cat.itacademy.project.business_logic.escaperoom.application.UpdateEscapeRoomService;
import cat.itacademy.project.business_logic.escaperoom.domain.EscapeRoomRepository;
import cat.itacademy.project.business_logic.escaperoom.infrastructure.EscapeRoomMySQLRepository;
import cat.itacademy.project.shared.domain.dtos.escape_room.EscapeRoomDTO;
import cat.itacademy.project.shared.domain.dtos.escape_room.UpdateEscapeRoomDTO;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.Optional;

public class UpdateEscapeRoomController implements Command<EscapeRoomDTO> {
    private final UpdateEscapeRoomService service;

    public UpdateEscapeRoomController(UpdateEscapeRoomDTO updateEscapeRoomDTO) {
        EscapeRoomRepository repo = new EscapeRoomMySQLRepository(MySqlConnection.getInstance());
        this.service = new UpdateEscapeRoomService(updateEscapeRoomDTO, repo);

    }

    @Override
    public Optional<EscapeRoomDTO> execute() {


        return service.execute();

    }
}

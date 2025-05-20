package cat.itacademy.project.api.escaperoom.application;

import cat.itacademy.project.business_logic.escaperoom.application.UpdateEscapeRoomService;
import cat.itacademy.project.business_logic.escaperoom.domain.EscapeRoomRepository;
import cat.itacademy.project.business_logic.escaperoom.infrastructure.EscapeRoomMySQLRepository;
import cat.itacademy.project.shared.domain.Command;
import cat.itacademy.project.shared.domain.dtos.UpdateEscapeRoomDTO;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.Optional;

public class UpdateEscapeRoomController implements Command<Void> {
    private final UpdateEscapeRoomService service;
    public UpdateEscapeRoomController(UpdateEscapeRoomDTO updateEscapeRoomDTO) {
        EscapeRoomRepository repo = new EscapeRoomMySQLRepository(MySqlConnection.getInstance());
        this.service = new UpdateEscapeRoomService(updateEscapeRoomDTO, repo);

    }

    @Override
    public Optional<Void> execute() {

        service.execute();
        return Optional.empty();

    }
}

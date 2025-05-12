package cat.itacademy.project.api.escaperoom.application;

import cat.itacademy.project.buissness_logic.escaperoom.domain.EscapeRoomRepository;
import cat.itacademy.project.buissness_logic.escaperoom.infrastructure.EscapeRoomMySQLRepository;
import cat.itacademy.project.shared.domain.Command;
import cat.itacademy.project.shared.domain.dtos.CreateEscapeRoomDTO;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.Optional;

public class CreateEscapeRoomController implements Command<Void> {
    private final CreateEscapeRoomService service;

    public CreateEscapeRoomController(CreateEscapeRoomDTO createEscapeRoomDTO) {

        EscapeRoomRepository repo = new EscapeRoomMySQLRepository(MySqlConnection.getInstance());
        this.service = new CreateEscapeRoomService(createEscapeRoomDTO, repo);
    }

    public Optional<Void> execute() {

        service.execute();
        return Optional.empty();
    }
}

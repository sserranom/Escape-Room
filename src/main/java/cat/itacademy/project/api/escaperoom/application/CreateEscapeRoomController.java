package cat.itacademy.project.api.escaperoom.application;

import cat.itacademy.project.shared.domain.dtos.CreateEscapeRoomDTO;
import cat.itacademy.project.buissness_logic.escaperoom.domain.EscapeRoomRepository;
import cat.itacademy.project.buissness_logic.escaperoom.infrastructure.EscapeRoomMySQLRepository;
import cat.itacademy.project.frontend.shared.MenuCommand;

import java.util.Optional;

public class CreateEscapeRoomController extends MenuCommand<Void> {
    private final CreateEscapeRoomService service;

    public CreateEscapeRoomController(CreateEscapeRoomDTO createEscapeRoomDTO) {
        EscapeRoomRepository repo = new EscapeRoomMySQLRepository();
        this.service = new CreateEscapeRoomService(createEscapeRoomDTO, repo);
    }

    public Optional<Void> execute() {

        service.execute();
        return Optional.empty();
    }
}

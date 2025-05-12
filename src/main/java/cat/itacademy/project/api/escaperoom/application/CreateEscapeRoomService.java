package cat.itacademy.project.api.escaperoom.application;

import cat.itacademy.project.buissness_logic.escaperoom.application.CreateEscapeRoom;
import cat.itacademy.project.buissness_logic.escaperoom.domain.EscapeRoomRepository;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.shared.domain.dtos.CreateEscapeRoomDTO;

import java.util.Optional;

public class CreateEscapeRoomService extends MenuCommand<Void> {
    private final EscapeRoomRepository repo;
    private final CreateEscapeRoomDTO createEscapeRoomDTO;

    public CreateEscapeRoomService(CreateEscapeRoomDTO createEscapeRoomDTO, EscapeRoomRepository repository) {
        this.createEscapeRoomDTO = createEscapeRoomDTO;
        this.repo = repository;
    }

    @Override
    public Optional<Void> execute() {
        CreateEscapeRoom creator;
        creator = new CreateEscapeRoom(createEscapeRoomDTO, repo);
        creator.create();
        return Optional.empty();
    }
}

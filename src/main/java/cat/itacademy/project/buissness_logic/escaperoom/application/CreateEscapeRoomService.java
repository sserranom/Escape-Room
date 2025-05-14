package cat.itacademy.project.buissness_logic.escaperoom.application;

import cat.itacademy.project.buissness_logic.escaperoom.domain.EscapeRoom;
import cat.itacademy.project.buissness_logic.escaperoom.domain.EscapeRoomRepository;
import cat.itacademy.project.shared.domain.dtos.CreateEscapeRoomDTO;
import cat.itacademy.project.shared.domain.exceptions.AlreadyExistsException;

import java.util.Optional;

public final class CreateEscapeRoomService {
    private final EscapeRoom escapeRoom;
    private final EscapeRoomRepository repo;

    public CreateEscapeRoomService(CreateEscapeRoomDTO request, EscapeRoomRepository repo) {
        this.escapeRoom = EscapeRoom.create(request);
        this.repo = repo;
    }

    public void execute() {
        ensureDoesNotExist();
        repo.create(escapeRoom);
    }

    private void ensureDoesNotExist() throws AlreadyExistsException {
        Optional<EscapeRoom> existing = repo.findByName(escapeRoom.getName());
        if (existing.isPresent()) {
            throw new AlreadyExistsException("Escape Room '" + escapeRoom.getName() + "' already exist");
        }
    }

}

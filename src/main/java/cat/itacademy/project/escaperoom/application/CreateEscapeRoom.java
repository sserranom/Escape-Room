package cat.itacademy.project.escaperoom.application;

import cat.itacademy.project.escaperoom.domain.CreateEscapeRoomDTO;
import cat.itacademy.project.escaperoom.domain.EscapeRoom;
import cat.itacademy.project.escaperoom.infrastructure.EscapeRoomMySQLRepository;
import cat.itacademy.project.shared.domain.exceptions.AlreadyExistsException;

import java.util.Optional;

public final class CreateEscapeRoom {
    private final EscapeRoom escapeRoom;
    private final EscapeRoomMySQLRepository repo;

    public CreateEscapeRoom(CreateEscapeRoomDTO request, EscapeRoomMySQLRepository repo ) {
        this.escapeRoom = EscapeRoom.create(request);
        this.repo = repo;
    }

    public void create() throws AlreadyExistsException {
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

package cat.itacademy.project.business_logic.escaperoom.application;

import cat.itacademy.project.business_logic.escaperoom.domain.EscapeRoom;
import cat.itacademy.project.business_logic.escaperoom.domain.EscapeRoomRepository;
import cat.itacademy.project.shared.domain.dtos.escape_room.CreateEscapeRoomDTO;
import cat.itacademy.project.shared.domain.dtos.escape_room.EscapeRoomDTO;
import cat.itacademy.project.shared.domain.exceptions.AlreadyExistsException;
import cat.itacademy.project.shared.domain.exceptions.NotFoundException;

import java.util.Optional;

public final class CreateEscapeRoomService implements Command<EscapeRoomDTO> {
    private final EscapeRoom escapeRoom;
    private final EscapeRoomRepository repo;
//    private final EventManager events;

    public CreateEscapeRoomService(CreateEscapeRoomDTO request, EscapeRoomRepository repo) {
        this.escapeRoom = EscapeRoom.create(request);
        this.repo = repo;
    }

    public Optional<EscapeRoomDTO> execute() {
        ensureDoesNotExist();
        repo.create(new CreateEscapeRoomDTO(escapeRoom.getName(),escapeRoom.getUrl()));
        final EscapeRoom created = getEscapeRoom();
        return Optional.of(created.toDTO());
    }

    private EscapeRoom getEscapeRoom() {
        return repo.findByName(escapeRoom.getName())
                .orElseThrow(() -> new NotFoundException("Escape Room '" + escapeRoom.getName() + "' not found"));
    }

    private void ensureDoesNotExist() throws AlreadyExistsException {
        Optional<EscapeRoom> existing = repo.findByName(escapeRoom.getName());
        if (existing.isPresent()) {
            throw new AlreadyExistsException("Escape Room '" + escapeRoom.getName() + "' already exist");
        }
    }

}

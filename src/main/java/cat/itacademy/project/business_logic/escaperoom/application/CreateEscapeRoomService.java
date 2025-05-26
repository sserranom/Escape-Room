package cat.itacademy.project.business_logic.escaperoom.application;

import cat.itacademy.project.business_logic.escaperoom.domain.EscapeRoom;
import cat.itacademy.project.business_logic.escaperoom.domain.EscapeRoomRepository;
import cat.itacademy.project.shared.domain.dtos.escape_room.CreateEscapeRoomDTO;
import cat.itacademy.project.shared.domain.dtos.escape_room.EscapeRoomDTO;
import cat.itacademy.project.shared.domain.exceptions.AlreadyExistsException;
import cat.itacademy.project.shared.domain.exceptions.NotFoundException;

import java.util.Optional;

public final class CreateEscapeRoomService  {
    private  EscapeRoom escapeRoom;
    private final EscapeRoomRepository repo;

    public CreateEscapeRoomService( EscapeRoomRepository repo) {

        this.repo = repo;
    }

    public Optional<EscapeRoomDTO> execute(CreateEscapeRoomDTO request) {
        this.escapeRoom = EscapeRoom.create(request);
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

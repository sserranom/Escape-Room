package cat.itacademy.project.business_logic.room.application;

import cat.itacademy.project.business_logic.room.domain.Room;
import cat.itacademy.project.business_logic.room.domain.RoomRepository;
import cat.itacademy.project.shared.domain.dtos.room.CreateRoomDTO;
import cat.itacademy.project.shared.domain.dtos.room.RoomDTO;
import cat.itacademy.project.shared.domain.exceptions.AlreadyExistsException;

import java.util.Optional;

public class CreateRoomService {
    private final CreateRoomDTO room;
    private final RoomRepository repo;

    public CreateRoomService(CreateRoomDTO request, RoomRepository repo) {
        this.room = request;
        this.repo = repo;
    }

    public void execute() {
        ensureDoesNotExist();
        repo.create(room);
    }

    private void ensureDoesNotExist() throws AlreadyExistsException {
        Optional<RoomDTO> existing = repo.findByName(room.name());
        if (existing.isPresent()) {
            throw new AlreadyExistsException("Room " + room.name() + " already exist");
        }
    }
}

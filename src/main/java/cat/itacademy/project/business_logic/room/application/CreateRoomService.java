package cat.itacademy.project.business_logic.room.application;

import cat.itacademy.project.business_logic.room.domain.RoomRepository;
import cat.itacademy.project.shared.domain.dtos.room.CreateRoomDTO;
import cat.itacademy.project.shared.domain.dtos.room.RoomDTO;
import cat.itacademy.project.shared.domain.exceptions.AlreadyExistsException;

import java.util.Optional;

public class CreateRoomService {
    private final RoomRepository repo;

    public CreateRoomService(RoomRepository repo) {
        this.repo = repo;
    }

    public Optional<RoomDTO> execute(CreateRoomDTO createRoomDTO) {
        ensureDoesNotExist(name);
        repo.create(createRoomDTO);
        return Optional.empty();
    }

    private void ensureDoesNotExist(String name) throws AlreadyExistsException {
        Optional<RoomDTO> existing = repo.findByName(name);
        if (existing.isPresent()) {
            throw new AlreadyExistsException("Room " + name + " already exist");
        }
    }
}

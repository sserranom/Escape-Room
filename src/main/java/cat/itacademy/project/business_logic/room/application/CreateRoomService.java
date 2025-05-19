package cat.itacademy.project.business_logic.room.application;

import cat.itacademy.project.business_logic.room.domain.Room;
import cat.itacademy.project.business_logic.room.domain.RoomRepository;
import cat.itacademy.project.shared.domain.dtos.CreateRoomDTO;
import cat.itacademy.project.shared.domain.exceptions.AlreadyExistsException;

import java.util.Optional;

public class CreateRoomService {
    private final Room room;
    private final RoomRepository repo;

    public CreateRoomService(CreateRoomDTO request, RoomRepository repo) {
        this.room = Room.create(request);
        this.repo = repo;
    }

    public void execute(){
        ensureDoesNotExist();
        repo.create(room);
    }

    private void ensureDoesNotExist() throws AlreadyExistsException {
        Optional<Room> existing = repo.findByName(room.getName());
        if (existing.isPresent()){
            throw new AlreadyExistsException("Room " + room.getName() + " already exist");
        }
    }
}

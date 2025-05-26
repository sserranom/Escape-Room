package cat.itacademy.project.business_logic.room.application;

import cat.itacademy.project.business_logic.room.domain.Room;
import cat.itacademy.project.business_logic.room.domain.RoomRepository;
import cat.itacademy.project.shared.domain.dtos.room.CreateRoomDTO;
import cat.itacademy.project.shared.domain.dtos.room.RoomDTO;
import cat.itacademy.project.shared.domain.exceptions.AlreadyExistsException;

import java.util.Optional;

public class CreateRoomService {
    private final RoomRepository repo;
    private Room room;

    public CreateRoomService( RoomRepository repo){
        this.repo = repo;
    }

    public void execute(CreateRoomDTO createRoomDTO){
        this.room = Room.create(createRoomDTO);
        ensureDoesNotExist();
        repo.create(createRoomDTO);

    }

    private void ensureDoesNotExist(){
        Optional<RoomDTO> existing = repo.findByName(room.getName());
        if (existing.isPresent()){
            throw new AlreadyExistsException("Room " + room.getName() + " Already exist");
        }
    }

}

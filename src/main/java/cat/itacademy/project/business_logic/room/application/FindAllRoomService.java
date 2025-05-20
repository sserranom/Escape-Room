package cat.itacademy.project.business_logic.room.application;

import cat.itacademy.project.business_logic.room.domain.RoomRepository;
import cat.itacademy.project.shared.domain.dtos.room.RoomDTO;

import java.util.List;

public class FindAllRoomService {
    private final RoomRepository repo;

    public FindAllRoomService(RoomRepository repo) {
        this.repo = repo;
    }


    public List<RoomDTO> findAll() {
        return repo.findAll();
    }
}

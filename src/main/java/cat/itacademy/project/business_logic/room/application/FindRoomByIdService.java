package cat.itacademy.project.business_logic.room.application;

import cat.itacademy.project.business_logic.room.domain.RoomRepository;
import cat.itacademy.project.shared.domain.dtos.room.RoomDTO;

import java.util.Optional;

public class FindRoomByIdService  {
    private final RoomRepository repo;

    public FindRoomByIdService(RoomRepository repo) {
        this.repo = repo;
    }

    public Optional<RoomDTO> execute(int id) {
        return repo.findById(id);

    }
}

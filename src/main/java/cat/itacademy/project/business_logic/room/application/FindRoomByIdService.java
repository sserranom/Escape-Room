package cat.itacademy.project.business_logic.room.application;

import cat.itacademy.project.business_logic.room.domain.RoomRepository;
import cat.itacademy.project.shared.domain.dtos.room.RoomDTO;

import java.util.Optional;

public class FindRoomByIdService implements Command<RoomDTO> {
    private final RoomRepository repo;
    private final int idToFind;

    public FindRoomByIdService(int idToFind, RoomRepository repo) {
        this.repo = repo;
        this.idToFind = idToFind;
    }

    @Override
    public Optional<RoomDTO> execute() {
        return repo.findById(idToFind);

    }
}

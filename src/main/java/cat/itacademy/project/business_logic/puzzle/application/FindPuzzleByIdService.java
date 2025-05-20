package cat.itacademy.project.business_logic.room.application;

import cat.itacademy.project.business_logic.room.domain.Room;
import cat.itacademy.project.business_logic.room.domain.RoomRepository;
import cat.itacademy.project.shared.domain.Command;
import cat.itacademy.project.shared.domain.dtos.RoomDTO;

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
        Optional<Room> roomOptional = repo.findById(idToFind);
        return roomOptional.map(room -> new RoomDTO(
                room.getId(),
                room.getName(),
                room.getPrice(),
                room.getEscapeRoomId()
        ));
    }
}

package cat.itacademy.project.business_logic.room.application;

import cat.itacademy.project.business_logic.room.domain.Room;
import cat.itacademy.project.business_logic.room.domain.RoomRepository;
import cat.itacademy.project.shared.domain.dtos.RoomDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FindAllRoomService {
    private final RoomRepository repo;

    public FindAllRoomService(RoomRepository repo) {
        this.repo = repo;
    }

    public List<RoomDTO> findAll() {
        List<Room> rooms = repo.findAll();
        return rooms.stream()
                .map(room -> new RoomDTO(
                        room.getId(),
                        room.getName(),
                        room.getPrice(),
                        room.getEscapeRoomId()
                ))
                .collect(Collectors.toList());
    }

    public List<Room> findAllRaw() {
        return repo.findAll();
    }
}

package cat.itacademy.project.business_logic.room.domain;

import cat.itacademy.project.shared.domain.dtos.room.CreateRoomDTO;
import cat.itacademy.project.shared.domain.dtos.room.RoomDTO;

import java.util.List;
import java.util.Optional;

public interface RoomRepository {

    void create(CreateRoomDTO room);

    void update(Room room);

    Optional<Void> delete(int id);

    Optional<RoomDTO> findById(int id);

    List<RoomDTO> findAll();

    Optional<RoomDTO> findByName(String name);

    Optional<Room> findAllByThemerId(int escapeRoomId);
}

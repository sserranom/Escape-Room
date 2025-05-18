package cat.itacademy.project.business_logic.room.domain;

import java.util.List;
import java.util.Optional;

public interface RoomRepository {

    void create(Room room);

    void update(Room room);

    Optional<Object> delete(int id);

    Optional<Room> findById(int id);

    List<Room> findAll();

    Optional<Room> findByName(String name);

    Optional<Room> findAllByEscapeRoomId(int escapeRoomId);
}

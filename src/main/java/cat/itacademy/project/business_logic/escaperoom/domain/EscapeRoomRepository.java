package cat.itacademy.project.business_logic.escaperoom.domain;

import cat.itacademy.project.shared.domain.dtos.escape_room.CreateEscapeRoomDTO;
import cat.itacademy.project.shared.domain.dtos.escape_room.EscapeRoomDTO;

import java.util.List;
import java.util.Optional;

public interface EscapeRoomRepository {

    void create(CreateEscapeRoomDTO escapeRoom);

    void update(EscapeRoomDTO escapeRoom);

    Optional<Void> delete(int id);

    Optional<EscapeRoomDTO> findById(int id);

    List<EscapeRoomDTO> findAll();

    Optional<EscapeRoom> findByName(String name);
}

package cat.itacademy.project.business_logic.escaperoom.domain;

import cat.itacademy.project.shared.domain.dtos.EscapeRoomDTO;

import java.util.List;
import java.util.Optional;

public interface EscapeRoomRepository {
    void create(EscapeRoom escapeRoom);

    void update(EscapeRoom escapeRoom);

    Optional<Void> delete(int id);

    Optional<EscapeRoom> findById(int id);

    List<EscapeRoomDTO> findAll();

    Optional<EscapeRoom> findByName(String name);
}

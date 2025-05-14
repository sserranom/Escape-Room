package cat.itacademy.project.buissness_logic.escaperoom.domain;

import java.util.List;
import java.util.Optional;

public interface EscapeRoomRepository {
    void create(EscapeRoom escapeRoom);

    void update(EscapeRoom escapeRoom);

    Optional<Void> delete(int id);

    Optional<EscapeRoom> findById(int id);

    List<EscapeRoom> findAll();

    Optional<EscapeRoom> findByName(String name);
}

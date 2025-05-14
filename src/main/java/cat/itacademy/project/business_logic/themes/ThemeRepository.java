package cat.itacademy.project.business_logic.themes;


import java.util.List;
import java.util.Optional;

public interface ThemeRepository {
    void create(Theme escapeRoom);

    void update(Theme escapeRoom);

    void delete(int id);

    Optional<Theme> findById(int id);

    List<Theme> findAll();

    Optional<Theme> findByName(String name);
}

package cat.itacademy.project.business_logic.theme.domain;


import cat.itacademy.project.shared.domain.dtos.ThemeDTO;

import java.util.List;
import java.util.Optional;

public interface ThemeRepository {
    void create(Theme escapeRoom);

    void update(Theme escapeRoom);

    void delete(int id);

    Optional<ThemeDTO> findById(int id);

    List<Theme> findAll();

    Optional<Theme> findByName(String name);
}

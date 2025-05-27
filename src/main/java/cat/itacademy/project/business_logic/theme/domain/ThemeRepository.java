package cat.itacademy.project.business_logic.theme.domain;


import cat.itacademy.project.shared.domain.dtos.theme.CreateThemeDTO;
import cat.itacademy.project.shared.domain.dtos.theme.ThemeDTO;

import java.util.List;
import java.util.Optional;

public interface ThemeRepository {
    void create(CreateThemeDTO escapeRoom);

    void update(Theme escapeRoom);

    void delete(int id);

    Optional<ThemeDTO> findById(int id);

    List<ThemeDTO> findAll(int escapeRoomId);

    Optional<Theme> findByName(String name);
}

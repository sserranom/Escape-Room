package cat.itacademy.project.business_logic.theme.application;


import cat.itacademy.project.business_logic.theme.domain.ThemeRepository;
import cat.itacademy.project.shared.domain.dtos.theme.ThemeDTO;

import java.util.List;


public class FindAllThemesService {
    private final ThemeRepository repo;

    public FindAllThemesService(ThemeRepository repo) {
        this.repo = repo;
    }

    public List<ThemeDTO> findAll(int escapeRoomId) {
        return repo.findAll(escapeRoomId);
    }
}
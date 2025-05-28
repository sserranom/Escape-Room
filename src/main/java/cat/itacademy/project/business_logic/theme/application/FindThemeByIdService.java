package cat.itacademy.project.business_logic.theme.application;

import cat.itacademy.project.business_logic.theme.domain.ThemeRepository;
import cat.itacademy.project.shared.domain.dtos.theme.ThemeDTO;

import java.util.Optional;

public class FindThemeByIdService {
    private final ThemeRepository repo;

    public FindThemeByIdService(ThemeRepository repo) {
        this.repo = repo;
    }

    public Optional<ThemeDTO> execute(int idToFind) {
        return repo.findById(idToFind);

    }
}

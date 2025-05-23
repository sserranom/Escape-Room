package cat.itacademy.project.business_logic.themes;

import cat.itacademy.project.business_logic.theme.domain.ThemeRepository;
import cat.itacademy.project.shared.domain.dtos.ThemeDTO;
import cat.itacademy.project.shared.domain.exceptions.NotFoundException;

public class FindThemeByIdService {
    private final ThemeRepository repo;

    public FindThemeByIdService(ThemeRepository repo) {
        this.repo = repo;
    }

    public ThemeDTO find(int id) {
        ThemeDTO theme = repo.findById(id).orElse(null);
        if (theme == null) {
            throw new NotFoundException("Theme not found");
        }
        return theme;

    }
}

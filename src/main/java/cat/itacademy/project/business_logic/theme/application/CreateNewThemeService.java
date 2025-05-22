package cat.itacademy.project.business_logic.theme.application;

import cat.itacademy.project.business_logic.theme.domain.Theme;
import cat.itacademy.project.business_logic.theme.domain.ThemeRepository;
import cat.itacademy.project.shared.domain.dtos.theme.CreateThemeDTO;

public class CreateNewThemeService {
    private final ThemeRepository repo;
    private final Theme theme;

    public CreateNewThemeService(CreateThemeDTO request, ThemeRepository repo) {
        this.theme = Theme.create(request);
        this.repo = repo;
    }

    public void execute() {
        repo.create(theme);
    }


}

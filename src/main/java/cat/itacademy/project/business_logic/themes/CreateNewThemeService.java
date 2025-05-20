package cat.itacademy.project.business_logic.themes;

import cat.itacademy.project.shared.domain.dtos.CreateThemeDTO;

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

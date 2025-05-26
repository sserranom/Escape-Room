package cat.itacademy.project.business_logic.theme.application;

import cat.itacademy.project.business_logic.theme.domain.ThemeRepository;
import cat.itacademy.project.shared.domain.dtos.theme.CreateThemeDTO;

public class CreateNewThemeService {
    private final ThemeRepository repo;

    public CreateNewThemeService( ThemeRepository repo) {
        this.repo = repo;
    }

    public void execute(CreateThemeDTO request) {
        repo.create(request);
    }


}

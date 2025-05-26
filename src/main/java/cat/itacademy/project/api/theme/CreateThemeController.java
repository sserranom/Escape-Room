package cat.itacademy.project.api.theme;

import cat.itacademy.project.business_logic.theme.application.CreateNewThemeService;
import cat.itacademy.project.business_logic.theme.domain.ThemeRepository;
import cat.itacademy.project.business_logic.theme.infrastructure.ThemeMySQLRepository;
import cat.itacademy.project.shared.domain.dtos.theme.CreateThemeDTO;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.Optional;

public class CreateThemeController implements Command<Void> {
    private final CreateNewThemeService service;

    public CreateThemeController(CreateThemeDTO createThemeDTO) {

        ThemeRepository repo = new ThemeMySQLRepository(MySqlConnection.getInstance());
        this.service = new CreateNewThemeService(createThemeDTO, repo);
    }

    public Optional<Void> execute() {

        service.execute();
        return Optional.empty();
    }
}

package cat.itacademy.project.api.theme;

import cat.itacademy.project.business_logic.theme.application.CreateNewThemeService;
import cat.itacademy.project.business_logic.theme.domain.ThemeRepository;
import cat.itacademy.project.business_logic.theme.infrastructure.ThemeMySQLRepository;
import cat.itacademy.project.shared.domain.dtos.theme.CreateThemeDTO;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

public class CreateThemeController {
    private final CreateNewThemeService service;

    public CreateThemeController() {

        ThemeRepository repo = new ThemeMySQLRepository(MySqlConnection.getInstance());
        this.service = new CreateNewThemeService( repo);
    }

    public void execute(CreateThemeDTO createThemeDTO) {

        service.execute(createThemeDTO);
    }
}

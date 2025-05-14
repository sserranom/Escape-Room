package cat.itacademy.project.api.theme;

import cat.itacademy.project.buissness_logic.themes.CreateNewTheme;
import cat.itacademy.project.buissness_logic.themes.CreateThemeDTO;
import cat.itacademy.project.buissness_logic.themes.ThemeMySQLRepository;
import cat.itacademy.project.buissness_logic.themes.ThemeRepository;
import cat.itacademy.project.shared.domain.Command;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.Optional;

public class CreateThemeController implements Command<Void> {
    private final CreateNewTheme service;

    public CreateThemeController(CreateThemeDTO createThemeDTO) {

        ThemeRepository repo = new ThemeMySQLRepository(MySqlConnection.getInstance());
        this.service = new CreateNewTheme(createThemeDTO, repo);
    }

    public Optional<Void> execute() {
        service.execute();
        return Optional.empty();
    }
}

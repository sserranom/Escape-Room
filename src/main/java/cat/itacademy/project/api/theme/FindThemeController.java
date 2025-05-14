package cat.itacademy.project.api.theme;

import cat.itacademy.project.buissness_logic.themes.FindThemes;
import cat.itacademy.project.buissness_logic.themes.ThemeRepository;
import cat.itacademy.project.buissness_logic.themes.ThemeMySQLRepository;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.buissness_logic.themes.ThemeDTO;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.List;
import java.util.Optional;

public class FindThemeController extends MenuCommand<List<ThemeDTO>> {
    private final FindThemes service;

    public FindThemeController(FindThemes service) {
        this.service = service;
    }

    public FindThemeController() {
        ThemeRepository repo = new ThemeMySQLRepository(MySqlConnection.getInstance());
        this.service = new FindThemes(repo);
    }


    @Override
    public Optional<List<ThemeDTO>> execute() {
        List<ThemeDTO> themes= service.findAll();
        if (themes.isEmpty()) {
            info("No themes found.");
        } else {
            info("List of themes:");
            themes.forEach(theme -> log(theme.name() + " (" + theme.description() + ")" + " (" + theme.escapeRoomId() + ")"));
        }
        return Optional.of(themes);
    }
}

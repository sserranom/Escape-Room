package cat.itacademy.project.api.theme;

import cat.itacademy.project.business_logic.themes.FindAllThemesService;
import cat.itacademy.project.business_logic.themes.ThemeRepository;
import cat.itacademy.project.business_logic.themes.ThemeMySQLRepository;
import cat.itacademy.project.shared.domain.dtos.ThemeDTO;
import cat.itacademy.project.shared.domain.Command;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.List;
import java.util.Optional;

public class FindThemeController implements Command<List<ThemeDTO>> {
    private final FindAllThemesService service;

    public FindThemeController() {
        ThemeRepository repo = new ThemeMySQLRepository(MySqlConnection.getInstance());
        this.service = new FindAllThemesService(repo);
    }


    @Override
    public Optional<List<ThemeDTO>> execute() {

        List<ThemeDTO> themes= service.findAll();

        return Optional.of(themes);
    }
}

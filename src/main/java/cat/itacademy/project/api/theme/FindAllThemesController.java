package cat.itacademy.project.api.theme;

import cat.itacademy.project.business_logic.theme.application.FindAllThemesService;
import cat.itacademy.project.business_logic.theme.domain.ThemeRepository;
import cat.itacademy.project.business_logic.theme.infrastructure.ThemeMySQLRepository;
import cat.itacademy.project.shared.domain.dtos.theme.ThemeDTO;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.List;
import java.util.Optional;

public class FindAllThemesController  {
    private final FindAllThemesService service;

    public FindAllThemesController() {
        ThemeRepository repo = new ThemeMySQLRepository(MySqlConnection.getInstance());
        this.service = new FindAllThemesService(repo);
    }


    public Optional<List<ThemeDTO>> execute() {

        List<ThemeDTO> themes = service.findAll();

        return Optional.of(themes);
    }
}

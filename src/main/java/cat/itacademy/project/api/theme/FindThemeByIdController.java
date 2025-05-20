package cat.itacademy.project.api.theme;

import cat.itacademy.project.business_logic.theme.domain.ThemeRepository;
import cat.itacademy.project.business_logic.theme.infrastructure.ThemeMySQLRepository;
import cat.itacademy.project.business_logic.themes.FindThemeByIdService;
import cat.itacademy.project.shared.domain.dtos.ThemeDTO;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

public class FindThemeByIdController {
    private final FindThemeByIdService service;

    public FindThemeByIdController() {


        ThemeRepository repo = new ThemeMySQLRepository(MySqlConnection.getInstance());
        this.service = new FindThemeByIdService(repo);
    }

    public ThemeDTO find(int id) {
        return service.find(id);
    }
}

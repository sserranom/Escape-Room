package cat.itacademy.project.api.theme;

import cat.itacademy.project.business_logic.theme.application.UpdateThemeService;
import cat.itacademy.project.business_logic.theme.domain.ThemeRepository;
import cat.itacademy.project.business_logic.theme.infrastructure.ThemeMySQLRepository;
import cat.itacademy.project.shared.domain.dtos.theme.UpdateThemeDTO;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.Optional;

public class UpdateThemeController {
    private final UpdateThemeService service;

    public UpdateThemeController() {
        ThemeRepository repo = new ThemeMySQLRepository(MySqlConnection.getInstance());
        this.service = new UpdateThemeService(repo);
    }

    public Optional<Void> execute(UpdateThemeDTO request) {
        service.execute(request);
        return Optional.empty();
    }
}

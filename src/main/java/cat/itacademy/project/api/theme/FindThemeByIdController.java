package cat.itacademy.project.api.theme;

import cat.itacademy.project.business_logic.theme.application.FindThemeByIdService;
import cat.itacademy.project.business_logic.theme.domain.ThemeRepository;
import cat.itacademy.project.business_logic.theme.infrastructure.ThemeMySQLRepository;
import cat.itacademy.project.shared.domain.dtos.theme.ThemeDTO;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.Optional;

public class FindThemeByIdController {
    private final FindThemeByIdService service;

    public FindThemeByIdController() {
        ThemeRepository repo = new ThemeMySQLRepository(MySqlConnection.getInstance());
        this.service = new FindThemeByIdService( repo);
    }

    public Optional<Optional<ThemeDTO>> execute(int idToFind) {
        try {
            return Optional.ofNullable(service.execute( idToFind));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
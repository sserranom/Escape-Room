package cat.itacademy.project.api.theme;

import cat.itacademy.project.business_logic.theme.application.FindThemeByIdService;
import cat.itacademy.project.business_logic.theme.domain.ThemeRepository;
import cat.itacademy.project.business_logic.theme.infrastructure.ThemeMySQLRepository;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.shared.domain.dtos.theme.ThemeDTO;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.Optional;

public class FindThemeByIdController extends MenuCommand<Optional<ThemeDTO>>{
    private final FindThemeByIdService service;
    private final int idToFind;

    public FindThemeByIdController(int idToFind) {
        ThemeRepository repo = new ThemeMySQLRepository(MySqlConnection.getInstance());
        this.service = new FindThemeByIdService(idToFind, repo);
        this.idToFind = idToFind;
    }

    @Override
    public Optional<Optional<ThemeDTO>> execute() {
        try {
            return Optional.ofNullable(service.execute());
        } catch (Exception e) {
            error("An unexpected error ocurred: " + e.getMessage());
            return Optional.empty();
        }
    }
}
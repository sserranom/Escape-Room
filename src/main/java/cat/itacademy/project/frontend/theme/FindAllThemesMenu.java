package cat.itacademy.project.frontend.theme;

import cat.itacademy.project.api.theme.FindThemeController;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.shared.domain.dtos.ThemeDTO;

import java.util.List;
import java.util.Optional;

public class FindAllThemesMenu extends MenuCommand<Void> {

    @Override
    public Optional<Void> execute() {

        FindThemeController controller = new FindThemeController();
        Optional<List<ThemeDTO>> themes = controller.execute();
        if (themes.isEmpty()) {
            info("No themes found.");
        } else {
            info("List of themes:");
            themes.get().forEach(theme -> log(theme.name() + " (" + theme.description() + ")" + " (" + theme.escapeRoomId() + ")"));
        }
        return Optional.empty();
    }
}

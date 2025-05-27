package cat.itacademy.project.frontend.theme;

import cat.itacademy.project.api.theme.FindAllThemesController;
import cat.itacademy.project.frontend.Menu;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.shared.domain.dtos.theme.ThemeDTO;

import java.util.List;
import java.util.Optional;

public class FindAllThemesMenu extends MenuCommand<Void> {

    @Override
    public Optional<Void> execute() {

        FindAllThemesController controller = new FindAllThemesController();
        Optional<List<ThemeDTO>> result = controller.execute(Menu.activeRoom().id());
        if (result.get().isEmpty()) {
            info("No themes found.");
        } else {
            info("List of themes:");
            result.get().forEach(ThemePrinter::print);
        }
        return Optional.empty();
    }
}

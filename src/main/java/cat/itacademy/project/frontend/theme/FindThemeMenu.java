package cat.itacademy.project.frontend.theme;

import cat.itacademy.project.api.theme.FindThemeController;
import cat.itacademy.project.frontend.shared.MenuCommand;

import java.util.Optional;

public class FindThemeMenu extends MenuCommand<Void> {

    @Override
    public Optional<Void> execute() {

        FindThemeController controller = new FindThemeController();
        controller.execute();
        return Optional.empty();
    }
}

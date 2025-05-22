package cat.itacademy.project.frontend.theme;

import cat.itacademy.project.api.theme.FindThemeByIdController;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.frontend.shared.MenuScanner;
import cat.itacademy.project.shared.domain.dtos.theme.ThemeDTO;
import java.util.Optional;

public class FindThemeByIdMenu extends MenuCommand<Void> {
    @Override
    public Optional<Void> execute() {
        try {
            int idToFind = MenuScanner.readInt("Enter the ID of the puzzle to find: ");
            FindThemeByIdController controller = new FindThemeByIdController(idToFind);
            Optional<Optional <ThemeDTO>> themeDTO = controller.execute();

            if (themeDTO.isPresent()) {
                Optional<ThemeDTO> foundTheme = themeDTO.get();
                ThemePrinter.print(foundTheme.get());
            } else {
                error("Theme with ID " + idToFind + " not found.");
            }
        } catch (Exception e) {
            error("An unexpected error occurred: " + e.getMessage());
        }
        return Optional.empty();
    }
}

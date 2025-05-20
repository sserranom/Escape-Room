package cat.itacademy.project.frontend.theme;

import cat.itacademy.project.api.theme.FindThemeByIdController;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.frontend.shared.MenuScanner;
import cat.itacademy.project.shared.domain.dtos.ThemeDTO;

import java.util.Optional;

public class FindThemeByIdMenu extends MenuCommand<Void> {
    @Override
    public Optional<Void> execute() {
        try {
            int idToFind = MenuScanner.readInt("Enter the ID of the puzzle to find: ");
            FindThemeByIdController controller = new FindThemeByIdController(idToFind);
            Optional<Optional<ThemeDTO>> puzzleDTO = controller.execute();

            if (puzzleDTO.isPresent()) {
                Optional<ThemeDTO> foundTheme = puzzleDTO.get();
                info("Found puzzle:");
                info("ID: " + foundTheme.get().id());
                info("Name: " + foundTheme.get().name());
                info("Description: " + foundTheme.get().description());
                info("Escape room ID: " + foundTheme.get().escapeRoomId());
            } else {
                error("Theme with ID " + idToFind + " not found.");
            }

        } catch (Exception e) {
            error("An unexpected error occurred: " + e.getMessage());
        }
        return Optional.empty();
    }
}

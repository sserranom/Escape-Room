package cat.itacademy.project.frontend.theme;

import cat.itacademy.project.api.theme.FindThemeByIdController;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.frontend.shared.MenuScanner;
import cat.itacademy.project.shared.domain.dtos.ThemeDTO;

import java.util.Optional;

public class FindThemeByIdMenu extends MenuCommand<ThemeDTO> {
    @Override
    public Optional<ThemeDTO> execute() {
        try {
            int idToFind = MenuScanner.readInt("Enter the ID of the puzzle to find: ");
            FindThemeByIdController controller = new FindThemeByIdController();
             ThemeDTO themeDTO = controller.find(idToFind);

            System.out.println("Theme found: " + themeDTO.name() + " FALTA EL PRINTER!");
            return Optional.of(themeDTO);

        } catch (Exception e) {
            error("An unexpected error occurred: " + e.getMessage());
        }
        return Optional.empty();
    }
}

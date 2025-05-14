package cat.itacademy.project.frontend.theme;

import cat.itacademy.project.api.theme.CreateThemeController;
import cat.itacademy.project.buissness_logic.themes.CreateThemeDTO;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.frontend.shared.MenuScanner;
import cat.itacademy.project.shared.domain.exceptions.EmptyFieldException;

import java.util.Optional;

public class CreateThemeMenu extends MenuCommand<Void> {
    private Integer escaperoom_id;
    private String name;
    private String description;

    @Override
    public Optional<Void> execute() {
        CreateThemeDTO request = getUSerInfo();
        CreateThemeController controller = new CreateThemeController(request);
        controller.execute();
        return Optional.empty();
    }
    public CreateThemeDTO getUSerInfo() {


        while (escaperoom_id == null || name == null || description == null) {

            try {
                name = MenuScanner.readString("Enter the name of the theme: ");
                escaperoom_id = MenuScanner.readInt("Enter the id of the escape room: ");
                description = MenuScanner.readString("Enter the description of the theme: ");
            } catch (EmptyFieldException e) {
                error("Field cannot be empty");
            } catch (Exception e) {
                error("An unexpected error occurred: " + e.getMessage());
            }

        }


        return new CreateThemeDTO(name, description, escaperoom_id);
    }
}



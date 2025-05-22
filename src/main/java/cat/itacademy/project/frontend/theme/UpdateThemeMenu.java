package cat.itacademy.project.frontend.theme;

import cat.itacademy.project.api.theme.UpdateThemeController;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.frontend.shared.MenuScanner;
import cat.itacademy.project.shared.domain.dtos.theme.UpdateThemeDTO;

import java.util.Optional;

public class UpdateThemeMenu extends MenuCommand<Void> {
    @Override
    public Optional<Void> execute() {
        try {
            UpdateThemeDTO dto = getInfo();
            UpdateThemeController controller = new UpdateThemeController(dto);
            controller.execute();
            info("Theme with name '" + dto.nameToUpdate() + "' updated successfully.");

        }catch (IllegalArgumentException e){
            error("Error: " + e.getMessage());
        }catch (Exception e){
            error("An unexpected error occurred: " + e.getMessage());
        }
        return Optional.empty();
    }

    private UpdateThemeDTO getInfo(){
        String nameToUpdate = MenuScanner.readString("Enter the name of the theme to update: ");
        String newName = MenuScanner.readString("Enter the new name: ");
        String newDescription = MenuScanner.readString("Enter the new description: ");
        int newEscapeRoomId = MenuScanner.readInt("Enter the new ID of the escape room: ");

        return new UpdateThemeDTO(
                nameToUpdate,
                newName.isEmpty() ? null : newName,
                newDescription.isEmpty() ? null : newDescription,
                newEscapeRoomId <= 0 ? null : newEscapeRoomId);
    }
}

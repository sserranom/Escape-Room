package cat.itacademy.project.frontend.escaperoom;

import cat.itacademy.project.api.escaperoom.application.UpdateEscapeRoomController;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.frontend.shared.MenuScanner;
import cat.itacademy.project.shared.domain.dtos.UpdateEscapeRoomDTO;
import cat.itacademy.project.shared.domain.exceptions.NotFoundException;

import java.util.Optional;

public class UpdateEscapeRoomMenu extends MenuCommand<Void> {

    @Override
    public Optional<Void> execute() {
        try {
            String nameToUpdate = MenuScanner.readString("Enter the name of the escape room to update: ");
            String newName = MenuScanner.readString("Enter the new name: ");
            String newUrl = MenuScanner.readString("Enter the new URL: ");

            UpdateEscapeRoomDTO dto = new UpdateEscapeRoomDTO(nameToUpdate, newName.isEmpty() ? null : newName, newUrl.isEmpty() ? null : newUrl);
            UpdateEscapeRoomController controller = new UpdateEscapeRoomController(dto);
            controller.execute();
            info("Escape room with name '" + nameToUpdate + "' updated successfully.");

        }catch (IllegalArgumentException e){
            error("Error: " + e.getMessage());
        }catch (Exception e){
            error("An unexpected error occurred: " + e.getMessage());
        }
        return Optional.empty();
    }
}

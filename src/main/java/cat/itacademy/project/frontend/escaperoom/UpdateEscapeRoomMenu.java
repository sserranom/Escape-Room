package cat.itacademy.project.frontend.escaperoom;

import cat.itacademy.project.api.escaperoom.application.UpdateEscapeRoomController;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.frontend.shared.MenuScanner;
import cat.itacademy.project.shared.domain.dtos.escape_room.EscapeRoomDTO;
import cat.itacademy.project.shared.domain.dtos.escape_room.UpdateEscapeRoomDTO;

import java.util.Optional;

public class UpdateEscapeRoomMenu extends MenuCommand<EscapeRoomDTO> {
    private final EscapeRoomDTO escapeRoomDTO;

    public UpdateEscapeRoomMenu(EscapeRoomDTO escapeRoomDTO) {
        this.escapeRoomDTO = escapeRoomDTO;
    }


    @Override
    public Optional<EscapeRoomDTO> execute() {
        try {
            UpdateEscapeRoomDTO dto = getInfo();
            UpdateEscapeRoomController controller = new UpdateEscapeRoomController(dto);
            Optional<EscapeRoomDTO> updated = controller.execute();
            info("Escape room with name '" + dto.nameToUpdate() + "' updated successfully.");
            return updated;
        } catch (IllegalArgumentException e) {
            error("Error: " + e.getMessage());
        } catch (Exception e) {
            error("An unexpected error occurred: " + e.getMessage());
        }
        return Optional.empty();
    }

    private UpdateEscapeRoomDTO getInfo() {
        String nameToUpdate = escapeRoomDTO.name();
        String newName = MenuScanner.readString("Enter the new name: ");
        String newUrl = MenuScanner.readString("Enter the new URL: ");

        return new UpdateEscapeRoomDTO(nameToUpdate, newName.isEmpty() ? null : newName, newUrl.isEmpty() ? null : newUrl);

    }
}

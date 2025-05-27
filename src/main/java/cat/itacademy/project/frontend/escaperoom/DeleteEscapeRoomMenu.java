package cat.itacademy.project.frontend.escaperoom;

import cat.itacademy.project.api.escaperoom.application.DeleteEscapeRoomController;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.shared.domain.dtos.escape_room.EscapeRoomDTO;
import cat.itacademy.project.shared.domain.exceptions.CustomException;

import java.util.Optional;

public class DeleteEscapeRoomMenu extends MenuCommand<Void> {
    EscapeRoomDTO escapeRoomDTO;

    public DeleteEscapeRoomMenu(EscapeRoomDTO escapeRoomDTO) {
        this.escapeRoomDTO = escapeRoomDTO;
    }

    @Override
    public Optional<Void> execute() {
        try {
            DeleteEscapeRoomController controller = new DeleteEscapeRoomController();
            controller.execute(escapeRoomDTO.id());
        } catch (CustomException e) {
            error(e.getMessage());
        }

        return Optional.empty();
    }
}

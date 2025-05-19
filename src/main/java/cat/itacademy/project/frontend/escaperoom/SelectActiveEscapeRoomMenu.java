package cat.itacademy.project.frontend.escaperoom;

import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.frontend.shared.MenuScanner;
import cat.itacademy.project.shared.domain.dtos.EscapeRoomDTO;

import java.util.List;
import java.util.Optional;

public class SelectActiveEscapeRoomMenu extends MenuCommand<EscapeRoomDTO> {
    List<EscapeRoomDTO> escapeRoomDTOS;

    public SelectActiveEscapeRoomMenu(List<EscapeRoomDTO> escapeRoomDTOS) {
        this.escapeRoomDTOS = escapeRoomDTOS;
    }



    @Override
    public Optional<EscapeRoomDTO> execute() {
        int id = MenuScanner.readInt("Please enter the Id of the escape room: ");
        return escapeRoomDTOS.stream()
                .filter(escapeRoomDTO -> escapeRoomDTO.id() == id)
                .findFirst();
    }
}

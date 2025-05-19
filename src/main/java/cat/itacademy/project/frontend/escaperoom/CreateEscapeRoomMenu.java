package cat.itacademy.project.frontend.escaperoom;

import cat.itacademy.project.api.escaperoom.application.CreateEscapeRoomController;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.frontend.shared.MenuScanner;
import cat.itacademy.project.shared.domain.dtos.CreateEscapeRoomDTO;
import cat.itacademy.project.shared.domain.dtos.EscapeRoomDTO;
import cat.itacademy.project.shared.domain.exceptions.AlreadyExistsException;
import cat.itacademy.project.shared.domain.exceptions.EmptyFieldException;

import java.util.Optional;

public class CreateEscapeRoomMenu extends MenuCommand<EscapeRoomDTO> {
    private String name;
    private String url;


    @Override
    public Optional<EscapeRoomDTO> execute() {
        Optional<EscapeRoomDTO> created = Optional.empty();
        info("Creating a new escape room...");
        CreateEscapeRoomDTO dto = getUserInfo();
        try {
            CreateEscapeRoomController controller = new CreateEscapeRoomController(dto);
            created = controller.execute();
            info("Escape room created successfully.");
        } catch (AlreadyExistsException e) {
            error(e.getMessage());
        } catch (Exception e) {
            error("Error: " + e.getMessage());
        }
        return created;
    }

    private CreateEscapeRoomDTO getUserInfo() {
        while (name == null || url == null) {
            try {
                if (name == null) {
                    name = MenuScanner.readString("Enter the name of the escape room: ");

                }
                url = MenuScanner.readString("Enter the URL of the escape room: ");
            } catch (EmptyFieldException e) {
                error("Error: Name or URL cannot be empty.");
            }
        }
        return new CreateEscapeRoomDTO(name, url);
    }
}

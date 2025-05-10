package cat.itacademy.project.menu.escaperoom;

import cat.itacademy.project.escaperoom.domain.CreateEscapeRoomDTO;
import cat.itacademy.project.menu.shared.MenuCommand;
import cat.itacademy.project.menu.shared.MenuScanner;
import cat.itacademy.project.shared.domain.exceptions.AlreadyExistsException;
import cat.itacademy.project.shared.domain.exceptions.EmptyFieldException;

public class CreateController extends MenuCommand {
    private String name;
    private String url;


    @Override
    public void execute() {
        CreateEscapeRoomService service;
        CreateEscapeRoomDTO createEscapeRoomDTO;
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
        try {

        runService();
        info("Escape room created successfully.");
        } catch (AlreadyExistsException e) {
            error(e.getMessage());
        } catch (Exception e) {
            error("Error: " + e.getMessage());
        }
    }

    private void runService() {
        CreateEscapeRoomDTO createEscapeRoomDTO;
        CreateEscapeRoomService service;
        createEscapeRoomDTO = new CreateEscapeRoomDTO(name, url);
        service = new CreateEscapeRoomService(createEscapeRoomDTO);

        service.execute();
    }


}

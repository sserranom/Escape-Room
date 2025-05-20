package cat.itacademy.project.frontend.puzzle;

import cat.itacademy.project.api.room.CreateRoomController;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.frontend.shared.MenuScanner;
import cat.itacademy.project.shared.domain.dtos.CreateRoomDTO;
import cat.itacademy.project.shared.domain.exceptions.EmptyFieldException;

import java.util.Optional;

public class CreateRoomMenu extends MenuCommand<Void> {
    private String name;
    private int roomId;
    private int themeId;
    private String difficulty;
    private String answer;
    private String story;

    id(), dto.name(), dto.difficulty(), dto.roomID(), dto.answer(), dto.story(), dto.themeId(), dto.price
    @Override
    public Optional<Void> execute() {
        CreateRoomDTO dto = getUserInfo();

        CreateRoomController controller = new CreateRoomController(dto);
        controller.execute();
        info("Puzzle created successfully.");
        return Optional.empty();
    }

    private CreateRoomDTO getUserInfo() {

        while (name == null || price <= 0 || escapeRoomId <= 0) {
            try {
                name = MenuScanner.readString("Enter the name of the room: ");
                price = MenuScanner.readDouble("Enter the price of the room: ");
                escapeRoomId = MenuScanner.readInt("Enter the id of the escape room: ");
            } catch (EmptyFieldException e) {
                error("Error: Name or price cannot be empty.");
            } catch (Exception e) {
                error("Invalid input. price must be a number" + e.getMessage());
            }
        }
        return new CreateRoomDTO(name, price, escapeRoomId);
    }
}

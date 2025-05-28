package cat.itacademy.project.frontend.Room;

import cat.itacademy.project.api.room.CreateRoomController;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.frontend.shared.MenuScanner;
import cat.itacademy.project.shared.domain.dtos.room.CreateRoomDTO;
import cat.itacademy.project.shared.domain.exceptions.EmptyFieldException;
import cat.itacademy.project.shared.domain.exceptions.InvalidDificultyException;

import java.util.Optional;

public class CreateRoomMenu extends MenuCommand<Void> {
    private String name;
    private String difficulty;
    private double price;
    private int theme_id;

    @Override
    public Optional<Void> execute() {
        CreateRoomDTO dto = getUserInfo();

        CreateRoomController controller = new CreateRoomController();
        controller.execute(dto);
        info("Room created successfully.");
        return Optional.empty();
    }

    private CreateRoomDTO getUserInfo() {

        while (name == null || price <= 0 || theme_id <= 0) {
            try {
                name = MenuScanner.readString("Enter the name of the room: ");
                price = MenuScanner.readDouble("Enter the price of the room: ");
                theme_id = MenuScanner.readInt("Enter the id of the Theme : ");
                int level = MenuScanner.readInt("Please select difficulty: 1 - Easy, 2 - Medium, 3 - Hard: ");
                switch (level) {
                    case 1 -> difficulty = "easy";
                    case 2 -> difficulty = "medium";
                    case 3 -> difficulty = "hard";
                    default -> error("Invalid difficulty level. Please select 1, 2, or 3.");

                }
            } catch (EmptyFieldException e) {
                error("Error: Name or price cannot be empty.");
            } catch (InvalidDificultyException e) {
                error(e.getMessage());
            }
        }
        return new CreateRoomDTO(name, price, difficulty, theme_id);
    }
}

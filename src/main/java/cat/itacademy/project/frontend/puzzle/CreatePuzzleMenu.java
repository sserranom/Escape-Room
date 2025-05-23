package cat.itacademy.project.frontend.puzzle;

import cat.itacademy.project.api.puzzle.CreatePuzzleController;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.frontend.shared.MenuScanner;
import cat.itacademy.project.shared.domain.dtos.puzzle.CreatePuzzleDTO;
import cat.itacademy.project.shared.domain.exceptions.EmptyFieldException;

import java.util.Optional;

public class CreatePuzzleMenu extends MenuCommand<Void> {
    private String name;
    private String difficulty;
    private int roomId;
    private String answer;
    private String story;
    private int themeId;
    private double price;


    @Override
    public Optional<Void> execute() {
        CreatePuzzleDTO dto = getUserInfo();

        CreatePuzzleController controller = new CreatePuzzleController(dto);
        controller.execute();
        info("Puzzle created successfully.");
        return Optional.empty();
    }

    private CreatePuzzleDTO getUserInfo() {

        while (name == null || difficulty ==null || roomId <= 0 || answer == null || story == null || themeId <= 0 || price <= 0) {
            try {
                name = MenuScanner.readString("Enter the name of the puzzle: ");
                difficulty = MenuScanner.readString("Enter the difficulty of the puzzle: ");
                roomId = MenuScanner.readInt("Enter the id of the puzzle: ");
                answer = MenuScanner.readString("Enter the answer of the puzzle: ");
                story = MenuScanner.readString("Enter the story of the puzzle: ");
                themeId = MenuScanner.readInt("Enter the theme id of the puzzle: ");
                price = MenuScanner.readDouble("Enter the price of the puzzle: ");

            } catch (EmptyFieldException e) {
                error("Error: They cannot be empty.");
            } catch (Exception e) {
                error("Invalid input. It must be a number" + e.getMessage());
            }
        }
        return new CreatePuzzleDTO(name, difficulty, roomId, answer, story, themeId, price);
    }
}

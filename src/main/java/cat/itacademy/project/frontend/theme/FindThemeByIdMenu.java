package cat.itacademy.project.frontend.puzzle;

import cat.itacademy.project.api.puzzle.FindPuzzleByIdController;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.frontend.shared.MenuScanner;
import cat.itacademy.project.shared.domain.dtos.PuzzleDTO;

import java.util.Optional;

public class FindPuzzleByIdMenu extends MenuCommand<Void> {
    @Override
    public Optional<Void> execute() {
        try {
            int idToFind = MenuScanner.readInt("Enter the ID of the puzzle to find: ");
            FindPuzzleByIdController controller = new FindPuzzleByIdController(idToFind);
            Optional<Optional<PuzzleDTO>> puzzleDTO = controller.execute();

            if (puzzleDTO.isPresent()) {
                Optional<PuzzleDTO> foundPuzzle = puzzleDTO.get();
                info("Found puzzle:");
                info("ID: " + foundPuzzle.get().id());
                info("Name: " + foundPuzzle.get().name());
                info("Difficulty: " + foundPuzzle.get().difficulty());
                info("Room ID: " + foundPuzzle.get().roomId());
                info("Answer: " + foundPuzzle.get().answer());
                info("Story: " + foundPuzzle.get().story());
                info("Theme ID: " + foundPuzzle.get().themeId());
                info("Price: " + foundPuzzle.get().price());
            } else {
                error("Puzzle with ID " + idToFind + " not found.");
            }

        } catch (Exception e) {
            error("An unexpected error occurred: " + e.getMessage());
        }
        return Optional.empty();
    }
}

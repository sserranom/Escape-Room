package cat.itacademy.project.frontend.puzzle;

import cat.itacademy.project.api.puzzle.FindPuzzleByIdController;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.frontend.shared.MenuScanner;
import cat.itacademy.project.frontend.theme.ThemePrinter;
import cat.itacademy.project.shared.domain.dtos.puzzle.PuzzleDTO;
import cat.itacademy.project.shared.domain.dtos.theme.ThemeDTO;

import java.util.Optional;

public class FindPuzzleByIdMenu extends MenuCommand<Void> {
    @Override
    public Optional<Void> execute() {
        try {
            int idToFind = MenuScanner.readInt("Enter the ID of the puzzle to find: ");
            FindPuzzleByIdController controller = new FindPuzzleByIdController();
            Optional<Optional<PuzzleDTO>> puzzleDTO = controller.execute(idToFind);

            if (puzzleDTO.isPresent()) {
                Optional<PuzzleDTO> foundPuzzle = puzzleDTO.get();
                PuzzlePrinter.print(foundPuzzle.get());
            } else {
                error("Puzzle with ID " + idToFind + " not found.");
            }

        } catch (Exception e) {
            error("An unexpected error occurred: " + e.getMessage());
        }
        return Optional.empty();
    }
}

package cat.itacademy.project.frontend.puzzle;

import cat.itacademy.project.api.puzzle.FindAllPuzzlesController;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.shared.domain.dtos.puzzle.PuzzleDTO;

import java.util.List;
import java.util.Optional;

public class FindAllPuzzlesMenu extends MenuCommand<Void> {
    @Override
    public Optional<Void> execute() {
        FindAllPuzzlesController controller = new FindAllPuzzlesController();
        Optional<List<PuzzleDTO>> result = controller.execute();

        if (result.get().isEmpty()) {
            info("No puzzles found.");
        } else {
            info("List of puzzles:");
            result.get().forEach(PuzzlePrinter::print);
        }
        return Optional.empty();
    }
}

package cat.itacademy.project.frontend.puzzle;

import cat.itacademy.project.api.puzzle.FindAllPuzzlesController;
import cat.itacademy.project.api.puzzle.FindAllPuzzlesController;
import cat.itacademy.project.business_logic.puzzle.domain.Puzzle;
import cat.itacademy.project.frontend.shared.MenuCommand;

import java.util.List;
import java.util.Optional;

public class FindAllPuzzleMenu extends MenuCommand<Void> {
    @Override
    public Optional<Void> execute() {
        FindAllPuzzlesController controller = new FindAllPuzzlesController();
        Optional<List<Puzzle>> result = controller.execute();

        if (result.isPresent()){
            List<Puzzle> puzzles = result.get();
            if (puzzles.isEmpty()){
                info("No puzzles found");
            }
        }
        return Optional.empty();
    }
}

package cat.itacademy.project.frontend.puzzle;

import cat.itacademy.project.api.puzzle.FindAllPuzzlesController;
import cat.itacademy.project.business_logic.puzzle.domain.Puzzle;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.shared.domain.dtos.PuzzleDTO;

import java.util.List;
import java.util.Optional;

public class FindAllPuzzlesMenu extends MenuCommand<Void> {
    @Override
    public Optional<Void> execute() {
        FindAllPuzzlesController controller = new FindAllPuzzlesController();
        Optional<List<PuzzleDTO>> result = controller.execute();

        if (result.isPresent()){
            List<PuzzleDTO> puzzles = result.get();
            if (puzzles.isEmpty()){
                info("No puzzles found");
            }
        }
        return Optional.empty();
    }
}

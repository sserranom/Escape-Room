package cat.itacademy.project.frontend.puzzle;

import cat.itacademy.project.api.puzzle.CreatePuzzleController;
import cat.itacademy.project.api.puzzle.UpdatePuzzleController;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.shared.domain.dtos.puzzle.CreatePuzzleDTO;
import cat.itacademy.project.shared.domain.dtos.puzzle.UpdatePuzzleDTO;

import java.util.Optional;

public class ManagePuzzleMenu extends MenuCommand<Void> {
    @Override
    public Optional<Void> execute() {
        log("Manage puzzles menu is not yet implemented.");
        CreatePuzzleDTO createPuzzleDTO = new CreatePuzzleDTO("nombre", "nombre", 1, "1", "sdfjdhfkjdsf", 1, 1123);
        CreatePuzzleController createPuzzleController = new CreatePuzzleController();
        createPuzzleController.execute(createPuzzleDTO);
        UpdatePuzzleController updatePuzzleController = new UpdatePuzzleController();
        UpdatePuzzleDTO dto = new UpdatePuzzleDTO("nombre", "nombre", "hard", 1, "sdfjdhfkjdsf", "1", 1, 12332);
        updatePuzzleController.execute(dto);
        // Here you would implement the logic to manage puzzles.
        return Optional.empty();
    }
}

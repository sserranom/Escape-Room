package cat.itacademy.project.frontend.puzzle;

import cat.itacademy.project.api.puzzle.DeletePuzzleController;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.frontend.shared.MenuScanner;
import cat.itacademy.project.shared.domain.exceptions.CustomException;

import java.util.Optional;

public class DeletePuzzleMenu extends MenuCommand<Void> {

    @Override
    public Optional<Void> execute() {
        try {
            int idToDelete = MenuScanner.readInt("Enter the ID of the puzzle to delete: ");
            DeletePuzzleController controller = new DeletePuzzleController(idToDelete);
            controller.execute();
        } catch (CustomException e) {
            error(e.getMessage());
        }
        return Optional.empty();
    }
}

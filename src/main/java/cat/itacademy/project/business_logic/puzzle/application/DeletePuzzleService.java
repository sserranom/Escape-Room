package cat.itacademy.project.business_logic.puzzle.application;

import cat.itacademy.project.business_logic.puzzle.domain.PuzzleRepository;
import cat.itacademy.project.frontend.shared.MenuCommand;

import java.util.Optional;

public class DeletePuzzleService extends MenuCommand<Boolean> {
    private final PuzzleRepository repo;
    private final int idToDelete;

    public DeletePuzzleService(int idToDelete, PuzzleRepository repo) {
        this.repo = repo;
        this.idToDelete = idToDelete;
    }

    @Override
    public Optional<Boolean> execute() {
        Optional<Void> result = repo.delete(idToDelete);
        return Optional.of(!result.isEmpty());
    }
}

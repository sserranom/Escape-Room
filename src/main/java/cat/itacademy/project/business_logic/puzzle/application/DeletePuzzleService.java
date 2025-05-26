package cat.itacademy.project.business_logic.puzzle.application;

import cat.itacademy.project.business_logic.puzzle.domain.PuzzleRepository;


public class DeletePuzzleService  {
    private final PuzzleRepository repo;

    public DeletePuzzleService(PuzzleRepository repo) {
        this.repo = repo;
    }

    public void execute(int idToDelete) {
         repo.delete(idToDelete);
    }
}

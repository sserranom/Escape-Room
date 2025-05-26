package cat.itacademy.project.business_logic.puzzle.application;

import cat.itacademy.project.business_logic.puzzle.domain.PuzzleRepository;
import cat.itacademy.project.shared.domain.dtos.puzzle.PuzzleDTO;

import java.util.Optional;

public class FindPuzzleByIdService  {
    private final PuzzleRepository repo;

    public FindPuzzleByIdService(PuzzleRepository repo) {
        this.repo = repo;
    }

    public Optional<PuzzleDTO> execute(int idToFind) {
        return repo.findById(idToFind);
    }
}

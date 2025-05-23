package cat.itacademy.project.business_logic.puzzle.application;

import cat.itacademy.project.business_logic.puzzle.domain.PuzzleRepository;
import cat.itacademy.project.shared.domain.Command;
import cat.itacademy.project.shared.domain.dtos.puzzle.PuzzleDTO;

import java.util.Optional;

public class FindPuzzleByIdService implements Command<PuzzleDTO> {
    private final PuzzleRepository repo;
    private final int idToFind;

    public FindPuzzleByIdService(int idToFind, PuzzleRepository repo) {
        this.repo = repo;
        this.idToFind = idToFind;
    }

    @Override
    public Optional<PuzzleDTO> execute() {
        return repo.findById(idToFind);
    }
}

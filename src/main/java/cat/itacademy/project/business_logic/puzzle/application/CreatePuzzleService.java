package cat.itacademy.project.business_logic.puzzle.application;

import cat.itacademy.project.business_logic.puzzle.domain.Puzzle;
import cat.itacademy.project.business_logic.puzzle.domain.PuzzleRepository;
import cat.itacademy.project.shared.domain.dtos.puzzle.CreatePuzzleDTO;
import cat.itacademy.project.shared.domain.dtos.puzzle.PuzzleDTO;
import cat.itacademy.project.shared.domain.exceptions.AlreadyExistsException;
import cat.itacademy.project.shared.domain.exceptions.NotFoundException;

import java.util.Optional;

public final class CreatePuzzleService {
    private final PuzzleRepository repo;

    public CreatePuzzleService(PuzzleRepository repo) {

        this.repo = repo;
    }

    public Optional<PuzzleDTO> execute(CreatePuzzleDTO request) {
        Puzzle puzzle = Puzzle.create(request);
        ensureDoesNotExist(request.name());
        repo.create(puzzle);
        final PuzzleDTO created = getPuzzle(puzzle.getName());
        return Optional.of(created);
    }

    private PuzzleDTO getPuzzle(String name) {
        return repo.findByName(name)
                .orElseThrow(() -> new NotFoundException("Puzzle '" + name + "' not found"));
    }

    private void ensureDoesNotExist(String name) throws AlreadyExistsException {
        Optional<PuzzleDTO> existing = repo.findByName(name);
        if (existing.isPresent()) {
            throw new AlreadyExistsException("Puzzle '" + name + "' already exist");
        }
    }

}

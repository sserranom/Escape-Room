package cat.itacademy.project.business_logic.puzzle.application;

import cat.itacademy.project.business_logic.puzzle.domain.Puzzle;
import cat.itacademy.project.business_logic.puzzle.domain.PuzzleRepository;
import cat.itacademy.project.shared.domain.Command;
import cat.itacademy.project.shared.domain.dtos.puzzle.CreatePuzzleDTO;
import cat.itacademy.project.shared.domain.dtos.puzzle.PuzzleDTO;
import cat.itacademy.project.shared.domain.exceptions.AlreadyExistsException;
import cat.itacademy.project.shared.domain.exceptions.NotFoundException;

import java.util.Optional;

public final class CreatePuzzleService implements Command<PuzzleDTO> {
    private final Puzzle puzzle;
    private final PuzzleRepository repo;

    public CreatePuzzleService(CreatePuzzleDTO request, PuzzleRepository repo) {
        this.puzzle = Puzzle.create(request);
        this.repo = repo;
    }

    public Optional<PuzzleDTO> execute() {
        ensureDoesNotExist();
        repo.create(puzzle);
        final PuzzleDTO created = getPuzzle();
        return Optional.of(created);
    }

    private PuzzleDTO getPuzzle() {
        return repo.findByName(puzzle.getName())
                .orElseThrow(() -> new NotFoundException("Puzzle '" + puzzle.getName() + "' not found"));
    }

    private void ensureDoesNotExist() throws AlreadyExistsException {
        Optional<PuzzleDTO> existing = repo.findByName(puzzle.getName());
        if (existing.isPresent()) {
            throw new AlreadyExistsException("Puzzle '" + puzzle.getName() + "' already exist");
        }
    }

}

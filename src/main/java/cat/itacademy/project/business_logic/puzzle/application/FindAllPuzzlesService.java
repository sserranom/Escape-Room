package cat.itacademy.project.business_logic.puzzle.application;

import cat.itacademy.project.business_logic.puzzle.domain.Puzzle;
import cat.itacademy.project.business_logic.puzzle.domain.PuzzleRepository;
import cat.itacademy.project.business_logic.puzzle.domain.PuzzleDTO;

import java.util.List;
import java.util.stream.Collectors;

public class FindAllPuzzleService {
    private final PuzzleRepository repo;

    public FindAllPuzzleService(PuzzleRepository repo) {
        this.repo = repo;
    }

    public List<PuzzleDTO> findAll() {
        List<Puzzle> puzzles = repo.findAll();
        return puzzles.stream()
                .map(puzzle -> new Puzzle()DTO(
                        puzzles.getId(),
                        puzzles.getName(),
                        puzzles.getPrice(),
                        puzzles.getEscapePuzzleId()
                ))
                .collect(Collectors.toList());
    }

    public List<Puzzle> findAllRaw() {
        return repo.findAll();
    }
}

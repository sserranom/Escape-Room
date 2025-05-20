package cat.itacademy.project.business_logic.puzzle.application;

import cat.itacademy.project.business_logic.puzzle.domain.Puzzle;
import cat.itacademy.project.business_logic.puzzle.domain.PuzzleRepository;
import cat.itacademy.project.shared.domain.dtos.PuzzleDTO;

import java.util.List;
import java.util.stream.Collectors;

public class FindAllPuzzlesService {
    private final PuzzleRepository repo;

    public FindAllPuzzlesService(PuzzleRepository repo) {
        this.repo = repo;
    }

    public List<PuzzleDTO> findAll() {
        List<Puzzle> puzzles = repo.findAll();
        return puzzles.stream()
                .map(puzzle -> new PuzzleDTO(
                        puzzle.getId(),
                        puzzle.getName(),
                        puzzle.getDifficulty(),
                        puzzle.getRoomId(),
                        puzzle.getAnswer(),
                        puzzle.getStory(),
                        puzzle.getThemeId(),
                        puzzle.getPrice()
                ))
                .collect(Collectors.toList());
    }



    public List<Puzzle> findAllRaw() {
        return repo.findAll();
    }
}

package cat.itacademy.project.business_logic.puzzle.application;

import cat.itacademy.project.business_logic.puzzle.domain.PuzzleRepository;
import cat.itacademy.project.shared.domain.dtos.puzzle.PuzzleDTO;

import java.util.List;

public class FindAllPuzzlesService {
    private final PuzzleRepository repo;

    public FindAllPuzzlesService(PuzzleRepository repo) {
        this.repo = repo;
    }


    public List<PuzzleDTO> findAll() {
        return repo.findAll();
    }
}

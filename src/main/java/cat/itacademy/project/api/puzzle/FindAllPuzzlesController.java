package cat.itacademy.project.api.puzzle;

import cat.itacademy.project.business_logic.puzzle.application.FindAllPuzzlesService;
import cat.itacademy.project.business_logic.puzzle.domain.PuzzleRepository;
import cat.itacademy.project.business_logic.puzzle.infrastructure.PuzzleMySQLRepository;
import cat.itacademy.project.shared.domain.dtos.puzzle.PuzzleDTO;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.List;
import java.util.Optional;

public class FindAllPuzzlesController {
    private final FindAllPuzzlesService service;


    public FindAllPuzzlesController() {
        PuzzleRepository repo = new PuzzleMySQLRepository(MySqlConnection.getInstance());
        this.service = new FindAllPuzzlesService(repo);
    }


    public Optional<List<PuzzleDTO>> execute() {
        List<PuzzleDTO> puzzle = service.findAll();

        return Optional.of(puzzle);
    }
}

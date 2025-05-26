package cat.itacademy.project.api.puzzle;

import cat.itacademy.project.business_logic.puzzle.application.CreatePuzzleService;
import cat.itacademy.project.business_logic.puzzle.domain.PuzzleRepository;
import cat.itacademy.project.business_logic.puzzle.infrastructure.PuzzleMySQLRepository;
import cat.itacademy.project.shared.domain.dtos.puzzle.CreatePuzzleDTO;
import cat.itacademy.project.shared.domain.dtos.puzzle.PuzzleDTO;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.Optional;

public class CreatePuzzleController implements Command<PuzzleDTO> {
    private final CreatePuzzleService service;

    public CreatePuzzleController(CreatePuzzleDTO createPuzzleDTO) {

        PuzzleRepository repo = new PuzzleMySQLRepository(MySqlConnection.getInstance());
        this.service = new CreatePuzzleService(createPuzzleDTO, repo);
    }

    public Optional<PuzzleDTO> execute() {

        service.execute();
        return Optional.empty();
    }
}

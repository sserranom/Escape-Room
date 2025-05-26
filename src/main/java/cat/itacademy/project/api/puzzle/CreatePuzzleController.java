package cat.itacademy.project.api.puzzle;

import cat.itacademy.project.business_logic.puzzle.application.CreatePuzzleService;
import cat.itacademy.project.business_logic.puzzle.domain.PuzzleRepository;
import cat.itacademy.project.business_logic.puzzle.infrastructure.PuzzleMySQLRepository;
import cat.itacademy.project.shared.domain.dtos.puzzle.CreatePuzzleDTO;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

public class CreatePuzzleController  {
    private final CreatePuzzleService service;

    public CreatePuzzleController() {

        PuzzleRepository repo = new PuzzleMySQLRepository(MySqlConnection.getInstance());
        this.service = new CreatePuzzleService(repo);
    }

    public void execute(CreatePuzzleDTO dto) {

        service.execute(dto);

    }
}

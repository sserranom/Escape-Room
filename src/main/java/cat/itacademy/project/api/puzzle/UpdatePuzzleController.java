package cat.itacademy.project.api.puzzle;

import cat.itacademy.project.business_logic.puzzle.application.UpdatePuzzleService;
import cat.itacademy.project.business_logic.puzzle.domain.PuzzleRepository;
import cat.itacademy.project.business_logic.puzzle.infrastructure.PuzzleMySQLRepository;
import cat.itacademy.project.shared.domain.dtos.puzzle.UpdatePuzzleDTO;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

public class UpdatePuzzleController {
    private final UpdatePuzzleService service;

    public UpdatePuzzleController() {
        PuzzleRepository repo = new PuzzleMySQLRepository(MySqlConnection.getInstance());
        this.service = new UpdatePuzzleService(repo);

    }

    public void execute(UpdatePuzzleDTO updatePuzzleDTO) {

        service.execute(updatePuzzleDTO);


    }
}

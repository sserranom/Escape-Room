package cat.itacademy.project.api.puzzle;

import cat.itacademy.project.business_logic.puzzle.application.DeletePuzzleService;
import cat.itacademy.project.business_logic.puzzle.domain.PuzzleRepository;
import cat.itacademy.project.business_logic.puzzle.infrastructure.PuzzleMySQLRepository;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.Optional;

public class DeletePuzzleController  {
    private final DeletePuzzleService service;

    public DeletePuzzleController() {
        PuzzleRepository repo = new PuzzleMySQLRepository(MySqlConnection.getInstance());
        this.service = new DeletePuzzleService( repo);
    }

    public void execute(int id) {
            service.execute(id);



    }
}

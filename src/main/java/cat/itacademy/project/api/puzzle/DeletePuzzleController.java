package cat.itacademy.project.api.puzzle;

import cat.itacademy.project.business_logic.puzzle.application.DeletePuzzleService;
import cat.itacademy.project.business_logic.puzzle.domain.PuzzleRepository;
import cat.itacademy.project.business_logic.puzzle.infrastructure.PuzzleMySQLRepository;
import cat.itacademy.project.shared.domain.Command;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.Optional;

public class DeletePuzzleController implements Command<Void> {
    private final DeletePuzzleService service;
    private final int idToDelete;

    public DeletePuzzleController(int idToDelete) {
        PuzzleRepository repo = new PuzzleMySQLRepository(MySqlConnection.getInstance());
        this.service = new DeletePuzzleService(idToDelete, repo);
        this.idToDelete = idToDelete;
    }

    @Override
    public Optional<Void> execute() {
            service.execute();
            return Optional.empty();


    }
}

package cat.itacademy.project.api.puzzle;

import cat.itacademy.project.business_logic.puzzle.application.UpdatePuzzleService;
import cat.itacademy.project.business_logic.puzzle.domain.PuzzleRepository;
import cat.itacademy.project.business_logic.puzzle.infrastructure.PuzzleMySQLRepository;
import cat.itacademy.project.shared.domain.Command;
import cat.itacademy.project.shared.domain.dtos.UpdatePuzzleDTO;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.Optional;

public class UpdatePuzzleController implements Command<Void> {
    private final UpdatePuzzleService service;
    public UpdatePuzzleController(UpdatePuzzleDTO updatePuzzleDTO) {
        PuzzleRepository repo = new PuzzleMySQLRepository(MySqlConnection.getInstance());
        this.service = new UpdatePuzzleService(updatePuzzleDTO, repo);

    }

    @Override
    public Optional<Void> execute() {

        service.execute();
        return Optional.empty();

    }
}

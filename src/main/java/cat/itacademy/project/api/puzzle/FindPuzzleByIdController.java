package cat.itacademy.project.api.puzzle;

import cat.itacademy.project.business_logic.puzzle.application.FindPuzzleByIdService;
import cat.itacademy.project.business_logic.puzzle.domain.PuzzleRepository;
import cat.itacademy.project.business_logic.puzzle.infrastructure.PuzzleMySQLRepository;
import cat.itacademy.project.shared.domain.dtos.puzzle.PuzzleDTO;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.Optional;

public class FindPuzzleByIdController {
    private final FindPuzzleByIdService service;

    public FindPuzzleByIdController() {
        PuzzleRepository repo = new PuzzleMySQLRepository(MySqlConnection.getInstance());
        this.service = new FindPuzzleByIdService(repo);
    }

    public Optional<Optional<PuzzleDTO>> execute(int idToFind) {
        return Optional.ofNullable(service.execute(idToFind));

    }
}

package cat.itacademy.project.api.puzzle;

import cat.itacademy.project.business_logic.puzzle.application.FindPuzzleByIdService;
import cat.itacademy.project.business_logic.puzzle.domain.PuzzleRepository;
import cat.itacademy.project.business_logic.puzzle.infrastructure.PuzzleMySQLRepository;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.shared.domain.dtos.PuzzleDTO;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.Optional;

public class FindPuzzleByIdController extends MenuCommand<Optional<PuzzleDTO>> {
    private final FindPuzzleByIdService service;
    private final int idToFind;

    public FindPuzzleByIdController(int idToFind) {
        PuzzleRepository repo = new PuzzleMySQLRepository(MySqlConnection.getInstance());
        this.service = new FindPuzzleByIdService(idToFind, repo);
        this.idToFind = idToFind;
    }

    @Override
    public Optional<Optional<PuzzleDTO>> execute() {
        try {
            return Optional.ofNullable(service.execute());
        } catch (Exception e) {
            error("An unexpected error occurred: " + e.getMessage());
            return Optional.empty();
        }
    }
}

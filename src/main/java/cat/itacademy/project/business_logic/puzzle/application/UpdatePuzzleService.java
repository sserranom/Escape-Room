package cat.itacademy.project.business_logic.puzzle.application;

import cat.itacademy.project.business_logic.puzzle.domain.Puzzle;
import cat.itacademy.project.business_logic.puzzle.domain.PuzzleRepository;
import cat.itacademy.project.shared.domain.dtos.puzzle.PuzzleDTO;
import cat.itacademy.project.shared.domain.dtos.puzzle.UpdatePuzzleDTO;
import cat.itacademy.project.shared.domain.events.EventManager;
import cat.itacademy.project.shared.domain.exceptions.EmptyFieldException;
import cat.itacademy.project.shared.domain.exceptions.NotFoundException;

import java.util.Optional;

public class UpdatePuzzleService {
    private final PuzzleRepository repo;
    private final EventManager eventManager = EventManager.getInstance();

    public UpdatePuzzleService(PuzzleRepository repo) {
        this.repo = repo;
    }

    public Optional<PuzzleDTO> execute(UpdatePuzzleDTO request) {
        if (request.name() == null || request.name().isBlank()) {
            throw new EmptyFieldException("Field 'name' cannot be empty.");
        }

        Optional<PuzzleDTO> existingOptional = repo.findByName(request.nameToUpdate());

        if (existingOptional.isEmpty()) {
            throw new NotFoundException("Puzzle with name '" + request.nameToUpdate() + "' does not exist.");
        }

        Puzzle puzzle = Puzzle.fromDatabase(existingOptional.get());

        puzzle.setName(!request.name().isBlank() ? request.name() : puzzle.getName());
        puzzle.setDifficulty(!request.difficulty().isBlank() ? request.difficulty() : puzzle.getDifficulty());
        puzzle.setRoomId(request.roomId() > 0 ? request.roomId() : puzzle.getRoomId());
        puzzle.setAnswer(!request.answer().isBlank() ? request.answer() : puzzle.getAnswer());
        puzzle.setStory(!request.story().isBlank() ? request.story() : puzzle.getStory());
        puzzle.setThemeId(request.themeId() > 0 ? request.themeId() : puzzle.getThemeId());
        puzzle.setPrice(request.price() > 0 ? request.price() : puzzle.getPrice());

        repo.update(puzzle);
        if (puzzle.isPublishable()) {
            eventManager.publish("puzzle.published", puzzle.toDTO());
        }
        return Optional.of(puzzle.toDTO());

    }
}

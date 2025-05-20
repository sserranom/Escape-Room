package cat.itacademy.project.business_logic.puzzle.application;

import cat.itacademy.project.business_logic.puzzle.domain.Puzzle;
import cat.itacademy.project.business_logic.puzzle.domain.PuzzleRepository;
import cat.itacademy.project.shared.domain.Command;
import cat.itacademy.project.shared.domain.dtos.PuzzleDTO;
import cat.itacademy.project.shared.domain.dtos.UpdatePuzzleDTO;
import cat.itacademy.project.shared.domain.exceptions.AlreadyExistsException;
import cat.itacademy.project.shared.domain.exceptions.EmptyFieldException;
import cat.itacademy.project.shared.domain.exceptions.NotFoundException;

import java.util.Optional;

public class UpdatePuzzleService implements Command<PuzzleDTO> {
    private final UpdatePuzzleDTO request;
    private final PuzzleRepository repo;

    public UpdatePuzzleService(UpdatePuzzleDTO request, PuzzleRepository repo) {
        this.request = request;
        this.repo = repo;
    }

    @Override
    public Optional<PuzzleDTO> execute() {
        if (request.name() == null || request.name().isBlank()){
            throw new EmptyFieldException("Field 'name' cannot be empty.");
        }

        Optional<Puzzle> existingOptional = repo.findByName(request.nameToUpdate());

        if (existingOptional.isEmpty()){
            throw new NotFoundException("Puzzle with name '" + request.nameToUpdate() + "' does not exist.");
        }

        Puzzle puzzleToUpdate = existingOptional.get();
        Puzzle updatedPuzzle = puzzleToUpdate;

        if (!request.name().equals(puzzleToUpdate.getName()) || !request.name().isBlank()) {
            Optional<Puzzle> existingWithNewName = repo.findByName((request.name()));
            if (existingWithNewName.isPresent() && !Integer.valueOf(existingWithNewName.get().getId()).equals(puzzleToUpdate.getId())){
                throw new AlreadyExistsException("Puzzle with name '" + request.name() + "' already exist.");
            }
            updatedPuzzle = updatedPuzzle.createNewInstanceWithName(request.name());
        }

        if (request.difficulty() != null && !request.difficulty().isBlank() && !request.difficulty().equals(puzzleToUpdate.getDifficulty())) {
            updatedPuzzle = updatedPuzzle.createNewInstanceWithDifficulty(request.difficulty());
        }
        if (request.roomId() > 0 && request.roomId() != puzzleToUpdate.getRoomId()) {
            updatedPuzzle = updatedPuzzle.createNewInstanceWithRoomId(request.roomId());
        }

        if (request.answer() != null && !request.answer().isBlank() && !request.answer().equals(puzzleToUpdate.getAnswer())) {
            updatedPuzzle = updatedPuzzle.createNewInstanceWithAnswer(request.answer());
        }

        if (request.story() != null && !request.story().isBlank() && !request.story().equals(puzzleToUpdate.getStory())) {
            updatedPuzzle = updatedPuzzle.createNewInstanceWithStory(request.story());
        }

        if (request.themeId() > 0 && request.themeId() != puzzleToUpdate.getThemeId()) {
            updatedPuzzle = updatedPuzzle.createNewInstanceWithThemeId(request.themeId());
        }

        if (request.price() > 0 && request.price() != puzzleToUpdate.getPrice()) {
            updatedPuzzle = updatedPuzzle.createNewInstanceWithPrice(request.price());
        }

        repo.update(updatedPuzzle);
        return Optional.of(updatedPuzzle.toDTO());

    }
}

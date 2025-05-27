package cat.itacademy.project.shared.domain.exceptions;

public class PuzzleWithoutRoomException extends RuntimeException {
    public PuzzleWithoutRoomException(String message) {
        super(message);
    }
}

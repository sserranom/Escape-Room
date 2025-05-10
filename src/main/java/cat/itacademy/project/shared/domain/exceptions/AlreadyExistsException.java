package cat.itacademy.project.shared.domain.exceptions;

public class AlreadyExistsException extends CustomException {
    public AlreadyExistsException(String message) {
        super(message);
    }
}

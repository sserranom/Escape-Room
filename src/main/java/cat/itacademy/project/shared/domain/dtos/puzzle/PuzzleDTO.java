package cat.itacademy.project.shared.domain.dtos.puzzle;

import cat.itacademy.project.shared.domain.dtos.DTO;
import cat.itacademy.project.shared.domain.exceptions.EmptyFieldException;


public record PuzzleDTO(int id, String name,  int roomId, String answer, String story, int themeId,
                        double price) implements DTO {
    public PuzzleDTO {
        if (name == null || name.isBlank()) {
            throw new EmptyFieldException("Name cannot be null or empty");
        }

        if (roomId <= 0) {
            throw new EmptyFieldException("Room ID must be greater than 0");
        }

        if (answer == null || answer.isBlank()) {
            throw new EmptyFieldException("Answer cannot be null or empty");
        }

        if (story == null || story.isBlank()) {
            throw new EmptyFieldException("Story cannot be null or empty");
        }

        if (themeId <= 0) {
            throw new EmptyFieldException("Theme ID must be greater than 0");
        }

        if (price <= 0) {
            throw new EmptyFieldException("Price must be greater than 0");
        }

    }
}





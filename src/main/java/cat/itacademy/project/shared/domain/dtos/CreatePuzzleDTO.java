package cat.itacademy.project.shared.domain.dtos;

import cat.itacademy.project.shared.domain.exceptions.EmptyFieldException;

public record CreatePuzzleDTO (String name, String difficulty, int roomId, String answer, String story, int themeId, double price) {
    public CreatePuzzleDTO {
        if (name == null || name.isBlank()){
            throw new EmptyFieldException("Name Cannot be null or empty");
        }
    }
}

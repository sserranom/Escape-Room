package cat.itacademy.project.shared.domain.dtos.puzzle;

import cat.itacademy.project.shared.domain.exceptions.EmptyFieldException;
import cat.itacademy.project.shared.domain.exceptions.InvalidPriceException;

public record CreatePuzzleDTO(String name, int roomId, String answer, String story, int themeId,
                              double price) {
    public CreatePuzzleDTO {
        if (name == null || name.isBlank()) {
            throw new EmptyFieldException("Name Cannot be null or empty");
        }
        if (price <= 0) {
            throw new InvalidPriceException("Price must be greater than 0");
        }
        if (price > 99999999.99) {
            throw new InvalidPriceException("Price must be less than 99999999.99");
        }
    }
}

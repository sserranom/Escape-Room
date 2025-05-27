package cat.itacademy.project.shared.domain.dtos.room;

import cat.itacademy.project.shared.domain.dtos.DTO;
import cat.itacademy.project.shared.domain.exceptions.EmptyFieldException;
import cat.itacademy.project.shared.domain.exceptions.InvalidDificultyException;
import cat.itacademy.project.shared.domain.exceptions.InvalidPriceException;

import java.util.stream.Stream;

public record CreateRoomDTO(String name, double price, String difficulty, int themeId) implements DTO {
    public CreateRoomDTO {
        if (name == null || name.isBlank()) {
            throw new EmptyFieldException("Name Cannot be null or empty");
        }
        if (Stream.of("easy", "medium", "hard").noneMatch(difficulty::equals)) {
            throw new InvalidDificultyException("Difficulty can only be easy, medium or hard");
        }
        if (price <= 0) {
            throw new InvalidPriceException("Price must be greater than 0");
        }
        if (price > 99999999.99) {
            throw new InvalidPriceException("Price must be less than 99999999.99");
        }
    }
}


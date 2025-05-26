package cat.itacademy.project.shared.domain.dtos.room;

import cat.itacademy.project.shared.domain.exceptions.EmptyFieldException;
import cat.itacademy.project.shared.domain.exceptions.InvalidDificultyException;
import cat.itacademy.project.shared.domain.exceptions.InvalidPriceException;

import java.util.stream.Stream;

public record UpdateRoomDTO(String nameToUpdate, String name, String difficulty, double price, int themeId) {
    public UpdateRoomDTO {

        if (nameToUpdate == null || nameToUpdate.isBlank()) {
            throw new EmptyFieldException("The name of the room to update must be provided.");
        }
        if ((name == null || name.isBlank())) {
            throw new EmptyFieldException("The name of the room must be provided.");
        }
        if (Stream.of("easy", "medium", "hard", null).noneMatch(difficulty::equals)) {
            throw new InvalidDificultyException("Difficulty can only be easy, medium or hard");
        }
        if (price <= 0) {
            throw new InvalidPriceException("Price must be greater than 0");
        }
        if (price > 99999999.99){
            throw new InvalidPriceException("Price must be less than 99999999.99");
        }
    }
}

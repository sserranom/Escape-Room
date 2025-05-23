package cat.itacademy.project.shared.domain.dtos.room;

import cat.itacademy.project.shared.domain.exceptions.InvalidDificultyException;

import java.util.stream.Stream;

public record RoomDTO(int id, String name, String difficulty, double price, int themeId, String themeName) {
    public RoomDTO {
        if (Stream.of("easy", "medium", "hard").noneMatch(difficulty::equals)) {
            throw new InvalidDificultyException("Difficulty can only be easy, medium or hard");
        }
    }
}

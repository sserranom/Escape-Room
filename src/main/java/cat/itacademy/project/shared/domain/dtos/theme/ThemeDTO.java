package cat.itacademy.project.shared.domain.dtos.theme;

import cat.itacademy.project.shared.domain.exceptions.EmptyFieldException;

public record ThemeDTO(int id, String name, String description, int escapeRoomId) {
    public ThemeDTO {
        if (name == null || name.isBlank()) {
            throw new EmptyFieldException("Name cannot be null or empty");
        }
        if (description == null || description.isBlank()) {
            throw new EmptyFieldException("Description cannot be null or empty");
        }
    }
}

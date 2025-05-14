package cat.itacademy.project.buissness_logic.themes;

import cat.itacademy.project.shared.domain.exceptions.EmptyFieldException;

public record CreateThemeDTO(String name, String description, int escapeRoomId) {
    public CreateThemeDTO {
        if (name == null || name.isBlank()) {
            throw new EmptyFieldException("Name cannot be null or empty");
        }
        if (description == null || description.isBlank()) {
            throw new EmptyFieldException("Description cannot be null or empty");
        }
    }
}

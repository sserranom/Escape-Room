package cat.itacademy.project.shared.domain.dtos;

import cat.itacademy.project.shared.domain.exceptions.EmptyFieldException;

public record CreateEscapeRoomDTO(String name, String url) {
    public CreateEscapeRoomDTO {
        if (name == null || name.isBlank()) {
            throw new EmptyFieldException("Name cannot be null or empty");
        }
        if (url == null || url.isBlank()) {
            throw new EmptyFieldException("URL cannot be null or empty");
        }
    }
}

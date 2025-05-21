package cat.itacademy.project.shared.domain.dtos.deco;

import cat.itacademy.project.shared.domain.exceptions.EmptyFieldException;

public record CreateDecoDTO (String name, String description, String type, int escapeRoomId, double price) {
    public CreateDecoDTO {
        if (name == null || name.isBlank()) {
            throw new EmptyFieldException("Name Cannot be null or empty");
        }
    }
}

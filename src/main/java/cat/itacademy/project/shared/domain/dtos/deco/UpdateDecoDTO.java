package cat.itacademy.project.shared.domain.dtos.deco;

import cat.itacademy.project.shared.domain.exceptions.EmptyFieldException;

public record UpdateDecoDTO (String nameToUpdate, String name, String description, String type, int escapeRoomId, double price) {
    public UpdateDecoDTO {
        if (nameToUpdate == null || nameToUpdate.isBlank()) {
            throw new EmptyFieldException("The name of the decorative item to update must be provided.");
        }
        if ((name == null || name.isBlank())) {
            throw new EmptyFieldException("The name of the decorative item must be provided.");
        }

    }
}

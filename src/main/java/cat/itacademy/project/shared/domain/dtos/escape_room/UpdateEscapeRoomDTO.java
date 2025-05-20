package cat.itacademy.project.shared.domain.dtos.escape_room;

import cat.itacademy.project.shared.domain.exceptions.EmptyFieldException;

public record UpdateEscapeRoomDTO(String nameToUpdate, String name, String url) {

    public UpdateEscapeRoomDTO {

        if (nameToUpdate == null || nameToUpdate.isBlank()) {
            throw new EmptyFieldException("The name of the escape room to update must be provided.");
        }
        if ((name == null || name.isBlank()) && (url == null || url.isBlank())) {
            throw new EmptyFieldException("At least one of name or URL must be provided for update.");
        }
    }

}

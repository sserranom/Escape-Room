package cat.itacademy.project.shared.domain.dtos.theme;

import cat.itacademy.project.shared.domain.exceptions.EmptyFieldException;

public record UpdateThemeDTO(String nameToUpdate, String name, String description, Integer escapeRoomId) {

    public UpdateThemeDTO {

        if (nameToUpdate == null || nameToUpdate.isBlank()) {
            throw new EmptyFieldException("The name cannot be null or empty");
        }
        if (name == null || name.isBlank()) {
            throw new EmptyFieldException("The name of the theme cannot be null or empty");
        }

        if (description == null || description.isBlank()) {
            throw new EmptyFieldException("The description of the theme cannot be null or empty");
        }

        if (escapeRoomId == null || escapeRoomId <= 0) {
            throw new EmptyFieldException("The ID of the escape room cannot be null or less than or equal to 0");
        }


    }
}


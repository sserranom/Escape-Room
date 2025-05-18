package cat.itacademy.project.shared.domain.dtos;

import cat.itacademy.project.shared.domain.exceptions.EmptyFieldException;

public record UpdateRoomDTO(String nameToUpdate, String name, double price, int escapeRoomId) {
    public UpdateRoomDTO {

        if (nameToUpdate == null || nameToUpdate.isBlank()){
            throw new EmptyFieldException("The name of the room to update must be provided.");
        }
        if((name == null || name.isBlank())){
            throw new EmptyFieldException("The name of the room must be provided.");
        }
    }
}

package cat.itacademy.project.shared.domain.dtos;

import cat.itacademy.project.shared.domain.exceptions.EmptyFieldException;

public record CreateRoomDTO(String name, double price, int escapeRoomId) {
    public CreateRoomDTO {
        if (name == null || name.isBlank()){
            throw new EmptyFieldException("Name Cannot be null or empty");
        }
    }
}

package cat.itacademy.project.shared.domain.dtos.deco;

import cat.itacademy.project.business_logic.deco.domain.Type;
import cat.itacademy.project.shared.domain.exceptions.EmptyFieldException;

import java.util.stream.Stream;

public record CreateDecoDTO (String name, String description, Type type, int escapeRoomId, double price) {
    public CreateDecoDTO {
        if (name == null || name.isBlank()) {
            throw new EmptyFieldException("Name Cannot be null or empty");
        }
    }
}

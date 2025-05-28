package cat.itacademy.project.shared.domain.dtos.deco;

import cat.itacademy.project.shared.domain.dtos.DTO;
import cat.itacademy.project.shared.domain.exceptions.EmptyFieldException;
import cat.itacademy.project.shared.domain.exceptions.InvalidPriceException;

public record CreateDecoDTO(String name, String description, String type, int escapeRoomId,
                            double price) implements DTO {
    public CreateDecoDTO {
        if (name == null || name.isBlank()) {
            throw new EmptyFieldException("Name Cannot be null or empty");
        }
        if (price <= 0) {
            throw new InvalidPriceException("Price must be greater than 0");
        }
        if (price > 99999999.99) {
            throw new InvalidPriceException("Price must be less than 99999999.99");
        }
    }
}

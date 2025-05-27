package cat.itacademy.project.shared.domain.dtos.customer;

import cat.itacademy.project.shared.domain.exceptions.EmptyFieldException;

public record CreateCustomerDTO(String name, String email, boolean isSubscribed) {
    public CreateCustomerDTO {
        if (name == null || name.isBlank()) {
            throw new EmptyFieldException("Name Cannot be null or empty");
        }
    }
}

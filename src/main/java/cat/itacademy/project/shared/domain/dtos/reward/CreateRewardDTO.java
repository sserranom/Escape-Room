package cat.itacademy.project.shared.domain.dtos.reward;

import cat.itacademy.project.shared.domain.exceptions.EmptyFieldException;

public record CreateRewardDTO(String recipient, String description) {
    public CreateRewardDTO {
        if (recipient == null || recipient.isBlank() ||
                description == null || description.isBlank()
        ) {
            throw new EmptyFieldException("Recipient, description, and delivery date cannot be null or empty");
        }
    }
}

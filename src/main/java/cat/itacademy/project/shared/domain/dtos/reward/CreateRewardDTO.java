package cat.itacademy.project.shared.domain.dtos.reward;

import cat.itacademy.project.shared.domain.exceptions.EmptyFieldException;
import org.bson.types.ObjectId;

import java.util.Date;

public record CreateRewardDTO(ObjectId id, String recipient, String description, Date deliveryDate) {
    public CreateRewardDTO {
        if (recipient == null || recipient.isBlank() ||
                description == null || description.isBlank() ||
                deliveryDate == null) {
            throw new EmptyFieldException("Recipient, description, and delivery date cannot be null or empty");
        }
    }
}

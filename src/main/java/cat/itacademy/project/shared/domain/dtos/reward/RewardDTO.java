package cat.itacademy.project.shared.domain.dtos.reward;

import cat.itacademy.project.shared.domain.dtos.DTO;
import org.bson.types.ObjectId;
import java.util.Date;

public record RewardDTO(ObjectId id, String recipient, String description, Date deliveryDate) implements DTO {
}

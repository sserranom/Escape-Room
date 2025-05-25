package cat.itacademy.project.shared.domain.dtos.reservation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ReservationDTO(
        int id,
        Integer customerId,
        Integer puzzleId,
        double totalPrice,
        LocalDateTime creationDate,
        LocalDateTime completionDate
) {
}

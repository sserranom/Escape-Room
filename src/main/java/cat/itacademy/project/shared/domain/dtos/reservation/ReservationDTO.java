package cat.itacademy.project.shared.domain.dtos.reservation;

import java.time.LocalDateTime;

public record ReservationDTO(
        int id,
        Integer customerId,
        String customerName, Integer puzzleId,
        String puzzleName, double totalPrice,
        LocalDateTime creationDate,
        LocalDateTime completionDate
) {
}

package cat.itacademy.project.shared.domain.dtos.reservation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreateReservationDTO(Integer customerId, Integer puzzleId, LocalDateTime completionDate) {
    public CreateReservationDTO{

        if (customerId != null && customerId <= 0) {
            throw new IllegalArgumentException("Customer ID, if provided, must be a positive integer.");
        }

        if (puzzleId != null && puzzleId <= 0) {
            throw new IllegalArgumentException("Puzzle ID, if provided, must be a positive integer.");
        }
    }
}

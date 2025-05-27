package cat.itacademy.project.shared.domain.dtos.reservation;

import java.time.LocalDateTime;

public record ReservationDTO(int id, Integer customerId, String customerName, Integer puzzleId, String puzzleNama, double total_price, LocalDateTime creation_date, LocalDateTime completionDate) {

}

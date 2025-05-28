package cat.itacademy.project.shared.domain.dtos.reservation;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ReservationDTO(int id, Integer customerId, String customerName, Integer puzzleId, String puzzleNama, double totalPrice, LocalDate creation_date, LocalDate completionDate) {

}

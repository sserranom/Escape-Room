package cat.itacademy.project.shared.domain.dtos.reservation;

import java.time.LocalDateTime;

public record UpdateReservationDTO (int id, Double totalPrice, LocalDateTime completionDate){
    public UpdateReservationDTO {
        if (id <= 0) {
            throw new IllegalArgumentException("Reservation ID must be a positive integer for update.");
        }

        if (totalPrice != null && totalPrice <= 0) {
            throw new IllegalArgumentException("Total price, if provided for update, must be greater than zero.");
        }
    }
}

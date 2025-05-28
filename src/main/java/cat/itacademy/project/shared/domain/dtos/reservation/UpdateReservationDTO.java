package cat.itacademy.project.shared.domain.dtos.reservation;

import cat.itacademy.project.shared.domain.dtos.DTO;

import java.time.LocalDate;

public record UpdateReservationDTO(Integer id, Integer id_customer, Integer id_puzzle, Double totalPrice,
                                   LocalDate completionDate) implements DTO {
    public UpdateReservationDTO {
        if (id <= 0) {
            throw new IllegalArgumentException("Reservation ID must be a positive integer for update.");
        }

        if (totalPrice != null && totalPrice <= 0) {
            throw new IllegalArgumentException("Total price, if provided for update, must be greater than zero.");
        }
    }
}

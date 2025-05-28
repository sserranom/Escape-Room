package cat.itacademy.project.frontend.reservation;

import cat.itacademy.project.shared.domain.dtos.reservation.ReservationDTO;

import java.time.format.DateTimeFormatter;

public class ReservationPrinter {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void print(ReservationDTO reservation) {
        String creationDate = (reservation.creation_date() != null) ? reservation.creation_date().format(formatter) : "N/A";
        String completionDate = (reservation.completionDate() != null) ? reservation.completionDate().format(formatter) : "N/A";

        System.out.printf(
                "Id: %d   |  CustomerId: %d   |  PuzzleId: %d   |  Total Price: %,.2f   |  Creation Date: %s   |  Completion Date: %s%n",
                reservation.id(),
                reservation.customerId(),
                reservation.puzzleId(),
                reservation.totalPrice(),
                creationDate,
                completionDate
        );
    }
}

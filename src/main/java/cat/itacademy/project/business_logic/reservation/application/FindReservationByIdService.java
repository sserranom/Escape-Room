package cat.itacademy.project.business_logic.reservation.application;

import cat.itacademy.project.business_logic.reservation.domain.Reservation;
import cat.itacademy.project.business_logic.reservation.domain.ReservationRepository;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.shared.domain.exceptions.NotFoundException;

import java.util.Optional;

public class FindReservationByIdService extends MenuCommand<Reservation> {
    private final ReservationRepository reservationRepo;
    private final int reservationId;

    public FindReservationByIdService(int reservationId, ReservationRepository reservationRepo) {
        this.reservationId = reservationId;
        this.reservationRepo = reservationRepo;
    }

    @Override
    public Optional<Reservation> execute() throws NotFoundException {

        Optional<Reservation> reservation = reservationRepo.findById(reservationId);

        if (reservation.isEmpty()) {
            throw new NotFoundException("Reservation with ID " + reservationId + " not found.");
        }

        return reservation;
    }
}

package cat.itacademy.project.business_logic.reservation.application;

import cat.itacademy.project.business_logic.reservation.domain.Reservation;
import cat.itacademy.project.business_logic.reservation.domain.ReservationRepository;
import cat.itacademy.project.frontend.shared.MenuCommand;

import java.util.List;
import java.util.Optional;

public class FindAllReservationsService extends MenuCommand<List<Reservation>> {
    private final ReservationRepository reservationRepo;

    public FindAllReservationsService(ReservationRepository reservationRepo) {
        this.reservationRepo = reservationRepo;
    }

    @Override
    public Optional<List<Reservation>> execute() {
        List<Reservation> reservations = reservationRepo.findAll();
        return Optional.of(reservations);
    }
}

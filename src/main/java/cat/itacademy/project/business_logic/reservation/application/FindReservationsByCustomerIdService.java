package cat.itacademy.project.business_logic.reservation.application;

import cat.itacademy.project.business_logic.reservation.domain.Reservation;
import cat.itacademy.project.business_logic.reservation.domain.ReservationRepository;
import cat.itacademy.project.frontend.shared.MenuCommand;

import java.util.List;
import java.util.Optional;

public class FindReservationsByCustomerIdService extends MenuCommand<List<Reservation>> {
    private final ReservationRepository reservationRepo;
    private final int customerId;

    public FindReservationsByCustomerIdService(int customerId, ReservationRepository reservationRepo) {
        this.customerId = customerId;
        this.reservationRepo = reservationRepo;
    }

    @Override
    public Optional<List<Reservation>> execute() {

        List<Reservation> reservations = reservationRepo.findAllByCustomerId(customerId);
        return Optional.of(reservations);
    }
}

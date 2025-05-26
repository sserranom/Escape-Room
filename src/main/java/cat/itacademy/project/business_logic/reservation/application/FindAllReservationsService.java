package cat.itacademy.project.business_logic.reservation.application;

import cat.itacademy.project.business_logic.reservation.domain.ReservationRepository;
import cat.itacademy.project.shared.domain.dtos.reservation.ReservationDTO;

import java.util.List;
import java.util.Optional;

public class FindAllReservationsService {
    private final ReservationRepository reservationRepo;

    public FindAllReservationsService(ReservationRepository reservationRepo) {
        this.reservationRepo = reservationRepo;
    }

    public Optional<List<ReservationDTO>> execute() {
        return Optional.of(reservationRepo.findAll());

    }
}

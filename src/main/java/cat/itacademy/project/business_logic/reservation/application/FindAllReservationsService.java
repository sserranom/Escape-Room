package cat.itacademy.project.business_logic.reservation.application;

import cat.itacademy.project.business_logic.reservation.domain.Reservation;
import cat.itacademy.project.business_logic.reservation.domain.ReservationRepository;
import cat.itacademy.project.shared.domain.dtos.reservation.ReservationDTO;

import java.util.List;

public class FindAllReservationsService {
    private final ReservationRepository repo;

    public FindAllReservationsService(ReservationRepository repo) {
        this.repo = repo;
    }

    public List<ReservationDTO> findAll() {
        return repo.findAll();
    }
}


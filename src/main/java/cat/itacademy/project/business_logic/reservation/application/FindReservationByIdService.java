package cat.itacademy.project.business_logic.reservation.application;

import cat.itacademy.project.business_logic.reservation.domain.ReservationRepository;
import cat.itacademy.project.shared.domain.dtos.reservation.ReservationDTO;

import java.util.Optional;

public class FindReservationByIdService {
    private final ReservationRepository repo;


    public FindReservationByIdService(ReservationRepository repo) {

        this.repo = repo;
    }

    public Optional<ReservationDTO> execute(int id) {

        return repo.findById(id);
    }
}

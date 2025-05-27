package cat.itacademy.project.business_logic.reservation.application;

import cat.itacademy.project.business_logic.reservation.domain.Reservation;
import cat.itacademy.project.business_logic.reservation.domain.ReservationRepository;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.shared.domain.dtos.reservation.ReservationDTO;

import java.util.List;
import java.util.Optional;

public class FindReservationsByPuzzleIdService {
    private final ReservationRepository repo;

    public FindReservationsByPuzzleIdService(ReservationRepository repo) {
        this.repo = repo;
    }

    public Optional<List<ReservationDTO>> execute(int id) {
        return repo.findAllByPuzzleId(id);
    }
}

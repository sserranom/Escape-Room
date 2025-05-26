package cat.itacademy.project.business_logic.reservation.domain;

import cat.itacademy.project.shared.domain.dtos.reservation.ReservationDTO;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository {

    void create(ReservationDTO reservation);

    Optional<ReservationDTO> findById(int id);

    List<ReservationDTO> findAll();

    List<Reservation> findAllByCustomerId(int customerId);

    List<Reservation> findAllByPuzzleId(int puzzleId);

    void update(Reservation reservation);

    Optional<Void> delete(int id);
}

package cat.itacademy.project.business_logic.reservation.domain;

import cat.itacademy.project.shared.domain.dtos.reservation.CreateReservationDTO;
import cat.itacademy.project.shared.domain.dtos.reservation.ReservationDTO;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository {

    void create(Reservation reservation);

    Optional<ReservationDTO> findById(int id);

    List<ReservationDTO> findAll();

    Optional<List<ReservationDTO>> findAllByCustomerId(int customerId);

    Optional<List<ReservationDTO>> findAllByPuzzleId(int puzzleId);

    void update(Reservation reservation);

    void delete(int id);
}

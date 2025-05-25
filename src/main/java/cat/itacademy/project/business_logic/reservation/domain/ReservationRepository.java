package cat.itacademy.project.business_logic.reservation.domain;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository {

    void create(Reservation reservation);

    Optional<Reservation> findById(int id);

    List<Reservation> findAll();

    List<Reservation> findAllByCustomerId(int customerId);

    List<Reservation> findAllByPuzzleId(int puzzleId);

    void update(Reservation reservation);

    Optional<Void> delete(int id);
}

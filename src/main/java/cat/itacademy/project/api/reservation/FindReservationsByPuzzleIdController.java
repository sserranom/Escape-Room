package cat.itacademy.project.api.reservation;

import cat.itacademy.project.business_logic.reservation.application.FindReservationsByPuzzleIdService;
import cat.itacademy.project.business_logic.reservation.domain.ReservationRepository;
import cat.itacademy.project.business_logic.reservation.infraestructure.ReservationMySQLRepository;
import cat.itacademy.project.shared.domain.dtos.reservation.ReservationDTO;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.List;
import java.util.Optional;

public class FindReservationsByPuzzleIdController {
    private final FindReservationsByPuzzleIdService service;


    public FindReservationsByPuzzleIdController(FindReservationsByPuzzleIdService service) {
        ReservationRepository repo = new ReservationMySQLRepository(MySqlConnection.getInstance());
        this.service = new FindReservationsByPuzzleIdService(repo);
    }

    public Optional<List<ReservationDTO>> execute(int puzzleId) {
        try {
            return service.execute(puzzleId);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}

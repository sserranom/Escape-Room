package cat.itacademy.project.api.reservation;

import cat.itacademy.project.business_logic.reservation.application.FindAllReservationsService;
import cat.itacademy.project.business_logic.reservation.domain.ReservationRepository;
import cat.itacademy.project.business_logic.reservation.infraestructure.ReservationMySQLRepository;
import cat.itacademy.project.shared.domain.dtos.reservation.ReservationDTO;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.List;
import java.util.Optional;

public class FindAllReservationController {
    private final FindAllReservationsService service;


    public FindAllReservationController() {
        ReservationRepository repo = new ReservationMySQLRepository(MySqlConnection.getInstance());
        this.service = new FindAllReservationsService(repo);
    }

    public Optional<List<ReservationDTO>> execute() {
        List<ReservationDTO> reservations = service.findAll();
        return Optional.of(reservations);

    }
}

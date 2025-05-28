package cat.itacademy.project.api.reservation;

import cat.itacademy.project.business_logic.reservation.application.FindReservationsByCustomerIdService;
import cat.itacademy.project.business_logic.reservation.domain.ReservationRepository;
import cat.itacademy.project.business_logic.reservation.infraestructure.ReservationMySQLRepository;
import cat.itacademy.project.shared.domain.dtos.reservation.ReservationDTO;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.List;
import java.util.Optional;

public class FindReservationByCustomerIdController {
    private final FindReservationsByCustomerIdService service;

    public FindReservationByCustomerIdController() {
        ReservationRepository repo = new ReservationMySQLRepository(MySqlConnection.getInstance());
        this.service = new FindReservationsByCustomerIdService(repo);
    }

    public Optional<List<ReservationDTO>> execute(int customerId) {
        try {
            return service.execute(customerId);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}

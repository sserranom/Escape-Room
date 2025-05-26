package cat.itacademy.project.api.reservation;

import cat.itacademy.project.business_logic.reservation.domain.ReservationRepository;
import cat.itacademy.project.business_logic.reservation.infraestructure.ReservationMySQLRepository;
import cat.itacademy.project.shared.domain.dtos.reservation.CreateReservationDTO;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.Optional;

public class CreateReservationController {
//private final CreateReservationService service;

    public CreateReservationController(CreateReservationDTO createReservationDTO) {
        ReservationRepository repo = new ReservationMySQLRepository(MySqlConnection.getInstance());
//        this.service = new CreateReservationService(createReservationDTO, repo);
    }


    public Optional<Void> execute() {
//        service.execute();
        return Optional.empty();
    }
}

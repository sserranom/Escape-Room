package cat.itacademy.project.api.reservation;

import cat.itacademy.project.business_logic.reservation.application.CreateReservationService;
import cat.itacademy.project.business_logic.reservation.domain.ReservationRepository;
import cat.itacademy.project.business_logic.reservation.infraestructure.ReservationMySQLRepository;
import cat.itacademy.project.shared.domain.dtos.reservation.CreateReservationDTO;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.Optional;

public class CreateReservationController implements Command<Void> {
private final CreateReservationService service;

    public CreateReservationController(CreateReservationDTO createReservationDTO) {
        ReservationRepository repo = new ReservationMySQLRepository(MySqlConnection.getInstance());
        this.service = new CreateReservationService(createReservationDTO, repo);
    }


    @Override
    public Optional<Void> execute() {
        service.execute();
        return Optional.empty();
    }
}

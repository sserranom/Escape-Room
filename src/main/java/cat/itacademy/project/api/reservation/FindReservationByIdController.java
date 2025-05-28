package cat.itacademy.project.api.reservation;

import cat.itacademy.project.business_logic.reservation.application.FindReservationByIdService;
import cat.itacademy.project.business_logic.reservation.domain.ReservationRepository;
import cat.itacademy.project.business_logic.reservation.infraestructure.ReservationMySQLRepository;
import cat.itacademy.project.shared.domain.dtos.reservation.ReservationDTO;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.Optional;

public class FindReservationByIdController {
    private final FindReservationByIdService service;

    public FindReservationByIdController(){
        ReservationRepository repo = new ReservationMySQLRepository(MySqlConnection.getInstance());
        this.service = new FindReservationByIdService(repo);
    }

    public Optional<Optional<ReservationDTO>> execute(int id){
        try{
            return Optional.ofNullable(service.execute(id));

        }catch (Exception e){
            return Optional.empty();
        }
    }
    
}

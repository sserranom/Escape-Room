package cat.itacademy.project.business_logic.reservation.application;

import cat.itacademy.project.business_logic.reservation.domain.ReservationRepository;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.shared.domain.dtos.reservation.ReservationDTO;

import java.util.List;
import java.util.Optional;

public class FindReservationsByCustomerIdService{
    private final ReservationRepository repo;


    public FindReservationsByCustomerIdService(ReservationRepository repo) {
        this.repo = repo;
    }

    public Optional<List<ReservationDTO>> execute(int customerId) {
        return repo.findAllByCustomerId(customerId);
    }
}

package cat.itacademy.project.business_logic.reservation.application;

import cat.itacademy.project.business_logic.reservation.domain.ReservationRepository;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.shared.domain.exceptions.NotFoundException;

import java.util.Optional;

public class DeleteReservationService extends MenuCommand<Void> {
    private final ReservationRepository reservationRepo;
    private final int reservationId;

    public DeleteReservationService(int reservationId, ReservationRepository reservationRepo) {
        this.reservationId = reservationId;
        this.reservationRepo = reservationRepo;
    }

    @Override
    public Optional<Void> execute() throws NotFoundException {

        if (reservationRepo.findById(reservationId).isEmpty()) {
            throw new NotFoundException("Reservation with ID " + reservationId + " not found for deletion.");
        }

        reservationRepo.delete(reservationId);

        return Optional.empty();
    }
}

package cat.itacademy.project.business_logic.reservation.application;

import cat.itacademy.project.business_logic.reservation.domain.Reservation;
import cat.itacademy.project.business_logic.reservation.domain.ReservationRepository;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.shared.domain.dtos.reservation.UpdateReservationDTO;
import cat.itacademy.project.shared.domain.exceptions.NotFoundException;

import java.util.Optional;

public class UpdateReservationService extends MenuCommand<Reservation> {
    private final ReservationRepository reservationRepo;
    private final UpdateReservationDTO dto;

    public UpdateReservationService(UpdateReservationDTO dto, ReservationRepository reservationRepo) {
        this.dto = dto;
        this.reservationRepo = reservationRepo;
    }

    @Override
    public Optional<Reservation> execute() throws NotFoundException, IllegalArgumentException {

        Optional<Reservation> existingReservationOpt = reservationRepo.findById(dto.id());
        if (existingReservationOpt.isEmpty()) {
            throw new NotFoundException("Reservation with ID " + dto.id() + " not found for update.");
        }
        Reservation existingReservation = existingReservationOpt.get();

        if (dto.totalPrice() != null) {
            if (dto.totalPrice() <= 0) {
                throw new IllegalArgumentException("Total price must be greater than zero for update.");
            }
            existingReservation.setTotalPrice(dto.totalPrice());
        }

        if (dto.completionDate() != null) {
            existingReservation.setCompletionDate(dto.completionDate());
        }

        reservationRepo.update(existingReservation);
        return Optional.of(existingReservation);
    }
}

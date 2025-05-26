package cat.itacademy.project.business_logic.reservation.application;

import cat.itacademy.project.business_logic.puzzle.domain.Puzzle;
import cat.itacademy.project.business_logic.puzzle.domain.PuzzleRepository;
import cat.itacademy.project.business_logic.reservation.domain.Reservation;
import cat.itacademy.project.business_logic.reservation.domain.ReservationRepository;
import cat.itacademy.project.business_logic.room.domain.Room;
import cat.itacademy.project.business_logic.room.domain.RoomRepository;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.shared.domain.dtos.customer.CustomerDTO;
import cat.itacademy.project.shared.domain.dtos.puzzle.PuzzleDTO;
import cat.itacademy.project.shared.domain.dtos.reservation.CreateReservationDTO;
import cat.itacademy.project.shared.domain.dtos.reservation.ReservationDTO;
import cat.itacademy.project.shared.domain.dtos.room.RoomDTO;
import cat.itacademy.project.shared.domain.exceptions.AlreadyExistsException;
import cat.itacademy.project.shared.domain.exceptions.NotFoundException;

import java.math.BigDecimal;
import java.util.Optional;

public class CreateReservationService {

//    private final CreateReservationDTO reservation;
//    private final ReservationRepository repo;
//
//    public CreateReservationService(CreateReservationDTO reservation, ReservationRepository repo) {
//        this.reservation = reservation;
//        this.repo = repo;
//    }
//
//
//    public void execute() {
//        ensureDoesNotExist();
//        repo.create(reservation);
//    }
//
//    private void ensureDoesNotExist() throws AlreadyExistsException {
//        Optional<ReservationDTO> existing = repo.findByName(reservation.name());
//        if (existing.isPresent()) {
//            throw new AlreadyExistsException("Customer " + customer.name() + " already exist");
//        }
//    }
}

package cat.itacademy.project.business_logic.reservation.application;

import cat.itacademy.project.business_logic.puzzle.domain.PuzzleRepository;
import cat.itacademy.project.business_logic.reservation.domain.Reservation;
import cat.itacademy.project.business_logic.reservation.domain.ReservationRepository;
import cat.itacademy.project.business_logic.room.domain.RoomRepository;
import cat.itacademy.project.shared.domain.dtos.reservation.CreateReservationDTO;

public class CreateReservationService {
    private final ReservationRepository reservationRepo;
    private final PuzzleRepository puzzleRepo;
    private final RoomRepository roomRepo;

    public CreateReservationService(ReservationRepository reservationRepo, PuzzleRepository puzzleRepo, RoomRepository roomRepo) {
        this.reservationRepo = reservationRepo;
        this.puzzleRepo = puzzleRepo;
        this.roomRepo = roomRepo;
    }

    public void execute(CreateReservationDTO request){
        Reservation newReservation = new Reservation(
                request.customerId(),
                request.puzzleId(),
                request.completionDate(),
                puzzleRepo,
                roomRepo
        );
        reservationRepo.create(newReservation);
    }
}

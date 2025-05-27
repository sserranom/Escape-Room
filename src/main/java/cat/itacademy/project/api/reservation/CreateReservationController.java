package cat.itacademy.project.api.reservation;

import cat.itacademy.project.business_logic.puzzle.domain.PuzzleRepository;
import cat.itacademy.project.business_logic.puzzle.infrastructure.PuzzleMySQLRepository;
import cat.itacademy.project.business_logic.reservation.application.CreateReservationService;
import cat.itacademy.project.business_logic.reservation.domain.ReservationRepository;
import cat.itacademy.project.business_logic.reservation.infraestructure.ReservationMySQLRepository;
import cat.itacademy.project.business_logic.room.domain.RoomRepository;
import cat.itacademy.project.business_logic.room.infraestructure.RoomMySQLRepository;
import cat.itacademy.project.shared.domain.dtos.reservation.CreateReservationDTO;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.Optional;

public class CreateReservationController {
    private final CreateReservationService service;

    public CreateReservationController() {

        ReservationRepository reservationRepo = new ReservationMySQLRepository(MySqlConnection.getInstance());
        PuzzleRepository puzzleRepo = new PuzzleMySQLRepository(MySqlConnection.getInstance());
        RoomRepository roomRepo = new RoomMySQLRepository(MySqlConnection.getInstance());

        this.service = new CreateReservationService(reservationRepo, puzzleRepo, roomRepo);
    }

    public void execute(CreateReservationDTO createReservationDTO) {
        service.execute(createReservationDTO);
    }
}

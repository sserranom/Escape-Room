package cat.itacademy.project.business_logic.reservation.application;

import cat.itacademy.project.business_logic.puzzle.domain.Puzzle;
import cat.itacademy.project.business_logic.puzzle.domain.PuzzleRepository;
import cat.itacademy.project.business_logic.reservation.domain.Reservation;
import cat.itacademy.project.business_logic.reservation.domain.ReservationRepository;
import cat.itacademy.project.business_logic.room.domain.Room;
import cat.itacademy.project.business_logic.room.domain.RoomRepository;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.shared.domain.dtos.puzzle.PuzzleDTO;
import cat.itacademy.project.shared.domain.dtos.reservation.CreateReservationDTO;
import cat.itacademy.project.shared.domain.dtos.room.RoomDTO;
import cat.itacademy.project.shared.domain.exceptions.NotFoundException;

import java.math.BigDecimal;
import java.util.Optional;

public class CreateReservationService extends MenuCommand<Reservation> {

    private final ReservationRepository reservationRepo;
    private final PuzzleRepository puzzleRepo;
    private final RoomRepository roomRepo;
    private final CreateReservationDTO dto;

    public CreateReservationService(CreateReservationDTO dto, ReservationRepository reservationRepo, PuzzleRepository puzzleRepo, RoomRepository roomRepo) {
        this.dto = dto;
        this.reservationRepo = reservationRepo;
        this.puzzleRepo = puzzleRepo;
        this.roomRepo = roomRepo;
    }

    @Override
    public Optional<Reservation> execute() throws IllegalArgumentException, NotFoundException {
        double calculatedTotalPrice = 0.0;

        Puzzle puzzle = null;
        if (dto.puzzleId() != null) {
            Optional<PuzzleDTO> puzzleOptional = puzzleRepo.findById(dto.puzzleId());
            if (puzzleOptional.isEmpty()) {
                throw new NotFoundException("Puzzle with ID " + dto.puzzleId() + " not found.");
            }
            puzzle = Puzzle.fromDatabase(puzzleOptional.get());

            if (puzzle.getRoomId() <= 0) {
                throw new IllegalArgumentException("Cannot create a reservation for a puzzle (ID: " + dto.puzzleId() + ") that is not associated with a room.");
            }

            if (puzzle.getPrice() > 0) {
                calculatedTotalPrice += puzzle.getPrice();
            } else {
                throw new IllegalArgumentException("Puzzle with ID " + dto.puzzleId() + " does not have a defined price or price is not positive.");
            }

            if (puzzle.getRoomId() <= 0 ) {
                Optional<RoomDTO> roomOptional = roomRepo.findById(puzzle.getRoomId());
                if (roomOptional.isEmpty()) {
                    throw new NotFoundException("Room with ID " + puzzle.getRoomId() + " associated with puzzle " + puzzle.getId() + " not found.");
                }
                Room room = Room.fromDatabase(roomOptional.get());

                if (room.getPrice() > 0) {
                    calculatedTotalPrice += room.getPrice();
                } else {
                    throw new IllegalArgumentException("Room with ID " + room.getId() + " does not have a defined price or price is not positive.");
                }
            }
        } else {
            throw new IllegalArgumentException("A reservation must be associated with a puzzle.");
        }

        if (calculatedTotalPrice <= 0) {
            throw new IllegalArgumentException("The calculated total price for the reservation must be greater than zero.");
        }

        Reservation newReservation = new Reservation(
                dto.customerId(),
                dto.puzzleId(),
                calculatedTotalPrice,
                dto.completionDate()
        );

        reservationRepo.create(newReservation);
        return Optional.of(newReservation);
    }
}

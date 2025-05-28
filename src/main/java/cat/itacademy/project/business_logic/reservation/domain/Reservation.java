package cat.itacademy.project.business_logic.reservation.domain;

import cat.itacademy.project.business_logic.puzzle.domain.PuzzleRepository;
import cat.itacademy.project.business_logic.room.domain.RoomDecoRepository;
import cat.itacademy.project.business_logic.room.domain.RoomRepository;
import cat.itacademy.project.shared.domain.dtos.deco.DecoDTO;
import cat.itacademy.project.shared.domain.dtos.puzzle.PuzzleDTO;
import cat.itacademy.project.shared.domain.dtos.reservation.CreateReservationDTO;
import cat.itacademy.project.shared.domain.dtos.reservation.ReservationDTO;
import cat.itacademy.project.shared.domain.dtos.room.RoomDTO;
import cat.itacademy.project.shared.domain.exceptions.NotFoundException;
import cat.itacademy.project.shared.domain.exceptions.PuzzleWithoutRoomException;

import java.time.LocalDate;
import java.util.List;

public class Reservation {

    private Integer id;
    private Integer customerId;
    private Integer puzzleId;
    private double totalPrice;
    private LocalDate creationDate;
    private LocalDate completionDate;

    public Reservation(int id, Integer customerId, Integer puzzleId, double totalPrice, LocalDate creationDate, LocalDate completionDate) {
        this.id = id;
        this.customerId = customerId;
        this.puzzleId = puzzleId;
        this.totalPrice = totalPrice;
        this.creationDate = creationDate;
        this.completionDate = completionDate;
    }

    public Reservation(Integer customerId, Integer puzzleId, LocalDate completionDate,
                       PuzzleRepository puzzleRepo, RoomRepository roomRepo, RoomDecoRepository roomDecoRepo) {
        this.customerId = customerId;
        this.puzzleId = puzzleId;
        this.creationDate = LocalDate.now();
        this.completionDate = (completionDate != null) ? LocalDate.from(completionDate.atStartOfDay()) : null;

        this.totalPrice = calculateTotalPrice(puzzleId, puzzleRepo, roomRepo, roomDecoRepo);
    }

    public static Reservation fromDatabase(ReservationDTO reservationDTO) {
        return new Reservation(
                reservationDTO.id(),
                reservationDTO.customerId(),
                reservationDTO.puzzleId(),
                reservationDTO.totalPrice(),
                reservationDTO.creation_date(),
                reservationDTO.completionDate()
        );
    }

    public static Reservation fromCreateDTO(CreateReservationDTO createDTO, PuzzleRepository puzzleRepo, RoomRepository roomRepo, RoomDecoRepository roomDecoRepo) {
        return new Reservation(
                createDTO.customerId(),
                createDTO.puzzleId(),
                createDTO.completionDate(),
                puzzleRepo,
                roomRepo,
                roomDecoRepo
        );
    }

    public ReservationDTO toDTO() {
        return new ReservationDTO(
                this.id,
                this.customerId,
                null,
                this.puzzleId,
                null,
                this.totalPrice,
                this.creationDate,
                this.completionDate
        );
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getPuzzleId() {
        return puzzleId;
    }

    public void setPuzzleId(Integer puzzleId) {
        this.puzzleId = puzzleId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", puzzleId=" + puzzleId +
                ", totalPrice=" + totalPrice +
                ", creationDate=" + creationDate +
                ", completionDate=" + completionDate +
                '}';
    }


    private double calculateTotalPrice(Integer puzzleId, PuzzleRepository puzzleRepo, RoomRepository roomRepo, RoomDecoRepository roomDecoRepo) {

        PuzzleDTO puzzle = puzzleRepo.findById(puzzleId)
                .orElseThrow(() -> new NotFoundException("Puzzle with ID " + puzzleId + " not found."));

        if (puzzle.roomId() == 0) {
            throw new PuzzleWithoutRoomException("Puzzle with ID " + puzzle.id() + " does not have an assigned room.");
        }

        double puzzlePrice = puzzle.price();

        RoomDTO room = roomRepo.findById(puzzle.roomId())
                .orElseThrow(() -> new NotFoundException("Room with ID " + puzzle.roomId() + " not found for puzzle."));

        double roomPrice = room.price();

        double decoTotalPrice = 0.0;
        List<DecoDTO> decosInRoom = roomDecoRepo.findDecosByRoomId(room.id());

        for (DecoDTO deco : decosInRoom) {
            decoTotalPrice += deco.price();
        }

        double finalTotalPrice = puzzlePrice + roomPrice + decoTotalPrice;

        return finalTotalPrice;
    }

    public void recalculateTotalPrice(PuzzleRepository puzzleRepo, RoomRepository roomRepo, RoomDecoRepository roomDecoRepo) {
        this.setTotalPrice(calculateTotalPrice(this.puzzleId, puzzleRepo, roomRepo, roomDecoRepo));
    }
}

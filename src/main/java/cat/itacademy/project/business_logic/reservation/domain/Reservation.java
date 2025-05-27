package cat.itacademy.project.business_logic.reservation.domain;

import cat.itacademy.project.business_logic.escaperoom.domain.EscapeRoomRepository;
import cat.itacademy.project.business_logic.puzzle.domain.PuzzleRepository;
import cat.itacademy.project.business_logic.room.domain.RoomRepository;
import cat.itacademy.project.shared.domain.dtos.escape_room.EscapeRoomDTO;
import cat.itacademy.project.shared.domain.dtos.puzzle.PuzzleDTO;
import cat.itacademy.project.shared.domain.dtos.reservation.CreateReservationDTO;
import cat.itacademy.project.shared.domain.dtos.reservation.ReservationDTO;
import cat.itacademy.project.shared.domain.dtos.room.RoomDTO;
import cat.itacademy.project.shared.domain.dtos.theme.ThemeDTO;
import cat.itacademy.project.shared.domain.exceptions.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Reservation {

    private int id;
    private Integer customerId;
    private Integer puzzleId;
    private double totalPrice;
    private LocalDateTime creationDate;
    private LocalDateTime completionDate;

    public Reservation(int id, Integer customerId, Integer puzzleId, double totalPrice, LocalDateTime creationDate, LocalDateTime completionDate) {
        this.id = id;
        this.customerId = customerId;
        this.puzzleId = puzzleId;
        this.totalPrice = totalPrice;
        this.creationDate = creationDate;
        this.completionDate = completionDate;
    }

    public Reservation(Integer customerId, Integer puzzleId, LocalDate completionDate,
                       PuzzleRepository puzzleRepo, RoomRepository roomRepo) {
        this.customerId = customerId;
        this.puzzleId = puzzleId;
        this.creationDate = LocalDateTime.now();
        this.completionDate = (completionDate != null) ? completionDate.atStartOfDay() : null;

        this.totalPrice = calculateTotalPrice(puzzleId, puzzleRepo, roomRepo);
    }

    public static Reservation fromDatabase(ReservationDTO reservationDTO) {
        return new Reservation(
                reservationDTO.id(),
                reservationDTO.customerId(),
                reservationDTO.puzzleId(),
                reservationDTO.total_price(),
                reservationDTO.creation_date(),
                reservationDTO.completionDate()
        );
    }

    public static Reservation fromCreateDTO(CreateReservationDTO createDTO, PuzzleRepository puzzleRepo, RoomRepository roomRepo) {
        return new Reservation(
                createDTO.customerId(),
                createDTO.puzzleId(),
                createDTO.completionDate(),
                puzzleRepo,
                roomRepo
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

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDateTime completionDate) {
        this.completionDate = completionDate;
    }

    private double calculateTotalPrice(Integer puzzleId, PuzzleRepository puzzleRepo, RoomRepository roomRepo) { // ¡Cambiado a RoomRepository!
        if (puzzleId == null) {
            throw new IllegalArgumentException("Puzzle ID is required to calculate total price.");
        }

        PuzzleDTO puzzle = puzzleRepo.findById(puzzleId)
                .orElseThrow(() -> new NotFoundException("Puzzle with ID " + puzzleId + " not found."));

        double puzzlePrice = puzzle.price();

        double roomPrice = 0.0;
        if (puzzle.roomId() <= 0) {
            RoomDTO room = roomRepo.findById(puzzle.roomId()) // Usar roomRepo
                    .orElseThrow(() -> new NotFoundException("Room with ID " + puzzle.roomId() + " not found for puzzle."));
            roomPrice = room.price();
        }

        return puzzlePrice + roomPrice;
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
}

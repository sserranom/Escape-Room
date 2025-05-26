package cat.itacademy.project.business_logic.reservation.domain;

import cat.itacademy.project.shared.domain.dtos.reservation.ReservationDTO;

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

    public Reservation(Integer customerId, Integer puzzleId, double totalPrice, LocalDateTime completionDate) {
        this.customerId = customerId;
        this.puzzleId = puzzleId;
        this.totalPrice = totalPrice;
        this.creationDate = completionDate;
    }

    public static Reservation fromDatabase(ReservationDTO reservationDTO) {
        return new Reservation(
                reservationDTO.id(),
                reservationDTO.customerId(),
                reservationDTO.puzzleId(),
                reservationDTO.totalPrice(),
                reservationDTO.creationDate(),
                reservationDTO.completionDate()
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

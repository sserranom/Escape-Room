package cat.itacademy.project.business_logic.reservation.domain;

import java.math.BigDecimal;
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

    public int getId() {
        return id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public Integer getPuzzleId() {
        return puzzleId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getCompletionDate() {
        return completionDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public void setPuzzleId(Integer puzzleId) {
        this.puzzleId = puzzleId;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
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

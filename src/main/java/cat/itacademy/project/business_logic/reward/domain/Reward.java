package cat.itacademy.project.business_logic.reward.domain;

import org.bson.types.ObjectId;

import java.util.Date;

public class Reward {
    private static final long serialVersionUID = 1L;
    private ObjectId id;
    private final String recipient;
    private final String description;
    private final Date deliveryDate;

    public Reward(ObjectId id, String recipient, String description, Date deliveryDate) {
        this.id = id;
        this.recipient = recipient;
        this.description = description;
        this.deliveryDate = deliveryDate;
    }

    public Reward(String recipient, String description, Date deliveryDate) {
        this.recipient = recipient;
        this.description = description;
        this.deliveryDate = deliveryDate;
    }

    public ObjectId getId() {
        return id;
    }

    public String getRecipient() {
        return recipient;
    }
    public String getDescription() {
        return description;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }
}

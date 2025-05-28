package cat.itacademy.project.business_logic.notification.domain;

import org.bson.types.ObjectId;


public class Notification {
    private final String recipientName;
    private final String recipientEmail;
    private final String message;
    private ObjectId id;

    public Notification(ObjectId id, String recipientName, String recipientEmail, String message) {
        this.id = id;
        this.recipientName = recipientName;
        this.recipientEmail = recipientEmail;
        this.message = message;
    }

    public Notification(String recipientName, String recipientEmail, String message) {
        this.recipientName = recipientName;
        this.recipientEmail = recipientEmail;
        this.message = message;
    }

    public ObjectId getId() {
        return id;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public String getMessage() {
        return message;
    }


}

package cat.itacademy.project.shared.domain.dtos.notification;

import org.bson.types.ObjectId;

public record NotificationDTO (ObjectId id, String recipientName, String recipientEmail, String message){



}
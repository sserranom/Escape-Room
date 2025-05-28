package cat.itacademy.project.shared.domain.dtos.notification;

import cat.itacademy.project.shared.domain.exceptions.EmptyFieldException;

public record CreateNotificationDTO(String recipientName, String recipientEmail, String message) {
    public CreateNotificationDTO {
        if (recipientName == null || recipientName.isBlank() ||
                recipientEmail == null || recipientEmail.isBlank() ||
                message == null || message.isBlank()) {
            throw new EmptyFieldException("Recipient name and email, and message cannot be null or empty");
        }
    }

}

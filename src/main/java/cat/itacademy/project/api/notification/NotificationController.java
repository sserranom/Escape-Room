package cat.itacademy.project.api.notification;

import cat.itacademy.project.shared.domain.dtos.notification.NotificationDTO;

import java.util.logging.Logger;

public class NotificationController {
    private static final Logger logger = Logger.getLogger(NotificationController.class.getName());

    public static void send(NotificationDTO notificationDTO) {
        if (notificationDTO == null) {
            throw new IllegalArgumentException("NotificationDTO cannot be null");
        }
        String notificationContent = String.format(
                "=== Sending Notification ===%nTo: %s <%s>%nMessage: %s%n===========================",
                notificationDTO.recipientName(), notificationDTO.recipientEmail(), notificationDTO.message()
        );
        logger.info(notificationContent);
    }
}

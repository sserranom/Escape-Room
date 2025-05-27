package cat.itacademy.project.api.notification;

import cat.itacademy.project.business_logic.notification.application.CreateNotificationService;
import cat.itacademy.project.business_logic.notification.domain.NotificationRepository;
import cat.itacademy.project.business_logic.notification.infrastructure.NotificationMongoRepository;
import cat.itacademy.project.shared.domain.dtos.DTO;
import cat.itacademy.project.shared.domain.dtos.notification.NotificationDTO;
import cat.itacademy.project.shared.domain.dtos.puzzle.PuzzleDTO;

import java.util.logging.Logger;

public class NotificationController {
 private CreateNotificationService service;
 private NotificationRepository repo;

    public NotificationController() {
        this.repo = new NotificationMongoRepository(cat.itacademy.project.shared.infrastructure.database.mongodb.MongoDBConnection.getInstance());
        this.service = new CreateNotificationService();
    }

    public  void send(DTO eventDto) {
        if (notificationDTO == null) {
            throw new IllegalArgumentException("NotificationDTO cannot be null");
        }
        PuzzleDTO puzzleDTO = (PuzzleDTO) eventDto;
        String notificationContent = String.format(
                "=== Sending Notification ===%nTo: %s <%s>%nMessage: %s%n===========================",
                p.recipientName(), notificationDTO.recipientEmail(), notificationDTO.message()
        );
        System.out.println(notificationContent);
    }
}

package cat.itacademy.project.api.notification;

import cat.itacademy.project.business_logic.notification.application.CreateNotificationService;
import cat.itacademy.project.business_logic.notification.infrastructure.NotificationMongoRepository;
import cat.itacademy.project.shared.domain.dtos.customer.CustomerDTO;
import cat.itacademy.project.shared.domain.dtos.notification.CreateNotificationDTO;
import cat.itacademy.project.shared.domain.dtos.notification.NotificationDTO;
import cat.itacademy.project.shared.domain.dtos.puzzle.PuzzleDTO;
import cat.itacademy.project.shared.infrastructure.database.mongodb.MongoDBConnection;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class NotificationController {
    private final CreateNotificationService createNotificationService;

    public NotificationController() {
        this .createNotificationService = new CreateNotificationService(
                new NotificationMongoRepository(MongoDBConnection.getDatabase())
        );
    }

    private static final Logger logger = Logger.getLogger(NotificationController.class.getName());

    public void send(PuzzleDTO eventDto) {

        List<NotificationDTO> notificationDTOS = buildNotifications(eventDto);
        for (NotificationDTO notificationDTO : notificationDTOS) {
            if (notificationDTO == null) {
                throw new IllegalArgumentException("NotificationDTO cannot be null");
            }

            // Print notification to screen
            String notificationContent = String.format(
                    "=== Sending Notification ===%nTo: %s <%s>%nMessage: %s%n===========================",
                    notificationDTO.recipientName(), notificationDTO.recipientEmail(), notificationDTO.message()
            );
            logger.info(notificationContent);

            // Instantiate MongoDB repository


            // Create and use notification service

            // Convert NotificationDTO to CreateNotificationDTO and persist
            var createNotificationDTO = new CreateNotificationDTO(
                    notificationDTO.id(),
                    notificationDTO.recipientName(),
                    notificationDTO.recipientEmail(),
                    notificationDTO.message()
            );

            createNotificationService.execute(createNotificationDTO);
        }
    }

    private List<NotificationDTO> buildNotifications(PuzzleDTO eventDto) {
        List<NotificationDTO> result = new ArrayList<>();
        List<CustomerDTO> subscribedCustomers = eventDto.subscribedCustomers();

        return result;
    }
}

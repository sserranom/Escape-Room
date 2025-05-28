package cat.itacademy.project.api.notification;

import cat.itacademy.project.business_logic.customer.application.FindAllCustomerSubscribedService;
import cat.itacademy.project.business_logic.customer.infraestructure.CustomerMySQLRepository;
import cat.itacademy.project.business_logic.notification.application.CreateNotificationService;
import cat.itacademy.project.business_logic.notification.infrastructure.NotificationMongoRepository;
import cat.itacademy.project.shared.domain.dtos.customer.CustomerDTO;
import cat.itacademy.project.shared.domain.dtos.notification.CreateNotificationDTO;
import cat.itacademy.project.shared.domain.dtos.puzzle.PuzzleDTO;
import cat.itacademy.project.shared.infrastructure.database.mongodb.MongoDBConnection;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class NotificationController {
    private static final Logger logger = Logger.getLogger(NotificationController.class.getName());
    private final CreateNotificationService createNotificationService;
    private final FindAllCustomerSubscribedService findAllCustomerSubscribedService;

    public NotificationController() {
        this.createNotificationService = new CreateNotificationService(
                new NotificationMongoRepository(MongoDBConnection.getDatabase())
        );
        this.findAllCustomerSubscribedService = new FindAllCustomerSubscribedService(
                new CustomerMySQLRepository(MySqlConnection.getInstance())
        );
    }

    public void send(PuzzleDTO eventDto) {

        List<CreateNotificationDTO> notificationDTOS = buildNotifications(eventDto);
        for (CreateNotificationDTO notificationDTO : notificationDTOS) {
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

                    notificationDTO.recipientName(),
                    notificationDTO.recipientEmail(),
                    notificationDTO.message()
            );

            createNotificationService.execute(createNotificationDTO);
        }
    }

    private List<CreateNotificationDTO> buildNotifications(PuzzleDTO eventDto) {
        List<CreateNotificationDTO> result = new ArrayList<>();
        List<CustomerDTO> subscribedCustomers = findAllCustomerSubscribedService.execute();
        for (CustomerDTO customer : subscribedCustomers) {
            CreateNotificationDTO notification = new CreateNotificationDTO(
                    customer.name(),
                    customer.email(),
                    String.format("New puzzle published: %s", eventDto.name())
            );
            result.add(notification);
        }

        return result;
    }
}

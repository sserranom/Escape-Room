package cat.itacademy.project.business_logic.notification.application;


import cat.itacademy.project.business_logic.notification.domain.NotificationRepository;
import cat.itacademy.project.shared.domain.dtos.notification.CreateNotificationDTO;

public class CreateNotificationService {
    private final NotificationRepository notificationRepository;

    public CreateNotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void execute(CreateNotificationDTO notification) {
        notificationRepository.create(notification);
    }
}

package cat.itacademy.project.api.notification;

import cat.itacademy.project.shared.domain.dtos.DTO;
import cat.itacademy.project.shared.domain.dtos.notification.NotificationDTO;
import cat.itacademy.project.shared.domain.events.EventListener;

public class NotificationEventListener implements EventListener {
    @Override
    public void update(String topic, DTO dto) {
        if (dto instanceof NotificationDTO) {
            NotificationDTO notificationDTO = (NotificationDTO) dto;
            NotificationController.send(notificationDTO);

        }
    }
}
package cat.itacademy.project.business_logic.notification.domain;


import cat.itacademy.project.shared.domain.dtos.notification.CreateNotificationDTO;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository {
    void create(CreateNotificationDTO notification);
    void save(Notification notification);
    Optional<Notification> findById(ObjectId id);
    List<Notification> findAll();
}

package cat.itacademy.project.business_logic.notification.infrastructure;

import cat.itacademy.project.business_logic.notification.domain.Notification;

import cat.itacademy.project.business_logic.notification.domain.NotificationRepository;
import cat.itacademy.project.shared.domain.dtos.notification.CreateNotificationDTO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class NotificationMongoRepository implements NotificationRepository {
    private final MongoCollection<Document> collection;
    private static final String RECIPIENT_NAME = "recipientName";
    private static final String RECIPIENT_EMAIL = "recipientEmail";
    private static final String MESSAGE = "message";


    public NotificationMongoRepository(MongoDatabase database) {
        this.collection = database.getCollection("rewards");
    }

    @Override
    public void create(CreateNotificationDTO notification) {
        Document doc = new Document(RECIPIENT_NAME, notification.recipientName())
                .append(RECIPIENT_EMAIL, notification.recipientEmail())
                .append(MESSAGE, notification.message());
        collection.insertOne(doc);
    }

    @Override
    public void save(Notification notification) {
        Document doc = new Document(RECIPIENT_NAME, notification.getRecipientName())
                .append(RECIPIENT_EMAIL, notification.getRecipientEmail())
                .append(MESSAGE, notification.getMessage());
        collection.insertOne(doc);
    }


    @Override
    public Optional<Notification> findById(ObjectId id) {
        Document doc = collection.find(new Document("_id", id)).first();
        if (doc == null) {
            return Optional.empty();
        }
        Notification notification = new Notification(
                doc.getObjectId("_id"),
                doc.getString(RECIPIENT_NAME),
                doc.getString(RECIPIENT_EMAIL),
                doc.getString(MESSAGE)
        );
        return Optional.of(notification);
    }

    @Override
    public List<Notification> findAll() {
        List<Notification> rewards = new ArrayList<>();
        for (Document doc : collection.find()) {
            rewards.add(new Notification(
                    doc.getObjectId("_id"),
                    doc.getString(RECIPIENT_NAME),
                    doc.getString(RECIPIENT_EMAIL),
                    doc.getString(MESSAGE)
            ));
        }
        return rewards;
    }


}

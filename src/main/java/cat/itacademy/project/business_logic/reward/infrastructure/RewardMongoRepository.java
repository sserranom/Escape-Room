package cat.itacademy.project.business_logic.reward.infrastructure;

import cat.itacademy.project.business_logic.reward.domain.Reward;
import cat.itacademy.project.business_logic.reward.domain.RewardRepository;
import cat.itacademy.project.shared.domain.dtos.reward.CreateRewardDTO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RewardMongoRepository implements RewardRepository {
    private static final String RECIPIENT = "recipient";
    private static final String DESCRIPTION = "description";
    private static final String DELIVERY_DATE = "deliveryDate";
    private final MongoCollection<Document> collection;


    public RewardMongoRepository(MongoDatabase database) {
        this.collection = database.getCollection("rewards");
    }

    @Override
    public void create(CreateRewardDTO reward) {
        Document doc = new Document(RECIPIENT, reward.recipient())
                .append(DESCRIPTION, reward.description())
                ;
        collection.insertOne(doc);
    }

    @Override
    public void save(Reward reward) {
        Document doc = new Document(RECIPIENT, reward.getRecipient())
                .append(DESCRIPTION, reward.getDescription())
                .append(DELIVERY_DATE, reward.getDeliveryDate());
        collection.insertOne(doc);
    }


    @Override
    public Optional<Reward> findById(ObjectId id) {
        Document doc = collection.find(new Document("_id", id)).first();
        if (doc == null) {
            return Optional.empty();
        }
        Reward reward = new Reward(
                doc.getObjectId("_id"),
                doc.getString(RECIPIENT),
                doc.getString(DESCRIPTION),
                doc.getDate(DELIVERY_DATE)
        );
        return Optional.of(reward);
    }

    @Override
    public List<Reward> findAll() {
        List<Reward> rewards = new ArrayList<>();
        for (Document doc : collection.find()) {
            rewards.add(new Reward(
                    doc.getObjectId("id"),
                    doc.getString(RECIPIENT),
                    doc.getString(DESCRIPTION),
                    doc.getDate(DELIVERY_DATE)
            ));
        }
        return rewards;
    }


}

package cat.itacademy.project.shared.infrastructure.database.mongodb;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnection {

    private static final String CONNECTION_STRING = MongoDBConfig.getString();
    private static volatile MongoClient instance;

    private MongoDBConnection() {
    }

    private static MongoClient createConnection() {
        return MongoClients.create(MongoDBConnection.CONNECTION_STRING);
    }

    public static MongoClient getInstance() {
        MongoClient result = instance;
        if (result != null) {
            return result;
        }
        synchronized (MongoDBConnection.class) {
            if (instance == null) {
                instance = createConnection();
            }
            return instance;
        }
    }

    public static MongoDatabase getDatabase(

    ) {
        MongoClient mongoClient = getInstance();
        return mongoClient.getDatabase("escape_room");
    }
}

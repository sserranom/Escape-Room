package cat.itacademy.project.shared.infrastructure.database.mongoDB;


public class MongoDBConnection {

    private static final String CONNECTION_STRING = MongoDBConfig.getString();
    private static volatile com.mongodb.client.MongoClient instance;

    private MongoDBConnection() {
    }

    private static com.mongodb.client.MongoClient createConnection() {
        return com.mongodb.client.MongoClients.create(MongoDBConnection.CONNECTION_STRING);
    }

    public static com.mongodb.client.MongoClient getInstance() {
        com.mongodb.client.MongoClient result = instance;
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
}

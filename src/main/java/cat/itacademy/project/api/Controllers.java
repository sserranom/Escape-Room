package cat.itacademy.project.api;

import cat.itacademy.project.shared.infrastructure.database.mongodb.MongoDBConnection;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controllers {
    private static final Connection mySqlConnection = MySqlConnection.getInstance();
    private satic final MongoDBConnection mongoConnection = MongoDBConnection.getInstance(); // Placeholder for MongoDB connection if needed
    private static final Map<String, List<Command<?>>> controllers = new HashMap<>();

    public static Command<?> getController(String controllerName) {
        List<Command<?>> commands = controllers.get(controllerName);
        if (commands == null || commands.isEmpty()) {
            throw new IllegalArgumentException("Controller not found: " + controllerName);
        }
        return commands.get(0); // Assuming we want the first command for simplicity
    }
}

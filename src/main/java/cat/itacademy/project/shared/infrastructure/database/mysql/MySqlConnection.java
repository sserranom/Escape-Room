package cat.itacademy.project.shared.infrastructure.database.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {
    private static final String CONNECTION_STRING = MySqlConfig.getString();
    private static volatile Connection instance;


    private MySqlConnection() {
    }

    private static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(MySqlConnection.CONNECTION_STRING);
    }

    public static Connection getInstance() {
        Connection result = instance;
        if (result != null) {
            return result;
        }
        synchronized (MySqlConnection.class) {
            if (instance == null) {
                try {
                    instance = createConnection();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }

            return instance;
        }
    }
}

package cat.itacademy.escaperoom.escaperoom.infrastructure;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlTestConnection {
    private static final String CONNECTION_STRING = MySqlTestConfig.getString();
    private static volatile Connection instance;


    private MySqlTestConnection() {
    }

    private static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(MySqlTestConnection.CONNECTION_STRING);
    }

    public static Connection getInstance() {
        Connection result = instance;
        if (result != null) {
            return result;
        }
        synchronized (MySqlTestConnection.class) {
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

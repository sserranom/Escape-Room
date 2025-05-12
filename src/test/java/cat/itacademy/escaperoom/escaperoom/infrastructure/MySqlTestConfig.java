package cat.itacademy.escaperoom.escaperoom.infrastructure;

import io.github.cdimascio.dotenv.Dotenv;

public class MySqlTestConfig {
    private static final Dotenv dotenv = Dotenv.load();
    private static final String DB_DATABASE = dotenv.get("MYSQL_DATABASE");
    private static final String DB_USER = dotenv.get("MYSQL_USER");
    private static final String DB_PORT = dotenv.get("MYSQL_TEST_PORT");
    private static final String DB_PASSWORD = dotenv.get("MYSQL_PASSWORD");
    private static final String DB_HOST = dotenv.get("MYSQL_HOST");
    private static final String CONNECTION_STRING = String.format("jdbc:mysql://%s:%s/%s?" +
            "user=%s&password=%s", DB_HOST, DB_PORT, DB_DATABASE, DB_USER, DB_PASSWORD);

    public static String getString() {
        return CONNECTION_STRING;
    }

}

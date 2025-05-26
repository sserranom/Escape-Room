package cat.itacademy.project.api;

import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.sql.Connection;

public class Controllers {
    private static final Connection mySqlConnection = MySqlConnection.getInstance();
}

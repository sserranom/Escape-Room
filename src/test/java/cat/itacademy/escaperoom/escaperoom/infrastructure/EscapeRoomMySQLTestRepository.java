package cat.itacademy.escaperoom.escaperoom;

import cat.itacademy.project.escaperoom.infrastructure.EscapeRoomMySQLRepository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EscapeRoomMySQLTestRepository extends EscapeRoomMySQLRepository {
    public EscapeRoomMySQLTestRepository() {
        super();
    }

    public void restore() {
        String sql = "DELETE FROM escape_rooms";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}


package cat.itacademy.escaperoom.escaperoom;

import cat.itacademy.escaperoom.database.mysql.MySqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class EscapeRoomRepository  {

    private final Connection connection;

    public EscapeRoomRepository() {
        this.connection = MySqlConnection.getInstance();
    }

    public void create(EscapeRoom  escapeRoom)  {
        String sql = "INSERT INTO escape_rooms (name, url) VALUES (?, ?)";
        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, escapeRoom.getName());
            preparedStatement.setString(2, escapeRoom.getUrl());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error saving escape room: " + e.getMessage());
        }
    }

    public void update( EscapeRoom escapeRoom) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void findById(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public EscapeRoom findByName(String name) {
        String sql = "SELECT * FROM escape_rooms WHERE name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // Set the "name" parameter in the SQL query
            preparedStatement.setString(1, name);

            // Execute the query
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return EscapeRoom.fromDatabase(rs.getInt("id"), rs.getString("name"), rs.getString("url"));
            }
        } catch (Exception e) {
            System.out.println("Error while finding escape room: " + e.getMessage());
        }
        return null;
    }
}

package cat.itacademy.project.business_logic.escaperoom.infrastructure;

import cat.itacademy.project.business_logic.escaperoom.domain.EscapeRoom;
import cat.itacademy.project.business_logic.escaperoom.domain.EscapeRoomRepository;
import cat.itacademy.project.shared.domain.dtos.EscapeRoomDTO;
import cat.itacademy.project.shared.domain.exceptions.DatabaseException;
import cat.itacademy.project.shared.domain.exceptions.NotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EscapeRoomMySQLRepository implements EscapeRoomRepository {

    protected final Connection connection;

    public EscapeRoomMySQLRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(EscapeRoom escapeRoom) {
        String sql = "INSERT INTO escape_rooms (name, url) VALUES (?, ?)";
        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, escapeRoom.getName());
            preparedStatement.setString(2, escapeRoom.getUrl());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new DatabaseException("Error saving escape room: " + e.getMessage());
        }
    }

    @Override
    public void update(EscapeRoom escapeRoom)  {
        String sql = "UPDATE escape_rooms Set name = ?, url = ? WHERE id = ?";
        try (var preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, escapeRoom.getName());
            preparedStatement.setString(2, escapeRoom.getUrl());
            preparedStatement.setInt(3, escapeRoom.getId());
            int rowUpdated = preparedStatement.executeUpdate();
            if (rowUpdated == 0){
                throw new NotFoundException("Escape room with ID " + escapeRoom.getId() + " not found.");
            }
        }catch (SQLException | NotFoundException e){
            throw new DatabaseException("Error updating escape room: " + e.getMessage());
        }

    }

    @Override
    public Optional<Void> delete(int id) {
        String sql = "DELETE FROM escape_rooms WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted == 0) {
                throw new NotFoundException("Escape room with ID " + id + " not found.");
            }
            return Optional.empty(); // Indicación explícita de eliminación exitosa
//            if (rowsDeleted > 0) {
//                return Optional.of(true); // Indicación explícita de eliminación exitosa
//            } else {
//                return Optional.of(false); // Indicación explícita de no encontrado
//            }
        } catch (SQLException e) {
            throw new DatabaseException("Error deleting escape room: " + e.getMessage());
        }
    }

    @Override
    public Optional<EscapeRoom> findById(int id) {
        String sql = "SELECT id, name, url FROM escape_rooms WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return Optional.of(EscapeRoom.fromDatabase(
                        new EscapeRoomDTO(rs.getInt("id"), rs.getString("name"), rs.getString("url"))
                ));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error finding escape room by id: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<EscapeRoom> findAll() {
        String sql = "SELECT  * FROM escape_rooms";
        List<EscapeRoom> escapeRooms = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                escapeRooms.add(EscapeRoom.fromDatabase(
                        new EscapeRoomDTO(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("url")
                        )
                ));
            }
        } catch (Exception e) {
            throw new DatabaseException("Error while finding all escape rooms: " + e.getMessage());
        }
        return escapeRooms;
    }

    @Override
    public Optional<EscapeRoom> findByName(String name) {
        String sql = "SELECT * FROM escape_rooms WHERE name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // Set the "name" parameter in the SQL query
            preparedStatement.setString(1, name);

            // Execute the query
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return Optional.of(EscapeRoom.fromDatabase(
                        new EscapeRoomDTO(rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("url")
                        )));
            }
        } catch (Exception e) {
            throw new DatabaseException("Error while finding escape room: " + e.getMessage());
        }
        return Optional.empty();
    }
}

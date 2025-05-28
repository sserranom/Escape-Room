package cat.itacademy.project.business_logic.room.infraestructure;

import cat.itacademy.project.business_logic.room.domain.Room;
import cat.itacademy.project.business_logic.room.domain.RoomRepository;
import cat.itacademy.project.shared.domain.dtos.room.CreateRoomDTO;
import cat.itacademy.project.shared.domain.dtos.room.RoomDTO;
import cat.itacademy.project.shared.domain.exceptions.DatabaseException;
import cat.itacademy.project.shared.domain.exceptions.NotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomMySQLRepository implements RoomRepository {

    private final Connection connection;

    public RoomMySQLRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(CreateRoomDTO room) {
        String sql = "INSERT INTO rooms (name, price,difficulty,  theme_id) VALUES (?, ?, ?, ? )";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, room.name());
            preparedStatement.setDouble(2, room.price());
            preparedStatement.setString(3, room.difficulty());
            preparedStatement.setInt(4, room.themeId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("Error creating room: " + e.getMessage());
        }
    }


    @Override
    public void update(Room room) {
        String sql = "UPDATE rooms SET name = ?, price = ?, difficulty = ?, theme_id = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, room.getName());
            preparedStatement.setDouble(2, room.getPrice());
            preparedStatement.setString(3, room.getDifficulty());
            preparedStatement.setInt(4, room.getTheme_id());
            preparedStatement.setInt(5, room.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("Error updating room: " + e.getMessage());
        }

    }

    @Override
    public Optional<Void> delete(int id) {
        String sql = "DELETE FROM rooms WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int rowDeleted = preparedStatement.executeUpdate();
            if (rowDeleted == 0) {
                throw new NotFoundException("Escape room with ID " + id + " not found.");
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DatabaseException("Error deleting room: " + e.getMessage());
        }

    }

    @Override
    public Optional<RoomDTO> findById(int id) {
        String sql = "SELECT r.id, r.name, r.difficulty, r.price, r.theme_id, t.name AS themeName " +
                "FROM rooms r " +
                "JOIN themes t ON r.theme_id = t.id " +
                "WHERE r.id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return Optional.of(new RoomDTO(rs.getInt("id"), rs.getString("name"), rs.getString("difficulty"), rs.getDouble("price"), rs.getInt("theme_id"), rs.getString("themeName")));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error finding room by id: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<RoomDTO> findAll() {
        List<RoomDTO> rooms = new ArrayList<>();
        String sql = "SELECT r.id, r.name, r.difficulty, r.price, r.theme_id, t.name AS themeName " +
                "FROM rooms r " +
                "JOIN themes t ON r.theme_id = t.id ";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                rooms.add(
                        new RoomDTO(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("difficulty"),
                                rs.getDouble("price"),
                                rs.getInt("theme_id"),
                                rs.getString("themeName")

                        ));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error finding all rooms: " + e.getMessage());
        }
        return rooms;
    }

    @Override
    public Optional<RoomDTO> findByName(String name) {
        String sql = "SELECT r.id, r.name, r.difficulty, r.price, r.theme_id, t.name AS themeName " +
                "FROM rooms r " +
                "JOIN themes t ON r.theme_id = t.id " +
                "WHERE r.name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return Optional.of(
                        new RoomDTO(rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("difficulty"),
                                rs.getDouble("price"),
                                rs.getInt("theme_id"),
                                rs.getString("themeName")
                        ));
            }
        } catch (Exception e) {
            throw new DatabaseException("Error while finding room: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<RoomDTO> findAllByThemerId(int themeId) {
        List<RoomDTO> rooms = new ArrayList<>();

        String sql = "SELECT r.id, r.name, r.difficulty, r.price, r.theme_id, t.name AS themeName " +
                "FROM rooms r " +
                "JOIN themes t ON r.theme_id = t.id " +
                "WHERE r.theme_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, themeId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                rooms.add(
                        new RoomDTO(rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("difficulty"),
                                rs.getDouble("price"),
                                rs.getInt("theme_id"),
                                rs.getString("themeName")
                        ));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error finding rooms by theme id: " + e.getMessage());
        }
        return rooms;
    }

    public List<RoomDTO> findAllByEscaperoomId(int escapeRoomId) {
        List<RoomDTO> rooms = new ArrayList<>();
        String sql = "SELECT r.id, r.name, r.difficulty, r.price, r.theme_id, t.name AS themeName " +
                "FROM rooms r " +
                "JOIN themes t ON r.theme_id = t.id " +
                "JOIN  escape_rooms e ON t.escaperoom_id = e.id " +
                "WHERE e.id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, escapeRoomId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                rooms.add(
                        new RoomDTO(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("difficulty"),
                                rs.getDouble("price"),
                                rs.getInt("theme_id"),
                                rs.getString("themeName")
                        ));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error finding all rooms by escaperoom id: " + e.getMessage());
        }
        return rooms;
    }
}

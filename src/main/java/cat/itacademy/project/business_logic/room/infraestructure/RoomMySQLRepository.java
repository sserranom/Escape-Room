package cat.itacademy.project.business_logic.room.infraestructure;

import cat.itacademy.project.business_logic.room.domain.Room;
import cat.itacademy.project.business_logic.room.domain.RoomRepository;
import cat.itacademy.project.shared.domain.dtos.RoomDTO;
import cat.itacademy.project.shared.domain.exceptions.DatabaseException;
import cat.itacademy.project.shared.domain.exceptions.NotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomMySQLRepository implements RoomRepository {

    private final Connection connection;

    public RoomMySQLRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Room room) {
        String sql = "INSERT INTO rooms (name, price, escaperoom_id) VALUES (?, ?, ? )";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, room.getName());
            preparedStatement.setDouble(2, room.getPrice());
            preparedStatement.setInt(3, room.getEscapeRoomId());
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            throw new DatabaseException("Error creating room: " + e.getMessage());
        }
    }


    @Override
    public void update(Room room) {
        String sql = "UPDATE rooms SET name = ?, price = ?, escaperoo_id = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, room.getName());
            preparedStatement.setDouble(2, room.getPrice());
            preparedStatement.setInt(3, room.getEscapeRoomId());
            preparedStatement.setInt(4, room.getId());
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            throw new DatabaseException("Error updating room: " + e.getMessage());
        }

    }

    @Override
    public Optional<Object> delete(int id) {
        String sql = "DELETE FROM rooms WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, id);
            int rowDeleted = preparedStatement.executeUpdate();
            if (rowDeleted == 0) {
                    throw new NotFoundException("Escape room with ID " + id + " not found.");
            }
            return Optional.empty();
        }catch (SQLException e){
            throw new DatabaseException("Error deleting room: " + e.getMessage());
        }

    }

    @Override
    public Optional<Room> findById(int id) {
        String sql = "SELECT id, name, price, escaperoom_id FROM rooms WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return Optional.of(new Room(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"), rs.getInt("escaperoom_id")));
            }
        }catch (SQLException e){
            throw new DatabaseException("Error finding room by id: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Room> findAll() {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT id, name, price, escaperoom_id FROM rooms";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                rooms.add(Room.fromDatabase(
                        new RoomDTO(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getDouble("price"),
                                rs.getInt("escaperoom_id")
                        )
                ));
            }
        }catch (SQLException e){
            throw new DatabaseException("Error finding all rooms: " + e.getMessage());
        }
        return rooms;
    }

    @Override
    public Optional<Room> findByName(String name) {
        String sql = "SELECT *FROM rooms WHERE name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()){
                return Optional.of(Room.fromDatabase(
                        new RoomDTO(rs.getInt("id"),
                                rs.getString("name"),
                                rs.getDouble("price"),
                                rs.getInt("escapeRoomId")
                        )));
            }
        }catch (Exception e){
            throw  new DatabaseException("Error while finding room: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Room> findAllByEscapeRoomId(int escapeRoomId) {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT id, name, price, escaperoom_id FROM rooms WHERE escaperoom_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, escapeRoomId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                return Optional.of(Room.fromDatabase(
                        new RoomDTO(rs.getInt("id"),
                                rs.getString("name"),
                                rs.getDouble("price"),
                                rs.getInt(escapeRoomId)
                        )));
            }
        }catch (Exception e){
            throw new DatabaseException("Error finding rooms by escaperoom id: " + e.getMessage());
        }
        return Optional.empty();
    }
}

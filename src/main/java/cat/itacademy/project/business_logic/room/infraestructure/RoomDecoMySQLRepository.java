package cat.itacademy.project.business_logic.room.infraestructure;

import cat.itacademy.project.business_logic.room.domain.RoomDecoRepository;
import cat.itacademy.project.shared.domain.dtos.deco.DecoDTO;
import cat.itacademy.project.shared.domain.dtos.room.AssignDecoToRoomDto;
import cat.itacademy.project.shared.domain.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDecoMySQLRepository implements RoomDecoRepository {
    Connection connection;

    public RoomDecoMySQLRepository(Connection connection) {
        this.connection = connection;

    }

    @Override
    public void assignDecoToRoom(AssignDecoToRoomDto request) {
        String sql = "INSERT INTO deco_inventory (room_id, deco_id, escaperoom_id) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, request.roomId());
            preparedStatement.setInt(2, request.decoId());
            preparedStatement.setInt(3, request.escapeRoomId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error assigning deco to room: " + e.getMessage());
        }
    }

    @Override
    public void removeDecoFromRoom(int roomId, int decoId) {
        String sql = "DELETE FROM deco_inventory WHERE room_id = ? AND deco_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, roomId);
            preparedStatement.setInt(2, decoId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error removing deco from room: " + e.getMessage());
        }
    }

    @Override
    public List<DecoDTO> findDecosByRoomId(int roomId) {
        List<DecoDTO> decos = new ArrayList<>();
        String sql = "SELECT d.id as id," +
                " d.name as name, " +
                "d.description as description, " +
                "d.type as type, " +
                "d.escaperoom_id as escaperoom_id, " +
                "d.price as price FROM deco_inventory di " +
                "JOIN deco d ON di.deco_id = d.id WHERE di.room_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, roomId);
            //(int id, String name, String description, String type, int escapeRoomId, double price)
            var rs = preparedStatement.executeQuery();
            while (rs.next()) {
                DecoDTO deco = new DecoDTO(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("type"),
                        rs.getInt("escaperoom_id"),
                        rs.getDouble("price")
                );
                decos.add(deco);
            }
            return decos;
        } catch (SQLException e) {
            throw new DatabaseException("Error finding decos by room ID: " + e.getMessage());
        }

    }
}

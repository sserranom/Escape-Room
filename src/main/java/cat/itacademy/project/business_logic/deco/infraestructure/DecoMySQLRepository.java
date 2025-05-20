package cat.itacademy.project.business_logic.deco.infraestructure;

import cat.itacademy.project.business_logic.deco.domain.Deco;
import cat.itacademy.project.business_logic.deco.domain.DecoRepository;
import cat.itacademy.project.business_logic.room.domain.Room;
import cat.itacademy.project.shared.domain.dtos.deco.CreateDecoDTO;
import cat.itacademy.project.shared.domain.dtos.deco.DecoDTO;
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

public class DecoMySQLRepository implements DecoRepository {

    private final Connection connection;

    public DecoMySQLRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(CreateDecoDTO deco) {

        String sql = "INSERT INTO deco (name, description, type, escaperoom_id, price) VALUES (?, ?, ?, ?, ? )";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, deco.name());
            preparedStatement.setString(2, deco.description());
            preparedStatement.setString(3, String.valueOf(deco.type()));
            preparedStatement.setInt(4, deco.escapeRoomId());
            preparedStatement.setDouble(5, deco.price());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("Error creating Deco: " + e.getMessage());
        }

    }

    @Override
    public void update(Deco deco) {

        String sql = "UPDATE deco SET name = ?, description = ?, type = ?, escaperoom_id = ?, price = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, deco.getName());
            preparedStatement.setString(2, deco.getDescription());
            preparedStatement.setString(3, String.valueOf(deco.getType()));
            preparedStatement.setInt(4, deco.getEscapeRoomId());
            preparedStatement.setDouble(5, deco.getPrice());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("Error updating deco: " + e.getMessage());
        }
    }

    @Override
    public Optional<Void> delete(int id) {
        String sql = "DELETE FROM deco WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int rowDeleted = preparedStatement.executeUpdate();
            if (rowDeleted == 0) {
                throw new NotFoundException("Deco with ID " + id + " not found.");
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DatabaseException("Error deleting deco: " + e.getMessage());
        }
    }

    @Override
    public Optional<DecoDTO> findById(int id) {
        String sql = "SELECT id, name, description, type, escaperoom_id, price FROM deco WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return Optional.of(new DecoDTO(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getString("type"), rs.getInt("escaperoom_id"), rs.getDouble("price")));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error finding room by id: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<DecoDTO> findAll() {
        List<DecoDTO> decoObjs = new ArrayList<>();
        String sql = "SELECT id, name, description, type, escaperoom_id, price FROM rooms";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                decoObjs.add(
                        new DecoDTO(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("description"),
                                String.valueOf(rs.getType()),
                                rs.getInt("escaperoom_id"),
                                rs.getDouble("price")

                        ));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error finding all decoObjs: " + e.getMessage());
        }
        return decoObjs;
    }

    @Override
    public Optional<DecoDTO> findByName(String name) {
        String sql = "SELECT * FROM deco WHERE name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return Optional.of(
                        new DecoDTO(rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("description"),
                                String.valueOf(rs.getType()),
                                rs.getInt("escaperrom_id"),
                                rs.getDouble("price")

                        ));
            }
        } catch (Exception e) {
            throw new DatabaseException("Error while finding deco: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Deco> findAllByEscapeRoomId(int escapeRoomId) {
        List<Deco> decoObjs = new ArrayList<>();
        String sql = "SELECT id, name, description, type, escaperoom_id, price FROM deco WHERE escaperoom_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, escapeRoomId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                return Optional.of(Deco.fromDatabase(
                        new DecoDTO(rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("description"),
                                rs.getString("type"),
                                rs.getInt(escapeRoomId),
                                rs.getDouble("price")

                        )));
            }
        } catch (Exception e) {
            throw new DatabaseException("Error finding decoObjs by escaperoom id: " + e.getMessage());
        }
        return Optional.empty();
    }
}

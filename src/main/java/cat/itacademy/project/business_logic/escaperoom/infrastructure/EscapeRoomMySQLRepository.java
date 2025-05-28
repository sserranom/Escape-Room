package cat.itacademy.project.business_logic.escaperoom.infrastructure;

import cat.itacademy.project.business_logic.deco.infraestructure.DecoMySQLRepository;
import cat.itacademy.project.business_logic.escaperoom.domain.EscapeRoom;
import cat.itacademy.project.business_logic.escaperoom.domain.EscapeRoomRepository;
import cat.itacademy.project.business_logic.puzzle.infrastructure.PuzzleMySQLRepository;
import cat.itacademy.project.business_logic.room.infraestructure.RoomMySQLRepository;
import cat.itacademy.project.shared.domain.dtos.deco.DecoDTO;
import cat.itacademy.project.shared.domain.dtos.escape_room.CreateEscapeRoomDTO;
import cat.itacademy.project.shared.domain.dtos.escape_room.EscapeRoomDTO;
import cat.itacademy.project.shared.domain.dtos.escape_room.EscapeRoomInventoryDto;
import cat.itacademy.project.shared.domain.dtos.puzzle.PuzzleDTO;
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

public class EscapeRoomMySQLRepository implements EscapeRoomRepository {

    protected final Connection connection;

    public EscapeRoomMySQLRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(CreateEscapeRoomDTO escapeRoom) {
        String sql = "INSERT INTO escape_rooms (name, url) VALUES (?, ?)";
        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, escapeRoom.name());
            preparedStatement.setString(2, escapeRoom.url());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new DatabaseException("Error saving escape room: " + e.getMessage());
        }
    }

    @Override
    public void update(EscapeRoomDTO escapeRoom) {
        String sql = "UPDATE escape_rooms Set name = ?, url = ? WHERE id = ?";
        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, escapeRoom.name());
            preparedStatement.setString(2, escapeRoom.url());
            preparedStatement.setInt(3, escapeRoom.id());
            int rowUpdated = preparedStatement.executeUpdate();
            if (rowUpdated == 0) {
                throw new NotFoundException("Escape room with ID " + escapeRoom.id() + " not found.");
            }
        } catch (SQLException | NotFoundException e) {
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
            return Optional.empty();
        } catch (SQLException e) {
            throw new DatabaseException("Error deleting escape room: " + e.getMessage());
        }
    }

    @Override
    public Optional<EscapeRoomDTO> findById(int id) {
        String sql = "SELECT id, name, url FROM escape_rooms WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return Optional.of(
                        new EscapeRoomDTO(rs.getInt("id"), rs.getString("name"), rs.getString("url"))
                );
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error finding escape room by id: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<EscapeRoomDTO> findAll() {
        String sql = "SELECT  * FROM escape_rooms";
        List<EscapeRoomDTO> escapeRooms = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                escapeRooms.add(
                        new EscapeRoomDTO(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("url")

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

    @Override
    public EscapeRoomInventoryDto findInventoryByEscapeRoomId(int escapeRoomId) {
        List<RoomDTO> rooms = new RoomMySQLRepository(this.connection).findAllByEscaperoomId(escapeRoomId);
        List<PuzzleDTO> puzzles = new PuzzleMySQLRepository(this.connection).findAllByEscapeRoomId(escapeRoomId);
        List<DecoDTO> decos = new DecoMySQLRepository(this.connection).findAllByEscapeRoomId(escapeRoomId);

        return new EscapeRoomInventoryDto(rooms, puzzles, decos);
    }
}

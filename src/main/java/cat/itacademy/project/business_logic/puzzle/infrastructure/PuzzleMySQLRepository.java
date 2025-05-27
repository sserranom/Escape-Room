package cat.itacademy.project.business_logic.puzzle.infrastructure;

import cat.itacademy.project.business_logic.puzzle.domain.Puzzle;
import cat.itacademy.project.business_logic.puzzle.domain.PuzzleRepository;
import cat.itacademy.project.shared.domain.dtos.puzzle.PuzzleDTO;
import cat.itacademy.project.shared.domain.exceptions.DatabaseException;
import cat.itacademy.project.shared.domain.exceptions.NotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PuzzleMySQLRepository implements PuzzleRepository {

    protected final Connection connection;

    public PuzzleMySQLRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Puzzle puzzle) {
        String sql = "INSERT INTO puzzles (name,  room_id, answer, story, theme_id, price) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, puzzle.getName());
            preparedStatement.setInt(2, puzzle.getRoomId());
            preparedStatement.setString(3, puzzle.getAnswer());
            preparedStatement.setString(4, puzzle.getStory());
            preparedStatement.setInt(5, puzzle.getThemeId());
            preparedStatement.setDouble(6, puzzle.getPrice());


            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new DatabaseException("Error saving escape room: " + e.getMessage());
        }
    }

    @Override
    public void update(Puzzle puzzle) {
        String sql = "UPDATE puzzles Set name = ?,  room_id = ?, answer = ?, story = ?, theme_id = ?, price = ? WHERE id = ?";

        try (var preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, puzzle.getName());
            preparedStatement.setInt(2, puzzle.getRoomId());
            preparedStatement.setString(3, puzzle.getAnswer());
            preparedStatement.setString(4, puzzle.getStory());
            preparedStatement.setInt(5, puzzle.getThemeId());
            preparedStatement.setDouble(6, puzzle.getPrice());

            int rowUpdated = preparedStatement.executeUpdate();

            if (rowUpdated == 0) {
                throw new NotFoundException("Puzzle with ID " + puzzle.getId() + " not found.");
            }

        } catch (SQLException | NotFoundException e) {
            throw new DatabaseException("Error updating puzzle: " + e.getMessage());
        }

    }

    @Override
    public Optional<Void> delete(int id) {
        String sql = "DELETE FROM puzzles WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted == 0) {
                throw new NotFoundException("Puzzle with ID " + id + " not found.");
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DatabaseException("Error deleting puzzle: " + e.getMessage());
        }
    }

    @Override
    public Optional<PuzzleDTO> findById(int id) {
        String sql = "SELECT id, name, room_id, answer, story, theme_id, price FROM puzzles WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return Optional.of(
                        new PuzzleDTO(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getInt("room_id"),
                                rs.getString("answer"),
                                rs.getString("story"),
                                rs.getInt("theme_id"),
                                rs.getDouble("price")
                        ));

            }
        } catch (SQLException e) {
            throw new DatabaseException("Error finding escape room by id: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<PuzzleDTO> findAll() {
        List<PuzzleDTO> puzzles = new ArrayList<>();
        String sql = "SELECT * FROM puzzles";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                puzzles.add(
                        new PuzzleDTO(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getInt("room_id"),
                                rs.getString("answer"),
                                rs.getString("story"),
                                rs.getInt("theme_id"),
                                rs.getDouble("price")

                        ));

            }
        } catch (Exception e) {
            throw new DatabaseException("Error while finding all escape rooms: " + e.getMessage());
        }
        return puzzles;
    }

    @Override
    public Optional<PuzzleDTO> findByName(String name) {
        String sql = "SELECT * FROM puzzles WHERE name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // Set the "name" parameter in the SQL query
            preparedStatement.setString(1, name);

            // Execute the query
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return Optional.of(
                        new PuzzleDTO(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getInt("room_id"),
                                rs.getString("answer"),
                                rs.getString("story"),
                                rs.getInt("theme_id"),
                                rs.getDouble("price")
                        ));
            }
        } catch (Exception e) {
            throw new DatabaseException("Error while finding escape room: " + e.getMessage());
        }
        return Optional.empty();
    }
    public List<PuzzleDTO> findAllByEscapeRoomId(int escapeRoomId) {
        List<PuzzleDTO> puzzles = new ArrayList<>();
        String sql = "SELECT p.id, p.name, p.theme_id, p.answer, p.story, p.price " +
                "FROM puzzles p " +
                "JOIN themes t ON p.theme_id = t.id " +
                "JOIN escape_rooms e ON t.escaperoom_id = e.id " +
                "WHERE e.id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, escapeRoomId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                puzzles.add(
                        new PuzzleDTO(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getInt("room_id") == 0 ? 0 : rs.getInt("room_id"), // Assuming room_id can be null, default to 0
                                rs.getString("answer"),
                                rs.getString("story"),
                                rs.getInt("theme_id"),
                                rs.getDouble("price")
                        ));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error finding puzzles by escape room id: " + e.getMessage());
        }
        return puzzles;
    }
}

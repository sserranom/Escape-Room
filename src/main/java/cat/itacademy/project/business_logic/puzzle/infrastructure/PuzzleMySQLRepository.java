package cat.itacademy.project.business_logic.puzzle.infrastructure;

import cat.itacademy.project.business_logic.puzzle.domain.Puzzle;
import cat.itacademy.project.business_logic.puzzle.domain.PuzzleRepository;
import cat.itacademy.project.shared.domain.dtos.PuzzleDTO;
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
        String sql = "INSERT INTO puzzles (name, difficulty, roomId, answer, story, themeId, price) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, puzzle.getName());
            preparedStatement.setString(2, puzzle.getDifficulty());
            preparedStatement.setInt(3, puzzle.getRoomId());
            preparedStatement.setString(4, puzzle.getAnswer());
            preparedStatement.setString(5, puzzle.getStory());
            preparedStatement.setInt(6, puzzle.getThemeId());
            preparedStatement.setDouble(7, puzzle.getPrice());


            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new DatabaseException("Error saving escape room: " + e.getMessage());
        }
    }

    @Override
    public void update(Puzzle puzzle)  {
        String sql = "UPDATE puzzles Set name = ?, difficulty = ?, roomId = ?, answer = ?, story = ?, themeId = ?, price = ? WHERE id = ?";

        try (var preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setString(1, puzzle.getName());
            preparedStatement.setString(2, puzzle.getDifficulty());
            preparedStatement.setInt(3, puzzle.getRoomId());
            preparedStatement.setString(4, puzzle.getAnswer());
            preparedStatement.setString(5, puzzle.getStory());
            preparedStatement.setInt(6, puzzle.getThemeId());
            preparedStatement.setDouble(7, puzzle.getPrice());

            int rowUpdated = preparedStatement.executeUpdate();

            if (rowUpdated == 0){
                throw new NotFoundException("Puzzle with ID " + puzzle.getId() + " not found.");
            }

        }catch (SQLException | NotFoundException e){
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
            return Optional.empty(); // Indicación explícita de eliminación exitosa
//            if (rowsDeleted > 0) {
//                return Optional.of(true); // Indicación explícita de eliminación exitosa
//            } else {
//                return Optional.of(false); // Indicación explícita de no encontrado
//            }
        } catch (SQLException e) {
            throw new DatabaseException("Error deleting puzzle: " + e.getMessage());
        }
    }

    @Override
    public Optional<Puzzle> findById(int id) {
        String sql = "SELECT id, name, difficulty, roomId, answer, story, themeId, price FROM puzzles WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return Optional.of(
                        new Puzzle(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("difficulty"),
                            rs.getInt("roomId"),
                            rs.getString("answer"),
                            rs.getString("story"),
                            rs.getInt("themeId"),
                            rs.getDouble("price")
                ));

            }
        } catch (SQLException e) {
            throw new DatabaseException("Error finding escape room by id: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Puzzle> findAll() {
        List<Puzzle> puzzles = new ArrayList<>();
        String sql = "SELECT * FROM puzzles";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                puzzles.add(Puzzle.fromDatabase(
                    new PuzzleDTO(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("difficulty"),
                        rs.getInt("roomId"),
                        rs.getString("answer"),
                        rs.getString("story"),
                        rs.getInt("themeId"),
                        rs.getDouble("price")
                    )
                ));

            }
        } catch (Exception e) {
            throw new DatabaseException("Error while finding all escape rooms: " + e.getMessage());
        }
        return puzzles;
    }

    @Override
    public Optional<Puzzle> findByName(String name) {
        String sql = "SELECT * FROM puzzles WHERE name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // Set the "name" parameter in the SQL query
            preparedStatement.setString(1, name);

            // Execute the query
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return Optional.of(Puzzle.fromDatabase(
                        new PuzzleDTO(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("difficulty"),
                                rs.getInt("roomId"),
                                rs.getString("answer"),
                                rs.getString("story"),
                                rs.getInt("themeId"),
                                rs.getDouble("price")
                        )));
            }
        } catch (Exception e) {
            throw new DatabaseException("Error while finding escape room: " + e.getMessage());
        }
        return Optional.empty();
    }
}

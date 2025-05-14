package cat.itacademy.project.buissness_logic.themes;

import cat.itacademy.project.shared.domain.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ThemeMySQLRepository implements ThemeRepository {

    protected final Connection connection;

    public ThemeMySQLRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Theme theme) {
        String sql = "INSERT INTO themes (name, description, escaperoom_id) VALUES (?, ?, ?)";
        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, theme.getName());
            preparedStatement.setString(2, theme.getDescription());
            preparedStatement.setInt(3, theme.getEscapeRoom().getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new DatabaseException("Error saving theme: " + e.getMessage());
        }
    }

    @Override
    public void update(Theme theme) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Optional<Theme> findById(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Theme> findAll() {
        String sql = "SELECT * FROM themes";
        List<Theme> themes = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                themes.add(Theme.fromDatabase(
                        new ThemeDTO(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("description"),
                                rs.getInt("EscapeRoomId")
                        )
                ));
            }
        } catch (Exception e) {
            throw new DatabaseException("Error while finding all themes: " + e.getMessage());
        }
        return themes;
    }

    @Override
    public Optional<Theme> findByName(String name) {
        String sql = "SELECT * FROM themes WHERE name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // Set the "name" parameter in the SQL query
            preparedStatement.setString(1, name);

            // Execute the query
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return Optional.of(Theme.fromDatabase(
                        new ThemeDTO(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("description"),
                                rs.getInt("EscapeRoomId")
                        )));
            }
        } catch (Exception e) {
            throw new DatabaseException("Error while finding theme: " + e.getMessage());
        }
        return Optional.empty();
    }
}

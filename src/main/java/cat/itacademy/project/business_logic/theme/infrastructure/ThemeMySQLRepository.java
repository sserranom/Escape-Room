package cat.itacademy.project.business_logic.theme.infrastructure;

import cat.itacademy.project.business_logic.theme.domain.Theme;
import cat.itacademy.project.business_logic.theme.domain.ThemeRepository;
import cat.itacademy.project.shared.domain.dtos.theme.CreateThemeDTO;
import cat.itacademy.project.shared.domain.dtos.theme.ThemeDTO;
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
    public void create(CreateThemeDTO theme) {
        String sql = "INSERT INTO themes (name, description, escaperoom_id) VALUES (?, ?, ?)";
        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, theme.name());
            preparedStatement.setString(2, theme.description());
            preparedStatement.setInt(3, theme.escapeRoomId());
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
    public Optional<ThemeDTO> findById(int id) {
//        throw new UnsupportedOperationException("Not supported yet.");
        String sql = "SELECT * FROM themes WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // Set the "id" parameter in the SQL query
            preparedStatement.setInt(1, id);

            // Execute the query
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return Optional.of(new ThemeDTO(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("escaperoom_id")
                ));
            }
        } catch (Exception e) {
            throw new DatabaseException("Error while finding theme: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<ThemeDTO> findAll(int escapeRoomId) {
        String sql = "SELECT id, name, description, escaperoom_id FROM themes" +
                " WHERE escaperoom_id = ?";
        List<ThemeDTO> themes = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, escapeRoomId);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                themes.add(new ThemeDTO(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("escaperoom_id")
                ));
            }
            return themes;
        } catch (Exception e) {
            throw new DatabaseException("Error while finding all themes: " + e.getMessage());
        }
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
                                rs.getInt("escaperoom_id")
                        )));
            }
        } catch (Exception e) {
            throw new DatabaseException("Error while finding theme: " + e.getMessage());
        }
        return Optional.empty();
    }
}

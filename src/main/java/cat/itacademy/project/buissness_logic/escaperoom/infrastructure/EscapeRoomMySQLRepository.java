package cat.itacademy.project.buissness_logic.escaperoom.infrastructure;

import cat.itacademy.project.buissness_logic.escaperoom.domain.EscapeRoom;
import cat.itacademy.project.buissness_logic.escaperoom.domain.EscapeRoomRepository;
import cat.itacademy.project.shared.domain.dtos.EscapeRoomDTO;
import cat.itacademy.project.shared.domain.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    public void update(EscapeRoom escapeRoom) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Optional<EscapeRoom> findById(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<EscapeRoom> findAll() {
        String sql = "SELECT  * FROM escape_rooms";
        List<EscapeRoom> escapeRooms = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()){
            while (rs.next()){
                escapeRooms.add(EscapeRoom.fromDatabase(
                        new EscapeRoomDTO(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("url")
                        )
                ));
            }
        }catch (Exception e){
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

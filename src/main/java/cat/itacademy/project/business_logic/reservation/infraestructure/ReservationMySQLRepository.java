package cat.itacademy.project.business_logic.reservation.infraestructure;

import cat.itacademy.project.business_logic.reservation.domain.Reservation;
import cat.itacademy.project.business_logic.reservation.domain.ReservationRepository;
import cat.itacademy.project.shared.domain.exceptions.DatabaseException;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservationMySQLRepository implements ReservationRepository {
    private final Connection connection;

    public ReservationMySQLRepository(Connection connection){
        this.connection = connection;
    }
    @Override
    public void create(Reservation reservation) {

        String SQL = "INSERT INTO reservations (customer_id, puzzle_id, total_price, completion_date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            if (reservation.getCustomerId() != null) {
                preparedStatement.setInt(1, reservation.getCustomerId());
            } else {
                preparedStatement.setNull(1, java.sql.Types.INTEGER);
            }
            if (reservation.getPuzzleId() != null) {
                preparedStatement.setInt(2, reservation.getPuzzleId());
            } else {
                preparedStatement.setNull(2, java.sql.Types.INTEGER);
            }
            preparedStatement.setDouble(3, reservation.getTotalPrice());
            if (reservation.getCompletionDate() != null) {
                preparedStatement.setTimestamp(4, Timestamp.valueOf(reservation.getCompletionDate()));
            } else {
                preparedStatement.setNull(4, java.sql.Types.TIMESTAMP);
            }
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                reservation.setId(generatedKeys.getInt(1));
            }
            Optional<Reservation> savedReservation = findById(reservation.getId());
            savedReservation.ifPresent(r -> reservation.setCreationDate(r.getCreationDate()));

        } catch (SQLException e) {
            throw new DatabaseException("Error creating reservation: " + e.getMessage());
        }
    }


    @Override
    public Optional<Reservation> findById(int id) {
        String SQL = "SELECT id, customer_id, puzzle_id, total_price, creation_date, completion_date FROM reservations WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return Optional.of(mapResultSetToReservation(rs));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error finding reservation by ID: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Reservation> findAll() {
        String SQL = "SELECT id, customer_id, puzzle_id, total_price, creation_date, completion_date FROM reservations";
        List<Reservation> reservations = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                reservations.add(mapResultSetToReservation(rs));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error finding all reservations: " + e.getMessage());
        }
        return reservations;
    }

    @Override
    public List<Reservation> findAllByCustomerId(int customerId) {
        String SQL = "SELECT id, customer_id, puzzle_id, total_price, creation_date, completion_date FROM reservations WHERE customer_id = ?";
        List<Reservation> reservations = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setInt(1, customerId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                reservations.add(mapResultSetToReservation(rs));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error finding reservations by customer ID: " + e.getMessage());
        }
        return reservations;
    }

    @Override
    public List<Reservation> findAllByPuzzleId(int puzzleId) {
        String SQL = "SELECT id, customer_id, puzzle_id, total_price, creation_date, completion_date FROM reservations WHERE puzzle_id = ?";
        List<Reservation> reservations = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setInt(1, puzzleId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                reservations.add(mapResultSetToReservation(rs));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error finding reservations by puzzle ID: " + e.getMessage());
        }
        return reservations;
    }

    @Override
    public void update(Reservation reservation) {
        String SQL = "UPDATE reservations SET customer_id = ?, puzzle_id = ?, total_price = ?, completion_date = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            if (reservation.getCustomerId() != null) {
                preparedStatement.setInt(1, reservation.getCustomerId());
            } else {
                preparedStatement.setNull(1, java.sql.Types.INTEGER);
            }
            if (reservation.getPuzzleId() != null) {
                preparedStatement.setInt(2, reservation.getPuzzleId());
            } else {
                preparedStatement.setNull(2, java.sql.Types.INTEGER);
            }
            preparedStatement.setDouble(3, reservation.getTotalPrice()); // Usar setDouble
            if (reservation.getCompletionDate() != null) {
                preparedStatement.setTimestamp(4, Timestamp.valueOf(reservation.getCompletionDate()));
            } else {
                preparedStatement.setNull(4, java.sql.Types.TIMESTAMP);
            }
            preparedStatement.setInt(5, reservation.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error updating reservation: " + e.getMessage());
        }
    }

    @Override
    public Optional<Void> delete(int id) {
        String SQL = "DELETE FROM reservations WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return Optional.empty();
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DatabaseException("Error deleting reservation: " + e.getMessage());
        }
    }

    private Reservation mapResultSetToReservation(ResultSet rs) throws SQLException {

        Integer customerId = rs.getObject("customer_id", Integer.class);
        Integer puzzleId = rs.getObject("puzzle_id", Integer.class);

        Timestamp creationTimestamp = rs.getTimestamp("creation_date");
        LocalDateTime creationDate = (creationTimestamp != null) ? creationTimestamp.toLocalDateTime() : null;

        Timestamp completionTimestamp = rs.getTimestamp("completion_date");
        LocalDateTime completionDate = (completionTimestamp != null) ? completionTimestamp.toLocalDateTime() : null;

        return new Reservation(
                rs.getInt("id"),
                customerId,
                puzzleId,
                rs.getDouble("total_price"),
                creationDate,
                completionDate
        );
    }
}

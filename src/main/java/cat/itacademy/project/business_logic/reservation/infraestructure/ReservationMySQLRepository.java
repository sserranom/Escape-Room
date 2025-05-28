package cat.itacademy.project.business_logic.reservation.infraestructure;

import cat.itacademy.project.business_logic.reservation.domain.Reservation;
import cat.itacademy.project.business_logic.reservation.domain.ReservationRepository;
import cat.itacademy.project.shared.domain.dtos.reservation.ReservationDTO;
import cat.itacademy.project.shared.domain.exceptions.DatabaseException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservationMySQLRepository implements ReservationRepository {
    private final Connection connection;

    public ReservationMySQLRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Reservation reservation) {
        String SQL = "INSERT INTO reservations (customer_id, puzzle_id, total_price, creation_date, completion_date) VALUES (?, ?, ?, ?, ?)";
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
            preparedStatement.setTimestamp(4, Timestamp.valueOf(reservation.getCreationDate().atStartOfDay()));
            if (reservation.getCompletionDate() != null) {
                preparedStatement.setTimestamp(5, Timestamp.valueOf(reservation.getCompletionDate().atStartOfDay()));
            } else {
                preparedStatement.setNull(5, java.sql.Types.TIMESTAMP);
            }
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                reservation.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error creating reservation: " + e.getMessage());
        }
    }

    @Override
    public Optional<ReservationDTO> findById(int id) {
        String sql = "SELECT " +
                "r.id, " +
                "r.customer_id, " +
                "c.name AS customer_name, " +
                "r.puzzle_id, " +
                "p.name AS puzzle_name, " +
                "r.total_price, " +
                "r.creation_date, " +
                "r.completion_date " +
                "FROM reservations r " +
                "LEFT JOIN customers c ON r.customer_id = c.id " +
                "LEFT JOIN puzzles p ON r.puzzle_id = p.id " +
                "WHERE r.id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return Optional.of(mapResultSetToReservationDTO(rs));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error finding reservation by id: " + e.getMessage());
        }
        return Optional.empty();
    }


    @Override
    public List<ReservationDTO> findAll() {
        List<ReservationDTO> reservations = new ArrayList<>();
        String sql = "SELECT " +
                "r.id, " +
                "r.customer_id, " +
                "c.name AS customer_name, " +
                "r.puzzle_id, " +
                "p.name AS puzzle_name, " +
                "r.total_price, " +
                "r.creation_date, " +
                "r.completion_date " +
                "FROM reservations r " +
                "LEFT JOIN customers c ON r.customer_id = c.id " +
                "LEFT JOIN puzzles p ON r.puzzle_id = p.id";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                reservations.add(mapResultSetToReservationDTO(rs));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error finding all reservations: " + e.getMessage());
        }
        return reservations;
    }

    @Override
    public Optional<List<ReservationDTO>> findAllByCustomerId(int customerId) {
        List<ReservationDTO> reservations = new ArrayList<>();
        String sql = "SELECT " +
                "r.id, " +
                "r.customer_id, " +
                "c.name AS customer_name, " +
                "r.puzzle_id, " +
                "p.name AS puzzle_name, " +
                "r.total_price, " +
                "r.creation_date, " +
                "r.completion_date " +
                "FROM reservations r " +
                "LEFT JOIN customers c ON r.customer_id = c.id " +
                "LEFT JOIN puzzles p ON r.puzzle_id = p.id " +
                "WHERE r.customer_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, customerId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                reservations.add(mapResultSetToReservationDTO(rs));
            }

            if (reservations.isEmpty()) {
                return Optional.empty();
            } else {
                return Optional.of(reservations);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error while finding reservations by Customer ID: " + e.getMessage());
        }
    }

    @Override
    public Optional<List<ReservationDTO>> findAllByPuzzleId(int puzzleId) {
        List<ReservationDTO> reservations = new ArrayList<>();
        String sql = "SELECT " +
                "r.id, " +
                "r.customer_id, " +
                "c.name AS customer_name, " +
                "r.puzzle_id, " +
                "p.name AS puzzle_name, " +
                "r.total_price, " +
                "r.creation_date, " +
                "r.completion_date " +
                "FROM reservations r " +
                "LEFT JOIN customers c ON r.customer_id = c.id " +
                "LEFT JOIN puzzles p ON r.puzzle_id = p.id " +
                "WHERE r.puzzle_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, puzzleId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                reservations.add(mapResultSetToReservationDTO(rs));
            }

            if (reservations.isEmpty()) {
                return Optional.empty();
            } else {
                return Optional.of(reservations);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error finding reservations by puzzle ID: " + e.getMessage());
        }
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
            preparedStatement.setDouble(3, reservation.getTotalPrice());
            if (reservation.getCompletionDate() != null) {
                preparedStatement.setTimestamp(4, Timestamp.valueOf(reservation.getCompletionDate().atStartOfDay()));
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
    public void delete(int id) {
        String SQL = "DELETE FROM reservations WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error deleting reservation: " + e.getMessage());
        }
    }

    private ReservationDTO mapResultSetToReservationDTO(ResultSet rs) throws SQLException {
        Timestamp creationTimestamp = rs.getTimestamp("creation_date");
        LocalDate creationDate = (creationTimestamp != null) ? LocalDate.from(creationTimestamp.toLocalDateTime()) : null;

        Timestamp completionTimestamp = rs.getTimestamp("completion_date");
        LocalDate completionDate = (completionTimestamp != null) ? LocalDate.from(completionTimestamp.toLocalDateTime()) : null;

        Integer customerId = rs.getObject("customer_id", Integer.class);
        String customerName = rs.getString("customer_name");
        if (rs.wasNull()) customerName = null;

        Integer puzzleId = rs.getObject("puzzle_id", Integer.class);
        String puzzleName = rs.getString("puzzle_name");
        if (rs.wasNull()) puzzleName = null;

        return new ReservationDTO(
                rs.getInt("id"),
                customerId,
                customerName,
                puzzleId,
                puzzleName,
                rs.getDouble("total_price"),
                creationDate,
                completionDate
        );
    }
}

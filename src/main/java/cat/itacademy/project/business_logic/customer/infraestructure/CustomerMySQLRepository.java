package cat.itacademy.project.business_logic.customer.infraestructure;

import cat.itacademy.project.business_logic.customer.domain.Customer;
import cat.itacademy.project.business_logic.customer.domain.CustomerRepository;
import cat.itacademy.project.shared.domain.dtos.customer.CreateCustomerDTO;
import cat.itacademy.project.shared.domain.dtos.customer.CustomerDTO;
import cat.itacademy.project.shared.domain.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerMySQLRepository implements CustomerRepository {
    private final Connection connection;

    public CustomerMySQLRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(CreateCustomerDTO customer) {
        String sql = "INSERT INTO customers (name, email, is_subscribed) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, customer.name());
            preparedStatement.setString(2, customer.email());
            preparedStatement.setBoolean(3, customer.isSubscribed());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error creating Customer: " + e.getMessage());
        }
    }

    @Override
    public void update(Customer customer) {
        String sql = "UPDATE customers set name = ?, email = ?, is_subscribed = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getEmail());
            preparedStatement.setBoolean(3, customer.getSubscribed());
            preparedStatement.setInt(4, customer.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error updating customer: " + e.getMessage());
        }
    }

    @Override
    public Optional<CustomerDTO> findById(int id) {
        String sql = "SELECT id, name, email, is_subscribed FROM customers WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return Optional.of(new CustomerDTO(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getBoolean("is_subscribed")));
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error finding customer by id: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<CustomerDTO> findAll() {
        List<CustomerDTO> customers = new ArrayList<>();
        String sql = "SELECT id, name, email, is_subscribed FROM customers";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                customers.add(new CustomerDTO(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getBoolean("is_subscribed")
                ));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error findig all customer: " + e.getMessage());
        }
        return customers;
    }
    @Override
    public Optional<CustomerDTO> findByName(String name) {
        String sql = "SELECT * FROM customers WHERE name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return Optional.of(new CustomerDTO(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getBoolean("is_subscribed")
                ));
            }
        } catch (Exception e) {
            throw new DatabaseException("Error while finding customer: " + e.getMessage());
        }
        return Optional.empty();
    }


    @Override
    public Optional<CustomerDTO> findByEmail(String email) {
        String sql = "SELECT * FROM customers WHERE email = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return Optional.of(new CustomerDTO(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getBoolean("is_subscribed")
                ));
            }
        } catch (Exception e) {
            throw new DatabaseException("Error while finding customer: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<CustomerDTO> FindAllCustomerSubscribed() {
        List<CustomerDTO> customers = new ArrayList<>();
        String sql = "SELECT id, name, email, is_subscribed FROM customers WHERE is_subscribed = true";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                customers.add(new CustomerDTO(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getBoolean("is_subscribed")
                ));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error findig all customer: " + e.getMessage());
        }
        return customers;
    }
}

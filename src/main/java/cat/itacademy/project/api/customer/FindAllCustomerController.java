package cat.itacademy.project.api.customer;

import cat.itacademy.project.business_logic.customer.application.FindAllCustomerService;
import cat.itacademy.project.business_logic.customer.domain.CustomerRepository;
import cat.itacademy.project.business_logic.customer.infraestructure.CustomerMySQLRepository;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.shared.domain.dtos.customer.CustomerDTO;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.List;
import java.util.Optional;

public class FindAllCustomerController {
    private final FindAllCustomerService service;

    public FindAllCustomerController() {
        CustomerRepository repo = new CustomerMySQLRepository(MySqlConnection.getInstance());
        this.service = new FindAllCustomerService(repo);
    }

    public Optional<List<CustomerDTO>> execute() {
        List<CustomerDTO> customers = service.findAll();
        return Optional.of(customers);
    }
}

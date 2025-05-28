package cat.itacademy.project.api.customer;

import cat.itacademy.project.business_logic.customer.application.FindAllCustomerSubscribedService;
import cat.itacademy.project.business_logic.customer.domain.CustomerRepository;
import cat.itacademy.project.business_logic.customer.infraestructure.CustomerMySQLRepository;
import cat.itacademy.project.shared.domain.dtos.customer.CustomerDTO;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.List;
import java.util.Optional;

public class FindAllCustomerSubscribedController {
    private final FindAllCustomerSubscribedService service;

    public FindAllCustomerSubscribedController() {
        CustomerRepository repo = new CustomerMySQLRepository(MySqlConnection.getInstance());
        this.service = new FindAllCustomerSubscribedService(repo);
    }

    public Optional<List<CustomerDTO>> execute() {
        List<CustomerDTO> customers = service.execute();
        return Optional.of(customers);
    }
}

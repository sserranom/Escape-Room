package cat.itacademy.project.api.customer;

import cat.itacademy.project.business_logic.customer.application.CreateCustomerService;
import cat.itacademy.project.business_logic.customer.domain.CustomerRepository;
import cat.itacademy.project.business_logic.customer.infraestructure.CustomerMySQLRepository;
import cat.itacademy.project.shared.domain.dtos.customer.CreateCustomerDTO;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.Optional;

public class CreateCustomerController  implements Command<Void> {
    private final CreateCustomerService service;

    public CreateCustomerController(CreateCustomerDTO createCustomerDTO) {
        CustomerRepository repo = new CustomerMySQLRepository(MySqlConnection.getInstance());
        this.service = new CreateCustomerService(createCustomerDTO, repo);
    }

    @Override
    public Optional<Void> execute() {
        service.execute();
        return Optional.empty();
    }
}

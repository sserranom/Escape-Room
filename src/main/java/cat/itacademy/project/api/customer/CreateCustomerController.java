package cat.itacademy.project.api.customer;

import cat.itacademy.project.business_logic.customer.application.CreateCustomerService;
import cat.itacademy.project.business_logic.customer.domain.CustomerRepository;
import cat.itacademy.project.business_logic.customer.infraestructure.CustomerMySQLRepository;
import cat.itacademy.project.shared.domain.dtos.customer.CreateCustomerDTO;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

public class CreateCustomerController {
    private final CreateCustomerService service;

    public CreateCustomerController() {
        CustomerRepository repo = new CustomerMySQLRepository(MySqlConnection.getInstance());
        this.service = new CreateCustomerService(repo);
    }

    public void execute(CreateCustomerDTO createCustomerDTO) {
        service.execute(createCustomerDTO);
    }
}

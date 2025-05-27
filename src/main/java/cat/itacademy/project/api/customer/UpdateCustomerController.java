package cat.itacademy.project.api.customer;

import cat.itacademy.project.business_logic.customer.application.UpdateCustomerService;
import cat.itacademy.project.business_logic.customer.domain.CustomerRepository;
import cat.itacademy.project.business_logic.customer.infraestructure.CustomerMySQLRepository;
import cat.itacademy.project.shared.domain.dtos.customer.UpdateCustomerDTO;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.Optional;

public class UpdateCustomerController {
    private final UpdateCustomerService service;

    public UpdateCustomerController() {
        CustomerRepository repo = new CustomerMySQLRepository(MySqlConnection.getInstance());
        this.service = new UpdateCustomerService(repo);
    }

    public void execute(UpdateCustomerDTO request) {
        service.execute(request);
    }
}

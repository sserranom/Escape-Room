package cat.itacademy.project.api.customer;

import cat.itacademy.project.business_logic.customer.application.FindCustomerByIdService;
import cat.itacademy.project.business_logic.customer.domain.CustomerRepository;
import cat.itacademy.project.business_logic.customer.infraestructure.CustomerMySQLRepository;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.shared.domain.dtos.customer.CustomerDTO;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.Optional;

public class FindCustomerByIdController {
    private final FindCustomerByIdService service;

    public FindCustomerByIdController() {
        CustomerRepository repo = new CustomerMySQLRepository(MySqlConnection.getInstance());
        this.service = new FindCustomerByIdService(repo);
    }

    public Optional<CustomerDTO> execute(int id) {

            return service.execute(id);
        }

}

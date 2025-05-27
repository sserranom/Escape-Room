package cat.itacademy.project.api.customer;

import cat.itacademy.project.business_logic.customer.application.FindCustomerByEmailService;
import cat.itacademy.project.business_logic.customer.domain.CustomerRepository;
import cat.itacademy.project.business_logic.customer.infraestructure.CustomerMySQLRepository;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.shared.domain.dtos.customer.CustomerDTO;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.Optional;

public class FindCustomerByEmailController {
    private final FindCustomerByEmailService service;

    public FindCustomerByEmailController() {
        CustomerRepository repo = new CustomerMySQLRepository(MySqlConnection.getInstance());
        this.service = new FindCustomerByEmailService(repo);
    }

    public Optional<CustomerDTO> execute(String email) {

            return service.execute(email);

        }

}

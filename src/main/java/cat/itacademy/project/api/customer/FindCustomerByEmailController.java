package cat.itacademy.project.api.customer;

import cat.itacademy.project.business_logic.customer.application.FindCustomerByEmailService;
import cat.itacademy.project.business_logic.customer.domain.CustomerRepository;
import cat.itacademy.project.business_logic.customer.infraestructure.CustomerMySQLRepository;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.shared.domain.dtos.customer.CustomerDTO;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.Optional;

public class FindCustomerByEmailController extends MenuCommand<Optional<CustomerDTO>> {
    private final FindCustomerByEmailService service;
    private final String emailToFind;

    public FindCustomerByEmailController(String emailToFind) {
        CustomerRepository repo = new CustomerMySQLRepository(MySqlConnection.getInstance());
        this.service = new FindCustomerByEmailService(repo, emailToFind);
        this.emailToFind = emailToFind;
    }

    @Override
    public Optional<Optional<CustomerDTO>> execute() {
        try {
            return Optional.ofNullable(service.execute());
        } catch (Exception e) {
            error("An unexpected error ocurred: " + e.getMessage());
            return Optional.empty();
        }

    }
}

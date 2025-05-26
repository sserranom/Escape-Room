package cat.itacademy.project.api.customer;

import cat.itacademy.project.business_logic.customer.application.FindCustomerByIdService;
import cat.itacademy.project.business_logic.customer.domain.CustomerRepository;
import cat.itacademy.project.business_logic.customer.infraestructure.CustomerMySQLRepository;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.shared.domain.dtos.customer.CustomerDTO;
import cat.itacademy.project.shared.infrastructure.database.mysql.MySqlConnection;

import java.util.Optional;

public class FindCustomerByIdController extends MenuCommand<Optional<CustomerDTO>> {
    private final FindCustomerByIdService service;
    private final int idToFind;

    public FindCustomerByIdController(int idToFind) {
        CustomerRepository repo = new CustomerMySQLRepository(MySqlConnection.getInstance());
        this.service = new FindCustomerByIdService(repo, idToFind);
        this.idToFind = idToFind;
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

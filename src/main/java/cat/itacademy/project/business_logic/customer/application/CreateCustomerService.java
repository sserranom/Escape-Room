package cat.itacademy.project.business_logic.customer.application;

import cat.itacademy.project.business_logic.customer.domain.CustomerRepository;
import cat.itacademy.project.shared.domain.dtos.customer.CreateCustomerDTO;
import cat.itacademy.project.shared.domain.dtos.customer.CustomerDTO;
import cat.itacademy.project.shared.domain.exceptions.AlreadyExistsException;

import java.util.Optional;

public class CreateCustomerService {
    private final CreateCustomerDTO customer;
    private final CustomerRepository repo;

    public CreateCustomerService(CreateCustomerDTO customer, CustomerRepository repo) {
        this.customer = customer;
        this.repo = repo;
    }

    public void execute(){
        ensureDoesNotExist();
        repo.create(customer);
    }

    private void ensureDoesNotExist() throws AlreadyExistsException {
        Optional<CustomerDTO> existing = repo.findByName(customer.name());
        if (existing.isPresent()) {
            throw new AlreadyExistsException("Customer " + customer.name() + " already exist");
        }
    }
}

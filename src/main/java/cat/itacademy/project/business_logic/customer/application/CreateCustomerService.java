package cat.itacademy.project.business_logic.customer.application;

import cat.itacademy.project.business_logic.customer.domain.Customer;
import cat.itacademy.project.business_logic.customer.domain.CustomerRepository;
import cat.itacademy.project.shared.domain.dtos.customer.CreateCustomerDTO;
import cat.itacademy.project.shared.domain.dtos.customer.CustomerDTO;
import cat.itacademy.project.shared.domain.exceptions.AlreadyExistsException;

import java.util.Optional;

public class CreateCustomerService {
    private final CustomerRepository repo;
    private Customer customer;

    public CreateCustomerService(CustomerRepository repo) {
        this.repo = repo;
    }

    public void execute(CreateCustomerDTO createCustomerDTO) {
        this.customer = Customer.create(createCustomerDTO);
        ensureDoesNotExist();
        repo.create(createCustomerDTO);
    }

    private void ensureDoesNotExist(){
        Optional<CustomerDTO> existing = repo.findByName(customer.getName());
        if (existing.isPresent()) {
            throw new AlreadyExistsException("Customer " + customer.getName() + " already exist");
        }
    }
}

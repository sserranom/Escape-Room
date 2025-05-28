package cat.itacademy.project.business_logic.customer.application;

import cat.itacademy.project.business_logic.customer.domain.Customer;
import cat.itacademy.project.business_logic.customer.domain.CustomerRepository;
import cat.itacademy.project.shared.domain.dtos.customer.CustomerDTO;
import cat.itacademy.project.shared.domain.dtos.customer.UpdateCustomerDTO;
import cat.itacademy.project.shared.domain.exceptions.EmptyFieldException;
import cat.itacademy.project.shared.domain.exceptions.NotFoundException;

import java.util.Optional;

public class UpdateCustomerService {
    private final CustomerRepository repo;

    public UpdateCustomerService(CustomerRepository repo) {
        this.repo = repo;
    }

    public Optional<CustomerDTO> execute(UpdateCustomerDTO request) {
        if (request.name() == null || request.name().isBlank()) {
            throw new EmptyFieldException("Field 'name' cannot be empty.");
        }

        Optional<CustomerDTO> existingOptional = repo.findByName(request.nameToUpdate());

        if (existingOptional.isEmpty()) {
            throw new NotFoundException("Customer with name '" + request.nameToUpdate() + "' does not exist.");
        }

        Customer customer = Customer.fromDatabase(existingOptional.get());

        customer.setName(
                request.name().isBlank() ? customer.getName() : request.name()
        );

        customer.setEmail(
                request.email().isBlank() ? customer.getEmail() : request.email()
        );

        customer.setSubscribed(
                request.is_subscribed() != customer.getSubscribed() ? request.is_subscribed() : customer.getSubscribed()
        );

        repo.update(customer);
        return Optional.of(customer.toDTO());
    }
}

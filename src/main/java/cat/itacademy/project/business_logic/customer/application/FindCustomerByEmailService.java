package cat.itacademy.project.business_logic.customer.application;

import cat.itacademy.project.business_logic.customer.domain.CustomerRepository;
import cat.itacademy.project.shared.domain.Command;
import cat.itacademy.project.shared.domain.dtos.customer.CustomerDTO;

import java.util.Optional;

public class FindCustomerByEmailService implements Command<CustomerDTO> {
   private final CustomerRepository repo;
   private final String emailToFind;

    public FindCustomerByEmailService(CustomerRepository repo, String emailToFind) {
        this.repo = repo;
        this.emailToFind = emailToFind;
    }

    @Override
    public Optional<CustomerDTO> execute() {
        return repo.findByEmail(emailToFind);
    }
}

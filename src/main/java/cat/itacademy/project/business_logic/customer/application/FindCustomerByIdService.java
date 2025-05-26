package cat.itacademy.project.business_logic.customer.application;

import cat.itacademy.project.business_logic.customer.domain.CustomerRepository;
import cat.itacademy.project.shared.domain.dtos.customer.CustomerDTO;

import java.util.Optional;

public class FindCustomerByIdService implements Command<CustomerDTO> {
    private final CustomerRepository repo;
    private final int idToFind;

    public FindCustomerByIdService(CustomerRepository repo, int idToFind) {
        this.repo = repo;
        this.idToFind = idToFind;
    }

    @Override
    public Optional<CustomerDTO> execute() {
        return repo.findById(idToFind);
    }
}

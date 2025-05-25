package cat.itacademy.project.business_logic.customer.application;

import cat.itacademy.project.business_logic.customer.domain.CustomerRepository;
import cat.itacademy.project.shared.domain.dtos.customer.CreateCustomerDTO;
import cat.itacademy.project.shared.domain.dtos.customer.CustomerDTO;
import cat.itacademy.project.shared.domain.dtos.room.RoomDTO;
import cat.itacademy.project.shared.domain.exceptions.AlreadyExistsException;

import java.util.Optional;

public class CreateCustomerService {
    private final CreateCustomerDTO custumer;
    private final CustomerRepository repo;

    public CreateCustomerService(CreateCustomerDTO custumer, CustomerRepository repo) {
        this.custumer = custumer;
        this.repo = repo;
    }

    public void execute(){
        ensureDoesNotExist();
        repo.create(custumer);
    }

    private void ensureDoesNotExist() throws AlreadyExistsException {
        Optional<CustomerDTO> existing = repo.findByName(custumer.name());
        if (existing.isPresent()) {
            throw new AlreadyExistsException("Customer " + custumer.name() + " already exist");
        }
    }
}

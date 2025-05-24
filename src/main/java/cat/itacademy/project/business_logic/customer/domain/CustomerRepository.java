package cat.itacademy.project.business_logic.customer.domain;

import cat.itacademy.project.business_logic.deco.domain.Deco;
import cat.itacademy.project.shared.domain.dtos.customer.CreateCustomerDTO;
import cat.itacademy.project.shared.domain.dtos.customer.CustomerDTO;
import cat.itacademy.project.shared.domain.dtos.deco.CreateDecoDTO;
import cat.itacademy.project.shared.domain.dtos.deco.DecoDTO;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    void create(CreateCustomerDTO deco);

    void update(Customer customer);

    Optional<CustomerDTO> findById(int id);

    List<CustomerDTO> findAll();

    Optional<CustomerDTO> findByName(String name);

    Optional<CustomerDTO> findAllByEmail(String email);
}

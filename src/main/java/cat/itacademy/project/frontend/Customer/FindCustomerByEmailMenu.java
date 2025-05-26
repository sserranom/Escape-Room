package cat.itacademy.project.frontend.Customer;

import cat.itacademy.project.api.customer.FindCustomerByEmailController;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.frontend.shared.MenuScanner;
import cat.itacademy.project.shared.domain.dtos.customer.CustomerDTO;

import java.util.Optional;

public class FindCustomerByEmailMenu extends MenuCommand<Void> {
    @Override
    public Optional<Void> execute() {
        try {
            String emailToFind = MenuScanner.readString("Enter the email customer to find: ");
            FindCustomerByEmailController controller = new FindCustomerByEmailController(emailToFind);
            Optional<Optional<CustomerDTO>> customerDTO = controller.execute();
            if (customerDTO.isPresent()) {
                Optional<CustomerDTO> foundCustomer = customerDTO.get();
                CustomerPrinter.print(foundCustomer.get());
            } else {
                error("Custmer with email " + emailToFind + " not found.");
            }
        } catch (Exception e) {
            error("An unexpected error ocurred: " + e.getMessage());
        }
        return Optional.empty();
    }
}

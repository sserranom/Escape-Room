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
            FindCustomerByEmailController controller = new FindCustomerByEmailController();
            Optional<CustomerDTO> customerDTO = controller.execute(emailToFind);
            if (customerDTO.isPresent()) {
                CustomerPrinter.print(customerDTO.get());
            } else {
                error("Custmer with email " + emailToFind + " not found.");
            }
        } catch (Exception e) {
            error("An unexpected error ocurred: " + e.getMessage());
        }
        return Optional.empty();
    }
}

package cat.itacademy.project.frontend.Customer;

import cat.itacademy.project.api.customer.CreateCustomerController;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.frontend.shared.MenuScanner;
import cat.itacademy.project.shared.domain.dtos.customer.CreateCustomerDTO;
import cat.itacademy.project.shared.domain.exceptions.EmptyFieldException;

import java.util.Optional;

public class CreateCustomerMenu extends MenuCommand<Void> {
    private String name;
    private String email;
    private boolean isSubscribed;

    @Override
    public Optional<Void> execute() {
        CreateCustomerDTO dto = getUserInfo();

        CreateCustomerController controller = new CreateCustomerController(dto);
        controller.execute();
        info("Customer created successfully.");
        return Optional.empty();
    }

    private CreateCustomerDTO getUserInfo() {
        while (name == null || email == null) {
            try {
                name = MenuScanner.readString("Enter the name of the customer: ");
                email = MenuScanner.readString("Enter the email of the customer: ");
                isSubscribed = MenuScanner.readBoolean("Is the client subscribed? YES / NO: ");
            } catch (EmptyFieldException e) {
                error("Error: fields cannot be empty.");
            }
        }
        return new CreateCustomerDTO(name, email, isSubscribed);
    }
}

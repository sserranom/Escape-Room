package cat.itacademy.project.frontend.Customer;

import cat.itacademy.project.api.customer.UpdateCustomerController;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.frontend.shared.MenuScanner;
import cat.itacademy.project.shared.domain.dtos.customer.UpdateCustomerDTO;

import java.util.Optional;

public class UpdateCustomerMenu extends MenuCommand<Void> {
    @Override
    public Optional<Void> execute() {
        try {
            UpdateCustomerDTO dto = getInfo();
            UpdateCustomerController controller = new UpdateCustomerController(dto);
            controller.execute();
            info("Customer with name '" + dto.nameToUpdate() + "' updated successfully.");
        } catch (IllegalArgumentException e) {
            error("Error: " + e.getMessage());
        } catch (Exception e) {
            error("An unexpected error occurred " + e.getMessage());
        }
        return Optional.empty();
    }

    private UpdateCustomerDTO getInfo() {
        String nameToUpdate = MenuScanner.readString("Enter the name of the customer to update: ");
        String newName = MenuScanner.readString("Enter the new Name: ");
        String newEmail = MenuScanner.readString("Enter the new Email: ");
        boolean newIsSubscribed = MenuScanner.readBoolean("Is the client subscribed?: ");

        return new UpdateCustomerDTO(nameToUpdate, newName.isEmpty() ? null : newName, newEmail, newIsSubscribed);
    }
}

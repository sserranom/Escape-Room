package cat.itacademy.project.frontend.Customer;

import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.frontend.shared.MenuScanner;
import cat.itacademy.project.shared.domain.exceptions.CustomException;

import java.util.Optional;

public class ManageCustomerMenu extends MenuCommand<Void> {


    @Override
    public Optional<Void> execute() {
        boolean isRunning = true;

        while (isRunning) {


            int choice = getUserInput();
            try {

                switch (choice) {
                    case 1:
                        log("Create a new Customer: ");
                        CreateCustomerMenu createCustomerMenu = new CreateCustomerMenu();
                        createCustomerMenu.execute();
                        break;

                    case 2:
                        log("Update Customer details: ");
                        UpdateCustomerMenu updateCustomerMenu = new UpdateCustomerMenu();
                        updateCustomerMenu.execute();
                        break;

                    case 3:
                        log("Find customer by email: ");
                        FindCustomerByEmailMenu findCustomerByEmailMenu = new FindCustomerByEmailMenu();
                        findCustomerByEmailMenu.execute();
                        break;

                    case 4:
                        log("Customers Details: ");
                        FindAllCustomerMenu findAllCustomerMenu = new FindAllCustomerMenu();
                        findAllCustomerMenu.execute();
                        break;

                    case 5:
                        log("Back: ");
                        isRunning = false;
                        break;
                    default:
                        error("Invalid choice. Please try again.");
                }
            } catch (CustomException e) {
                error(e.getMessage());
            }
        }

        return Optional.empty();
    }

    private int getUserInput() {
        log("1. Create a new Customer ");
        log("2. Update customers details ");
        log("3. Find customer by email ");
        log("4. View customer details ");
        log("5. Back <- ");

        return MenuScanner.readInt("Please enter your choice: ");
    }
}

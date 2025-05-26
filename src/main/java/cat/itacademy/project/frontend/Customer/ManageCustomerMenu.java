package cat.itacademy.project.frontend.Customer;

import cat.itacademy.project.frontend.Menu;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.frontend.shared.MenuScanner;
import cat.itacademy.project.shared.domain.dtos.customer.CustomerDTO;

import java.util.Optional;

public class ManageCustomerMenu extends MenuCommand<CustomerDTO> {
    private final CustomerDTO customerDTO;

    public ManageCustomerMenu(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    @Override
    public Optional<CustomerDTO> execute() {
        int choice = getUserInput();

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
                Menu menu = new Menu();
                menu.execute();
                break;
        }
        return Optional.ofNullable(customerDTO);
    }

    private int getUserInput() {
        log("1. Creat a new Customer ");
        log("2. Update customers details ");
        log("3. Find customer by email ");
        log("4. View customer details ");
        log("5. Back <- ");

        return MenuScanner.readInt("Please enter your choice: ");
    }
}

package cat.itacademy.project.frontend.Customer;

import cat.itacademy.project.api.customer.FindCustomerByIdController;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.frontend.shared.MenuScanner;
import cat.itacademy.project.shared.domain.dtos.customer.CustomerDTO;

import java.util.Optional;

public class FindCustomerByIdMenu extends MenuCommand<Void> {
    @Override
    public Optional<Void> execute() {
        try{
            int idToFind = MenuScanner.readInt("Enter the ID of the customer to find: ");
            FindCustomerByIdController controller = new FindCustomerByIdController(idToFind);
            Optional<Optional<CustomerDTO>> customerDTO = controller.execute();
            if(customerDTO.isPresent()){
                Optional<CustomerDTO> foundCustomer = customerDTO.get();
                CustomerPrinter.print(foundCustomer.get());
            }else{
                error("Customer with ID " + idToFind + " not found.");
            }
        }catch (Exception e){
            error("An unexpected error ocurred: " + e.getMessage());

        }
        return Optional.empty();
    }
}

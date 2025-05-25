package cat.itacademy.project.frontend.Customer;

import cat.itacademy.project.api.customer.FindAllCustomerController;
import cat.itacademy.project.frontend.shared.MenuCommand;
import cat.itacademy.project.shared.domain.dtos.customer.CustomerDTO;

import java.util.List;
import java.util.Optional;

public class FindAllCustomerMenu extends MenuCommand<List<CustomerDTO>> {
    @Override
    public Optional<List<CustomerDTO>> execute() {
        FindAllCustomerController controller = new FindAllCustomerController();
        Optional<List<CustomerDTO>> result = controller.execute();
        if (result.get().isEmpty()){
            error("No customer found.");
        }else{
            info ("List of Customers: ");
            result.get().forEach(CustomerPrinter::print);
        }
        return result;
    }
}

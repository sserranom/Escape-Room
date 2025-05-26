package cat.itacademy.project.frontend.Customer;

import cat.itacademy.project.shared.domain.dtos.customer.CustomerDTO;

public class CustomerPrinter {
    static void print(CustomerDTO customerDTO){
        System.out.printf("Id: %d  |  Name: %s  |  Email: %s  |  Is Subscribed: %s %n",
                customerDTO.id(), customerDTO.name(), customerDTO.email(),customerDTO.isSubscribed());
    }
}

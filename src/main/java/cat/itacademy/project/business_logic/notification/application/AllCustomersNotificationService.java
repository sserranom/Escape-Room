package cat.itacademy.project.business_logic.notification.application;

import cat.itacademy.project.business_logic.customer.domain.CustomerRepository;
import cat.itacademy.project.shared.domain.dtos.customer.CustomerDTO;
import cat.itacademy.project.shared.domain.dtos.notification.NotificationDTO;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.stream.Collectors;

public class AllCustomersNotificationService {
    private final CustomerRepository repo;

    public AllCustomersNotificationService(CustomerRepository repo) {
        this.repo = repo;
    }


    public List<NotificationDTO> execute(String message) {
        List<CustomerDTO> customers = repo.FindAllCustomerSubscribed();
        return customers.stream()
                .map(customer -> new NotificationDTO(
                        new ObjectId(String.valueOf(customer.id())),
                        customer.name(),
                        customer.email(),
                        message
                ))
                .collect(Collectors.toList());
    }

    public void sendAll(String message) {
//        execute(message).forEach(NotificationController::send);
    }
}

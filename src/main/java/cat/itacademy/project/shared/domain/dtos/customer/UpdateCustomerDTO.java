package cat.itacademy.project.shared.domain.dtos.customer;

import cat.itacademy.project.shared.domain.exceptions.EmptyFieldException;

public record UpdateCustomerDTO (String nameToUpdate, String name, String email, boolean is_subscribed){
    public UpdateCustomerDTO{
        if (nameToUpdate == null || nameToUpdate.isBlank()){
            throw new EmptyFieldException("The name of the Customer to update must be provided.");
        }
        if ((name == null || name.isBlank())){
            throw new EmptyFieldException("The name of the Customer must be provided.");
        }
    }
}

package cat.itacademy.project.business_logic.customer.domain;

import cat.itacademy.project.shared.domain.dtos.customer.CreateCustomerDTO;
import cat.itacademy.project.shared.domain.dtos.customer.CustomerDTO;
import cat.itacademy.project.shared.domain.dtos.room.RoomDTO;

public class Customer {
    private int id;
    private String name;
    private String email;
    private  Boolean isSubscribed;

    public Customer(int id, String name, String email, Boolean isSubscribed) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.isSubscribed = isSubscribed;
    }

    public Customer(String name, String email, Boolean isSubscribed) {
        this.name = name;
        this.email = email;
        this.isSubscribed = isSubscribed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getSubscribed() {
        return isSubscribed;
    }

    public void setSubscribed(Boolean subscribed) {
        isSubscribed = subscribed;
    }

    public static Customer create(CreateCustomerDTO dto){
        return new Customer(dto.name(), dto.email(), dto.isSubscribed());
    }

    public static Customer fromDatabase(CustomerDTO dto){
        return new Customer(dto.id(), dto.name(), dto.email(), dto.isSubscribed());
    }

    public CustomerDTO toDTO() {
        return new CustomerDTO(id, name, email, isSubscribed);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", isSubscribed=" + isSubscribed +
                '}';
    }
}

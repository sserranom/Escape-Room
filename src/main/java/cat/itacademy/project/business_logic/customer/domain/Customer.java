package cat.itacademy.project.business_logic.customer.domain;

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

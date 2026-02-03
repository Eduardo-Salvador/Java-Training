package Lambdas.Supplier.Domain;
import java.time.LocalDateTime;

public class Customer {
    private final String customerId;
    private final String name;
    private final LocalDateTime registrationDate;

    public Customer(String customerId, String name, LocalDateTime registrationDate) {
        this.customerId = customerId;
        this.name = name;
        this.registrationDate = registrationDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }
}
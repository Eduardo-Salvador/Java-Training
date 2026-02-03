package Lambdas.Supplier.Application;
import Lambdas.Supplier.Domain.Customer;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

public class Main {

    public static List<Customer> generateCustomers(int count, Supplier<Customer> customerSupplier){
        List<Customer> customerList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            customerList.add(customerSupplier.get());
        }
        return customerList;
    }

    public static void main(String[] args) {
        List<Customer> customerList = new ArrayList<>();
        AtomicInteger counter = new AtomicInteger(0);
        customerList = generateCustomers(10, () -> new Customer(String.valueOf(Math.random() * 100), ("Customer " + counter.incrementAndGet()), LocalDateTime.now()));
        for (Customer c : customerList){
            System.out.println(c.getCustomerId() + " | " + c.getName() + " | " + c.getRegistrationDate() + "%n");
        }

        Supplier<Double> randomPrices = () -> Math.random() * ((100 - 10) + 1) + 10;
        for (int i = 0; i < 100; i++) {
            System.out.println(randomPrices.get());
        }
    }
}
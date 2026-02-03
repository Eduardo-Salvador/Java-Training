package Lambdas.BiFunction.Application;
import Lambdas.BiFunction.Domain.CartItem;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CartItem c1 = new CartItem("Gaming Mouse", 2, 150.00);
        CartItem c2 = new CartItem("Mechanical Keyboard", 1, 450.00);
        CartItem c3 = new CartItem("Headset", 1, 320.50);
        CartItem c4 = new CartItem("XL Mouse Pad", 3, 75.90);
        CartItem c5 = new CartItem("Full HD Webcam", 1, 289.99);

        List<CartItem> cartItemsList = List.of(c1, c2, c3, c4, c5);

        Double total = CartItem.calculateTotal(cartItemsList, 1.05, (c, t) -> c * t);
        System.out.printf("Total in Cart + Taxes: $%.2f%n", total);

        total = CartItem.calculateTotal(cartItemsList, 150.00, (c, t) -> c + t);
        System.out.printf("Total in Cart + Fees: $%.2f%n", total);

        total = CartItem.calculateTotal(cartItemsList, 0.875, (c, t) -> c * t);
        System.out.printf("Total in Cart + 12.5 percent Discount: $%.2f%n", total);
        System.out.println("--------------------");

        total = CartItem.bonus(c3, 11, (q, b) -> (q.getQuantity() + b) * q.getUnitPrice());
        System.out.println("Item: " + c3.getProductName() + "Unit Price: $" + c3.getUnitPrice() + " Stock: " + c3.getQuantity());
        System.out.printf("Total Item + 11x Item bonus $%.2f%n", total);
        System.out.println("--------------------");
    }
}
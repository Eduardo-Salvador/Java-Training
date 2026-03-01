package Threads_CompletableFuture.ErrorHandlingDiferenceAndFallback;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class PaymentService {
    private static CompletableFuture<Void> checkPayments(int value){
        return CompletableFuture.supplyAsync(() -> {
            if (value < 0) {
                throw new IllegalArgumentException("Invalid amount");
            }
            if (value > 1000){
                throw new RuntimeException("Payment limit exceeded");
            }
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            return "Payment of $" + value + " approved";
        }).exceptionally(e -> "Payment failed: " + e.getCause().getMessage()
        ).thenAccept(System.out::println);
    }

    private static CompletableFuture<Void> checkPayments2(int value){
        return CompletableFuture.supplyAsync(() -> {
            if (value < 0) {
                throw new IllegalArgumentException("Invalid amount");
            }
            if (value > 1000){
                throw new RuntimeException("Payment limit exceeded");
            }
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            return "Payment of $" + value + " approved";
        }).handle((result, e) -> {
            if(e != null) {
                System.out.println("Status: FAILED");
                return "Payment failed: " + e.getCause().getMessage();
            }
            System.out.println("Status: SUCCESS");
            return result;
        }).thenAccept(System.out::println);
    }

    public static void main(String[] args) {
        System.out.println("=== Using exceptionally() ===");
        CompletableFuture<Void> c1 = checkPayments(100);
        CompletableFuture<Void> c2 = checkPayments(-100);
        CompletableFuture<Void> c3 = checkPayments(2000);
        CompletableFuture.allOf(c1, c2, c3).join();

        System.out.println("\n=== Using handle() ===");
        CompletableFuture<Void> c4 = checkPayments2(100);
        CompletableFuture<Void> c5 = checkPayments2(-100);
        CompletableFuture<Void> c6 = checkPayments2(2000);
        CompletableFuture.allOf(c4, c5, c6).join();
    }
}
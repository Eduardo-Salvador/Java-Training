package Records.Exercise;
import java.util.concurrent.CompletableFuture;

public class Main {
    private static CompletableFuture<Void> processTransaction(TransactionRecord transaction) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return transaction;
        }).thenApply(t -> {
            String newStatus = t.isHighValue() ? "REQUIRES_REVIEW" : "APPROVED";
            return new TransactionRecord(t.transactionId(), t.sender(), t.receiver(), t.amount(), newStatus);
        }).thenAccept(result -> System.out.println("Processed: " + result));
    }

    public static void main(String[] args) {
        TransactionRecord t1 = new TransactionRecord("Alice", "Bob", 5000);
        TransactionRecord t2 = new TransactionRecord("Carlos", "Maria", 15000);
        TransactionRecord t3 = new TransactionRecord("Ana", "Pedro", 800);

        CompletableFuture<Void> f1 = processTransaction(t1);
        CompletableFuture<Void> f2 = processTransaction(t2);
        CompletableFuture<Void> f3 = processTransaction(t3);

        CompletableFuture.allOf(f1, f2, f3).join();

        System.out.println("All transactions have been processed.");
    }
}
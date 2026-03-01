package Records.Exercise;
import java.util.UUID;

public record TransactionRecord(String transactionId, String sender, String receiver, double amount, String status) {
    public TransactionRecord {
        if (sender == null || sender.isBlank()) {
            throw new IllegalArgumentException("Sender cannot be null or blank.");
        }
        if (receiver == null || receiver.isBlank()) {
            throw new IllegalArgumentException("Receiver cannot be null or blank.");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
    }

    public TransactionRecord(String sender, String receiver, double amount) {
        this(UUID.randomUUID().toString(),
                sender,
                receiver,
                amount,
                "PENDING");
    }

    public boolean isHighValue(){
        return amount > 10000;
    }
}
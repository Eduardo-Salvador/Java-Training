package Streams.GenerateAndSummarizing.Domain;

public class Transaction {
    private Integer id;
    private Double value;
    private Type type;

    public enum Type {
        DEPOSIT,
        WITHDRAW
    }

    public Transaction(Integer id, Double value, Type type) {
        this.id = id;
        this.value = value;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public Double getValue() {
        return value;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return id + ": $" + value + " - Type: " + type;
    }
}
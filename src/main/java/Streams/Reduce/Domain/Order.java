package Streams.Reduce.Domain;
import java.util.List;

public class Order {
    private Integer id;
    private List<Item> items;

    public Order(Integer id, List<Item> items) {
        this.id = id;
        this.items = items;
    }

    public Integer getId() {
        return id;
    }

    public List<Item> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return id + ": " + items;
    }
}
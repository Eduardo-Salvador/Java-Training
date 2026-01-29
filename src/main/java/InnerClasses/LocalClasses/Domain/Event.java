package InnerClasses.LocalClasses.Domain;
import java.util.ArrayList;
import java.util.List;

public class Event {
    public Event(String eventName, Integer maxTickets) {
    }

    public void generateTickets() {
        class Ticket {
            private final String code;
            private final Double price;

            public Ticket(String code, Double price) {
                this.code = code;
                this.price = price;
            }

            @Override
            public String toString() {
                return "Ticket{" +
                        "code='" + code + '\'' +
                        ", price=" + price +
                        '}';
            }

            public static void sellAll(final List<Ticket> tickets){
                Double sum = 0D;
                for (Ticket t : tickets) {
                    sum += t.price;
                }
                System.out.println("Value total tickets: $" + sum);
            }
        }
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(new Ticket("T001", 150.0));
        tickets.add(new Ticket("T002", 200.0));
        tickets.add(new Ticket("T003", 180.0));
        tickets.add(new Ticket("T004", 250.0));
        tickets.add(new Ticket("T005", 300.0));
        tickets.forEach(System.out::println);

        Ticket.sellAll(tickets);
    }
}
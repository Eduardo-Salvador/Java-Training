package JDBC.Domain;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Book {
    int id;
    String title;
    String author;
    int year;
    boolean available;
}
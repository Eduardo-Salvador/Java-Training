package Collections.NavigableSet_TreeSet.Application;
import Collections.NavigableSet_TreeSet.Domain.Book;
import java.util.NavigableSet;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        Book b1 = new Book("The Hobbit", "J.R.R. Tolkien", 310);
        Book b2 = new Book("1984", "George Orwell", 328);
        Book b3 = new Book("Pride and Prejudice", "Jane Austen", 279);
        Book b4 = new Book("To Kill a Mockingbird", "Harper Lee", 281);
        Book b5 = new Book("The Catcher in the Rye", "J.D. Salinger", 277);
        NavigableSet<Book> books = new TreeSet<>();
        books.add(b1);
        books.add(b2);
        books.add(b3);
        books.add(b4);
        books.add(b5);
        books.forEach(System.out::println);
        System.out.println("--------------");
        System.out.println(books.first());
        System.out.println(books.last());
        System.out.println("--------------");
        System.out.println(b3);
        System.out.println("Lower: " + books.lower(b3));
        System.out.println("Higher: " + books.higher(b3));
        System.out.println("--------------");
        for (Book book : books.descendingSet()) {
            System.out.println(book);
        }
        System.out.println("--------------");
    }
}
package Collections.BinarySearch.Application;
import Collections.BinarySearch.Controller.LibrarySearch;
import Collections.BinarySearch.Domain.Book;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Book b1 = new Book("The Silent Patient", "Alex Michaelides", 2019);
        Book b2 = new Book("Educated", "Tara Westover", 2018);
        Book b3 = new Book("The Night Circus", "Erin Morgenstern", 2011);
        Book b4 = new Book("Becoming", "Michelle Obama", 2018);
        Book b5 = new Book("Dune", "Frank Herbert", 1965);
        List<Book> bookList = new ArrayList<>(List.of(b1, b2, b3, b4, b5));
        System.out.println("List ordered:");
        Collections.sort(bookList);
        bookList.forEach(System.out::println);
        System.out.println("----------------");
        System.out.println("Finding Book:");
        Book find = LibrarySearch.searchBook(bookList, b3);
        System.out.println(find);
    }
}
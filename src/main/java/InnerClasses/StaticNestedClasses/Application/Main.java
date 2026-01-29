package InnerClasses.StaticNestedClasses.Application;
import InnerClasses.StaticNestedClasses.Domain.Library.Book;
import InnerClasses.StaticNestedClasses.Domain.Library.Statistics;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Book> bookList = new ArrayList<>(List.of(new Book("Dom Casmurro", "Machado de Assis", 1899),
                new Book("O Senhor dos Anéis", "J.R.R. Tolkien", 1954),
                new Book("Cem Anos de Solidão", "Gabriel García Márquez", 1967)));

        System.out.println("Books:");
        for (Book b : bookList){
            b.printBook();
        }

        System.out.println("Other 2 Books!");
        bookList.add(new Book("1984", "George Orwell", 1949));
        bookList.add(new Book("O Pequeno Príncipe", "Antoine de Saint-Exupéry", 1943));

        Statistics s1 = new Statistics();
        s1.counterBooks(bookList);
    }
}
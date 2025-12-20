package InnerClasses.StaticAlignedClasses.Application;
import InnerClasses.StaticAlignedClasses.Domain.Library;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Library.Book> bookList = new ArrayList<>(List.of(new Library.Book("Dom Casmurro", "Machado de Assis", 1899),
                new Library.Book("O Senhor dos Anéis", "J.R.R. Tolkien", 1954),
                new Library.Book("Cem Anos de Solidão", "Gabriel García Márquez", 1967)));

        for (Library.Book b : bookList){
            b.printBook();
            System.out.println("----------------------------------------------------------------------------");
        }

        Library.Statistics s1 = new Library.Statistics();
        s1.counterBooks(bookList);
        System.out.println("----------------------------------------------------------------------------");
    }
}
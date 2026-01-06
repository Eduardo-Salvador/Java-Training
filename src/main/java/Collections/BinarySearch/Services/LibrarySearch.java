package Collections.BinarySearch.Services;
import Collections.BinarySearch.Domain.Book;
import java.util.Collections;
import java.util.List;

public class LibrarySearch {
    public static Book searchBook(List<Book> bookList, Book book) {
        Collections.sort(bookList);
        int bookPosition = Collections.binarySearch(bookList, book);
        if (bookPosition > 0) {
            System.out.println("Book this in position: " + bookPosition);
            return bookList.get(bookPosition);
        }
        return null;
    }
}
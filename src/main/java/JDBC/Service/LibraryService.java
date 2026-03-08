package JDBC.Service;
import JDBC.Domain.Book;
import JDBC.Repository.LibraryRepository;
import java.util.Optional;
import java.util.Scanner;

public class LibraryService {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void menu(int op){
        switch (op){
            case 1 -> create();
            case 2 -> findAll();
            case 3 -> findById();
            case 4 -> update();
            case 5 -> delete();
        }
    }

    private static void create(){
        System.out.println("Enter the book title: ");
        String title = SCANNER.nextLine();
        System.out.println("Enter the book author: ");
        String author = SCANNER.nextLine();
        System.out.println("Enter the book year: ");
        int year = Integer.parseInt(SCANNER.nextLine());
        LibraryRepository.create(Book.builder().title(title).author(author).year(year).build());
    }

    private static void findAll(){
        System.out.println("Library stock: ");
        System.out.printf("%-5s %-30s %-20s %-6s %-10s%n", "ID", "TITLE", "AUTHOR", "YEAR", "AVAILABLE");
        System.out.println("-".repeat(75));
        LibraryRepository.findAll().forEach(book -> System.out.printf(
                "%-5d %-30s %-20s %-6d %-10s%n",
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getYear(),
                book.isAvailable() ? "YES" : "NO"
        ));
    }

    private static void findById(){
        System.out.println("Type the id of the book you want to search");
        int id = Integer.parseInt(SCANNER.nextLine());
        Optional<Book> book = LibraryRepository.findById(id);
        if (book.isEmpty()) {
            System.out.println("Book not found!");
            return;
        }
        System.out.printf("[%d] - %s %s %d %b%n", book.get().getId(), book.get().getTitle(), book.get().getAuthor(), book.get().getYear(), book.get().isAvailable());
    }

    private static void update(){
        System.out.println("Type the id of the book you want to update: ");
        int id = Integer.parseInt(SCANNER.nextLine());

        Optional<Book> existing = LibraryRepository.findById(id);
        if (existing.isEmpty()) {
            System.out.println("Book not found!");
            return;
        }

        System.out.println("Enter the new title (leave blank to keep current): ");
        String title = SCANNER.nextLine();

        System.out.println("Enter the new author (leave blank to keep current): ");
        String author = SCANNER.nextLine();

        System.out.println("Enter the new year (leave blank to keep current): ");
        String yearInput = SCANNER.nextLine();

        System.out.println("Is the book available? (Y/N, leave blank to keep current): ");
        String availableInput = SCANNER.nextLine();

        LibraryRepository.update(Book.builder()
                .id(id)
                .title(title.isBlank() ? existing.get().getTitle() : title)
                .author(author.isBlank() ? existing.get().getAuthor() : author)
                .year(yearInput.isBlank() ? existing.get().getYear() : Integer.parseInt(yearInput))
                .available(availableInput.isBlank() ? existing.get().isAvailable() : availableInput.equalsIgnoreCase("y"))
                .build());
    }

    private static void delete(){
        System.out.println("Type the id of the book you want to delete: ");
        int id = Integer.parseInt(SCANNER.nextLine());
        Optional<Book> existing = LibraryRepository.findById(id);
        if (existing.isEmpty()) {
            System.out.println("Book not found!");
            return;
        }
        LibraryRepository.delete(id);
    }
}
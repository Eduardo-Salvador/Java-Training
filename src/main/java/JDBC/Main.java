package JDBC;
import JDBC.Service.LibraryService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int op = -1;
        while (op != 0) {
            System.out.print("""
        \n==================
         Library Manager
        ==================
        1 - Add book
        2 - List all books
        3 - Search by id
        4 - Update book
        5 - Delete book
        0 - Exit
        ==================
        Option:\s""");
            op = Integer.parseInt(scanner.nextLine());
            if (op != 0) LibraryService.menu(op);
        }
        System.out.println("Goodbye...");
        scanner.close();
    }
}
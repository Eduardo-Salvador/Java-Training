package InnerClasses.StaticNestedClasses.Domain;
import java.util.List;

public class Library {
        public static class Book {
            String title;
            String author;
            Integer year;

            public Book(String title, String author, Integer year) {
                this.title = title;
                this.author = author;
                this.year = year;
            }

            public void printBook(){
                System.out.println(title + " | Author: " + author + " | Year: " + year);
            }
        }

        public static class Statistics {
            public void counterBooks(List<Book> bookList){
                int counterYear = 0;

                for (Book b : bookList){
                    counterYear += b.year;
                }
                System.out.println("------------------------");
                System.out.println("They exist: " + bookList.size() + " books on the list.");
                System.out.println("The average year is: " + (counterYear / bookList.size()));
                System.out.println("------------------------");
            }
        }
}
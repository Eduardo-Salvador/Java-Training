package Collections.BinarySearch.Domain;

public class Book implements Comparable<Book> {
    private String title;
    private String author;
    private Integer year;

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getYear() {
        return year;
    }

    public Book(String title, String author, Integer year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    @Override
    public int compareTo(Book o){
        return this.title.compareTo(o.getTitle());
    }

    @Override
    public String toString() {
        return title + " - " + author + " - " + year;
    }
}
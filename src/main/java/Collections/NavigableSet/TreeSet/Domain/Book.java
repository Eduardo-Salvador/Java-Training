package Collections.NavigableSet.TreeSet.Domain;

public class Book implements Comparable<Book>{
    private String title;
    private String author;
    private Integer pages;

    public Book(String title, String author, Integer pages) {
        this.title = title;
        this.author = author;
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getPages() {
        return pages;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return getTitle() != null && getTitle().equals(book.getTitle());
    }

    @Override
    public int hashCode() {
        return getTitle() == null ? 0 : this.getTitle().hashCode();
    }

    @Override
    public String toString() {
        return "Book: " + getTitle() + " - " + getAuthor() + " - Pages: " + getPages();
    }

    @Override
    public int compareTo(Book o) {
        return this.title.compareTo(o.getTitle());
    }
}
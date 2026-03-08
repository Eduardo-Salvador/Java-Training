package JDBC.Repository;
import JDBC.Conn.DatabaseConnection;
import JDBC.Domain.Book;
import lombok.extern.log4j.Log4j2;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
public class LibraryRepository {
    public static void create(Book book){
        log.info("Saving Book '{}'", book);
        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement ps = prepareStatementCreate(conn, book)) {
            ps.execute();
        } catch (SQLException e) {
            log.error("Error while trying save a book '{}'", book, e);
        }
    }

    private static PreparedStatement prepareStatementCreate(Connection conn, Book book) throws SQLException {
        String sql = "INSERT INTO LibraryManager.`Library` (`title`, `author`, `year`, `available`) VALUES (?, ?, ?, ?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, book.getTitle());
        ps.setString(2, book.getAuthor());
        ps.setInt(3, book.getYear());
        ps.setBoolean(4, book.isAvailable());
        return ps;
    }

    public static List<Book> findAll(){
        List<Book> bookList = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = prepareStatementFindAll(conn);
             ResultSet rs = ps.executeQuery()) {
            while(rs.next()) {
                bookList.add(Book.builder()
                        .id(rs.getInt("id"))
                        .title(rs.getString("title"))
                        .author(rs.getString("author"))
                        .year(rs.getInt("year"))
                        .available(rs.getBoolean("available"))
                        .build()
                );
            }
        } catch (SQLException e) {
            log.error("Error while trying find all books ", e);
        }
        return bookList;
    }

    private static PreparedStatement prepareStatementFindAll(Connection conn) throws SQLException {
        String sql = """
                SELECT * FROM LibraryManager.`Library`;
                """;
        return conn.prepareStatement(sql);
    }

    public static Optional<Book> findById(int id){
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = prepareStatementFindById(conn, id);
             ResultSet rs = ps.executeQuery()) {
            if (!rs.next()) return Optional.empty();
            return Optional.ofNullable(Book.builder()
                    .id(rs.getInt("id"))
                    .title(rs.getString("title"))
                    .author(rs.getString("author"))
                    .year(rs.getInt("year"))
                    .available(rs.getBoolean("available"))
                    .build());
        } catch (SQLException e) {
            log.error("Error while trying find book id: {}", id, e);
        }
        return Optional.empty();
    }

    private static PreparedStatement prepareStatementFindById(Connection conn, int id) throws SQLException {
        String sql = """
                SELECT * FROM LibraryManager.`Library` WHERE id = ?;
                """;
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        return ps;
    }

    public static void update(Book book){
        log.info("Updating book {}", book.getTitle());
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = prepareStatementUpdate(conn, book)) {
            ps.execute();
        } catch (SQLException e) {
            log.error("Error while trying find book: {}", book.getTitle(), e);
        }
    }

    private static PreparedStatement prepareStatementUpdate(Connection conn, Book book) throws SQLException {
        String sql = """
                UPDATE LibraryManager.`Library`
                SET title = ?, author = ?, year = ?, available = ?
                WHERE id = ?;
                """;
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, book.getTitle());
        ps.setString(2, book.getAuthor());
        ps.setInt(3, book.getYear());
        ps.setInt(4, book.isAvailable() ? 1 : 0);
        ps.setInt(5, book.getId());
        return ps;
    }

    public static void delete(int id){
        log.info("Deleting book id: {}", id);
        if(findById(id).isPresent()) {
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement ps = prepareStatementDelete(conn, id)) {
                ps.execute();
            } catch (SQLException e) {
                log.error("Error while trying find book id: {}", id, e);
            }
        }
    }

    private static PreparedStatement prepareStatementDelete(Connection conn, int id) throws SQLException {
        String sql = """
                DELETE FROM LibraryManager.`Library` WHERE id = ?;
                """;
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        return ps;
    }
}
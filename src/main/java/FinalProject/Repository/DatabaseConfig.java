package FinalProject.Repository;
import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private static final DatabaseConfig INSTANCE = new DatabaseConfig();
    private Connection connection;

    private DatabaseConfig() {
        connect();
    }

    private void connect() {
        try {
            Dotenv dotenv = Dotenv.load();
            String url      = dotenv.get("DB_URL");
            String username = dotenv.get("DB_USER");
            String password = dotenv.get("DB_PASSWORD");
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to establish database connection.", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        if (INSTANCE.connection == null || INSTANCE.connection.isClosed()) {
            INSTANCE.connect();
        }
        return INSTANCE.connection;
    }
}
package se.khatchshah;
import java.sql.*;

public class SimpleDerbyApp {
    private static final String DB_URL = "jdbc:derby:myDB;create=true";

    public static void main(String[] args) {
        Connection conn = null;
        try {
            // Load Derby embedded server
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            // Connenct to database (create if not exists)
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("Connected to database");

            // Create table
            createTable(conn);

            // Insert data
            insertData(conn);

            // Query data
            queryData(conn);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // Close connection and shutdown Derby
            try {
                if (conn != null) {
                    conn.close();
                    System.out.println("Connection closed");
                }
                // Shutdown Derby
                DriverManager.getConnection("jdbc:derby:;shutdown=true");
            } catch (SQLException e) {
                if (e.getSQLState().equals("XJ015"))  {
                    System.out.println("Derby shutdown successfully");
                } else {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void queryData(Connection conn) throws SQLException {
        String sql = "SELECT * FROM users";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("Users:");
            while (rs.next()) {
                System.out.printf("ID: %d, Name: %s%n",
                        rs.getInt("id"),
                        rs.getString("name"));
            }
        }
    }

    private static void insertData(Connection conn) throws SQLException {
        String sql = "INSERT INTO users (name) VALUES (?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "Alice");
            pstmt.executeUpdate();
            System.out.println("Data inserted");
        }
    }

    private static void createTable(Connection conn) throws SQLException {
        String sql = "CREATE TABLE users ("
                + "id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,"
                + "name VARCHAR(50) NOT NULL)";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Table created");
        } catch (SQLException e) {
            if (e.getSQLState().equals("X0Y32")) {
                System.out.println("Table already exists");
            } else {
                throw e;
            }
        }
    }
}

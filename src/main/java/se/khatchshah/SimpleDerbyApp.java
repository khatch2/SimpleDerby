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

    private static void queryData(Connection conn) {
        
    }

    private static void insertData(Connection conn) {

    }

    private static void createTable(Connection conn) {

    }
}

package iuh.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=GaSaiGonDB;encrypt=false";
    private static final String USER = "sa";
    private static final String PASSWORD = "sapassword";
    private static Connection connection = null;

    private DatabaseConnection() {
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
//                System.out.println("Kết nối cơ sở dữ liệu GaSaiGonDB thành công!");
            } catch (ClassNotFoundException e) {
                System.err.println("Không tìm thấy driver JDBC: " + e.getMessage());
                throw new SQLException("Không tìm thấy driver JDBC", e);
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                    System.out.println("Đã đóng kết nối cơ sở dữ liệu!");
                }
            } catch (SQLException e) {
                System.err.println("Lỗi khi đóng kết nối: " + e.getMessage());
            } finally {
                connection = null;
            }
        }
    }
}
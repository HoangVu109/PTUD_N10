package iuh.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=GaSaiGonDB;encrypt=false";
    private static final String USER = "sa";
    private static final String PASSWORD = "sapassword";
    private static Connection connection = null;

    // Phương thức private để ngăn khởi tạo trực tiếp
    public DatabaseConnection() {
    }

    // Phương thức lấy kết nối (singleton)
    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Kết nối cơ sở dữ liệu GaSaiGonDB thành công!");
            } catch (ClassNotFoundException e) {
                System.err.println("Không tìm thấy driver JDBC: " + e.getMessage());
                e.printStackTrace();
            } catch (SQLException e) {
                System.err.println("Lỗi kết nối cơ sở dữ liệu: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return connection;
    }

    // Phương thức đóng kết nối (nếu cần)
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Đã đóng kết nối cơ sở dữ liệu!");
                connection = null;
            } catch (SQLException e) {
                System.err.println("Lỗi khi đóng kết nối: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
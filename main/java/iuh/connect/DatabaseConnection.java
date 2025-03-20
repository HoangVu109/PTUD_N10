package iuh.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=SAIGONDB;encrypt=false";
    private static final String USER = "sa";
    private static final String PASSWORD = "sapassword";
    private static Connection connection = null;

    // Phương thức private để ngăn khởi tạo trực tiếp
    public DatabaseConnection() {
    }

    // Phương thức khởi tạo kết nối khi ứng dụng chạy
    public static void initializeConnection() {
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
    }

    // Phương thức lấy kết nối
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            initializeConnection();
        }
        if (connection == null) {
            throw new SQLException("Không thể khởi tạo kết nối đến cơ sở dữ liệu!");
        }
        return connection;
    }

    // Phương thức đóng kết nối khi ứng dụng kết thúc
    public static void closeConnection() {
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                    System.out.println("Đã đóng kết nối cơ sở dữ liệu!");
                }
                connection = null;
            } catch (SQLException e) {
                System.err.println("Lỗi khi đóng kết nối: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
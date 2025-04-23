package iuh;

import iuh.connect.DatabaseConnection;
import iuh.gui.QuanLyGUI;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        DatabaseConnection.getConnection();
        try {
            new QuanLyGUI();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

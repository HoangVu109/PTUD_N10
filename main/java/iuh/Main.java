package iuh;

import iuh.connect.DatabaseConnection;
import iuh.main.GaSaiGonUI;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection db = new DatabaseConnection();
        try {
            new GaSaiGonUI();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

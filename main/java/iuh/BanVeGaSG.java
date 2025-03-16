package iuh;

import iuh.connect.DatabaseConnection;
import iuh.main.GaSaiGonUI;

import javax.swing.*;

public class BanVeGaSG {
    public static void main(String[] args) {
        DatabaseConnection db = new DatabaseConnection();
        new GaSaiGonUI();
    }
}

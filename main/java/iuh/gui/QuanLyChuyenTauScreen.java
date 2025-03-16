package iuh.gui;

import javax.swing.*;
import java.awt.*;

public class QuanLyChuyenTauScreen {
    private JPanel panel;

    public QuanLyChuyenTauScreen() {
        panel = new JPanel();
        panel.setBackground(new Color(245, 245, 245));
        JLabel label = new JLabel("Màn hình Quản lý chuyến tàu");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(label);
    }

    public JPanel getPanel() {
        return panel;
    }
}
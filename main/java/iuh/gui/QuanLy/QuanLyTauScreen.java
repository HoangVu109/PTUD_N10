package iuh.gui.QuanLy;

import javax.swing.*;
import java.awt.*;

public class QuanLyTauScreen {
    private JPanel panel;

    public QuanLyTauScreen() {
        panel = new JPanel();
        panel.setBackground(new Color(245, 245, 245));
        JLabel label = new JLabel("Màn hình Quản lý tàu");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(label);
    }

    public JPanel getPanel() {
        return panel;
    }
}
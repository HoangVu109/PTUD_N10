package iuh.screen.QuanLy;

import javax.swing.*;
import java.awt.*;

public class TraCuuThongTinScreen {
    private JPanel panel;

    public TraCuuThongTinScreen() {
        panel = new JPanel();
        panel.setBackground(new Color(245, 245, 245));
        JLabel label = new JLabel("Màn hình Tra cứu thông tin");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(label);
    }

    public JPanel getPanel() {
        return panel;
    }
}
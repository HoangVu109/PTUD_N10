package iuh.screen.QuanLy;

import iuh.screen.jpanel.LichTrinhPanel;
import iuh.screen.jpanel.DoanhThuPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HomeScreen extends JPanel {
    private JPanel rightPanel; // To replace content when buttons are clicked

    public HomeScreen() {
        // Set BorderLayout for main JPanel
        setLayout(new BorderLayout());
        setBackground(new Color(240, 243, 246)); // Match QuanLyChuyenTauScreen background
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Consistent padding

        // 1. imgPanel
        JPanel imgPanel = new JPanel();
        imgPanel.setBackground(Color.WHITE);
        imgPanel.setPreferredSize(new Dimension(0, 300)); // Fixed height for image
        imgPanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true)); // Subtle border
        imgPanel.setLayout(new BorderLayout());

        JLabel imageLabel = new JLabel();
        ImageIcon trainImage = new ImageIcon(new ImageIcon("src/main/java/iuh/icons/img.png")
                .getImage().getScaledInstance(1350, 300, Image.SCALE_SMOOTH));
        imageLabel.setIcon(trainImage);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        imgPanel.add(imageLabel, BorderLayout.CENTER);

        // 2. mainPanel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240, 243, 246));

        // leftPanel
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(240, 243, 246));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setPreferredSize(new Dimension(300, 0));
        leftPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(200, 200, 200)));

        // introduceTitle
        JLabel introduceTitle = new JLabel("Giới thiệu");
        introduceTitle.setFont(new Font("Segoe UI", Font.BOLD, 20)); // Match font size
        introduceTitle.setForeground(new Color(25, 118, 210)); // Match title color
        introduceTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(introduceTitle);
        leftPanel.add(Box.createVerticalStrut(15));

        // Info fields
        leftPanel.add(createInfoRow("Tên nhà ga:", "GA SÀI GÒN"));
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(createInfoRow("Địa chỉ:", "Gò Vấp, HCM"));
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(createInfoRow("Email:", "gasaigon@gmail.com"));
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(createInfoRow("Hotline:", "038999520"));
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(createInfoRow("Ngày lập:", "12/2/2024"));
        leftPanel.add(Box.createVerticalGlue());

        // rightPanel
        rightPanel = new JPanel();
        rightPanel.setBackground(new Color(240, 243, 246));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        // pageTitle
        JLabel pageTitle = new JLabel("Trang chủ");
        pageTitle.setFont(new Font("Segoe UI", Font.BOLD, 40)); // Slightly smaller than original
        pageTitle.setForeground(new Color(25, 118, 210)); // Match title color
        pageTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightPanel.add(pageTitle);
        rightPanel.add(Box.createVerticalStrut(10));

        // subtitle
        JLabel subtitle = new JLabel("Welcome back nhân viên A");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setForeground(new Color(33, 33, 33)); // Match text color
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightPanel.add(subtitle);
        rightPanel.add(Box.createVerticalStrut(20));

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.setBackground(new Color(240, 243, 246));

        JButton tinhTrangButton = createStyledButton("Tình trạng", new Color(25, 118, 210));
        tinhTrangButton.setPreferredSize(new Dimension(150, 50)); // Rectangular buttons
        tinhTrangButton.addActionListener(e -> switchToPanel(createTinhTrangPanel()));

        JButton doanhThuButton = createStyledButton("Doanh thu", new Color(0, 153, 0));
        doanhThuButton.setPreferredSize(new Dimension(150, 50));
        doanhThuButton.addActionListener(e -> switchToPanel(createDoanhThuPanel()));

        JButton lichTrinhButton = createStyledButton("Lịch trình", new Color(239, 83, 80));
        lichTrinhButton.setPreferredSize(new Dimension(150, 50));
        lichTrinhButton.addActionListener(e -> switchToPanel(createLichTrinhPanel()));

        buttonPanel.add(tinhTrangButton);
        buttonPanel.add(doanhThuButton);
        buttonPanel.add(lichTrinhButton);

        rightPanel.add(buttonPanel);
        rightPanel.add(Box.createVerticalGlue());

        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.CENTER);

        // Add panels to main JPanel
        add(imgPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
    }

    // Create info row for leftPanel
    private JPanel createInfoRow(String labelText, String textFieldText) {
        JPanel rowPanel = new JPanel();
        rowPanel.setBackground(new Color(240, 243, 246));
        rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.X_AXIS));
        rowPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(100, 30));
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(new Color(33, 33, 33)); // Match text color

        JTextField textField = new JTextField(textFieldText);
        textField.setPreferredSize(new Dimension(150, 30));
        textField.setMaximumSize(new Dimension(150, 30));
        textField.setEditable(false);
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textField.setForeground(new Color(33, 33, 33));
        textField.setBackground(Color.WHITE);
        textField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));

        rowPanel.add(label);
        rowPanel.add(Box.createHorizontalStrut(10));
        rowPanel.add(textField);
        rowPanel.add(Box.createHorizontalGlue());

        return rowPanel;
    }

    // Create styled button similar to QuanLyChuyenTauScreen
    private JButton createStyledButton(String text, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(backgroundColor.darker(), 2),
                BorderFactory.createEmptyBorder(8, 15, 8, 15)
        ));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(backgroundColor.brighter());
                button.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(backgroundColor.brighter(), 2),
                        BorderFactory.createEmptyBorder(8, 15, 8, 15)
                ));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(backgroundColor);
                button.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(backgroundColor.darker(), 2),
                        BorderFactory.createEmptyBorder(8, 15, 8, 15)
                ));
            }
        });
        return button;
    }

    // Replace rightPanel with new panel
    private void switchToPanel(JPanel newPanel) {
        Container parent = rightPanel.getParent();
        parent.remove(rightPanel);
        rightPanel = newPanel;
        parent.add(rightPanel, BorderLayout.CENTER);
        parent.revalidate();
        parent.repaint();
    }

    // Placeholder panels for Tình trạng, Doanh thu, Lịch trình
    private JPanel createTinhTrangPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(240, 243, 246));
        JLabel label = new JLabel("Đây là panel Tình trạng");
        label.setFont(new Font("Segoe UI", Font.BOLD, 16));
        label.setForeground(new Color(25, 118, 210));
        panel.add(label);
        return panel;
    }

    private JPanel createDoanhThuPanel() {
        return new DoanhThuPanel();
    }

    private JPanel createLichTrinhPanel() {
        return new LichTrinhPanel();
    }

    public JPanel getPanel() {
        return this;
    }

    // Main for testing the UI
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Home Screen");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.add(new HomeScreen());
            frame.setVisible(true);
        });
    }
}
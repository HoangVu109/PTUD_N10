package iuh.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class HomeScreen {
    private JPanel panel; // Panel chính để chứa giao diện
    private String employeeName = "Nhân viên A"; // Tạm đặt tên cố định

    public HomeScreen() {
        // Tạo panel chính với BoxLayout theo chiều dọc
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(240, 248, 255)); // Màu xanh nhạt nhẹ nhàng #F0F8FF
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding ngoài

        // Bước 1: Thêm logo
        JLabel logoLabel = new JLabel();
        ImageIcon logoIcon = new ImageIcon("main/java/iuh/icons/logo.png"); // Đường dẫn logo
        logoLabel.setIcon(new ImageIcon(logoIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Căn giữa logo
        panel.add(logoLabel);
        panel.add(Box.createVerticalStrut(10)); // Khoảng cách giữa logo và tiêu đề

        // Bước 2: Thêm tiêu đề "Welcome back, Nhân viên A"
        JLabel titleLabel = new JLabel("Welcome back, " + employeeName);

        titleLabel.setFont(new Font("Arial", Font.PLAIN, 28)); // Font không in đậm
        // Cho title màu phù hợp với giao diện thay vì màu đen
        titleLabel.setForeground(new Color(46, 58, 89)); // Màu xanh đậm #2E3A59
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Căn giữa
        panel.add(titleLabel);

        // Thêm khoảng cách dọc sau tiêu đề
        panel.add(Box.createVerticalStrut(20));

        // Bước 3: Hàng 1 - 3 ô (Số vé bán/ngày, Doanh thu/ngày, Lịch trình)
        JPanel row1 = new JPanel();
        row1.setLayout(new BoxLayout(row1, BoxLayout.X_AXIS)); // Xếp ngang
        row1.setBackground(new Color(240, 248, 255)); // Màu nền giống panel chính
        row1.setAlignmentX(Component.CENTER_ALIGNMENT); // Căn giữa hàng

        // Ô 1: Số vé bán/ngày
        JPanel ticketPanel = createInfoPanel("Số vé bán/ngày", "90", "main/java/iuh/icons/ticket.png");
        row1.add(ticketPanel);
        row1.add(Box.createHorizontalStrut(20));

        // Ô 2: Doanh thu/ngày
        JPanel revenuePanel = createInfoPanel("Doanh thu/ngày", "5,430,000 VND", "main/java/iuh/icons/revenue.png");
        row1.add(revenuePanel);
        row1.add(Box.createHorizontalStrut(20));

        // Ô 3: Lịch trình
        JPanel schedulePanel = createInfoPanel("Lịch trình", "", "main/java/iuh/icons/schedule.png");
        row1.add(schedulePanel);

        panel.add(row1); // Thêm hàng 1 vào panel chính
        panel.add(Box.createVerticalStrut(20));

        // Bước 4: Hàng 2 - 3 ô (Nhận ca, Kết ca, Chương trình khuyến mãi)
        JPanel row2 = new JPanel();
        row2.setLayout(new BoxLayout(row2, BoxLayout.X_AXIS)); // Xếp ngang
        row2.setBackground(new Color(240, 248, 255)); // Màu nền giống panel chính
        row2.setAlignmentX(Component.CENTER_ALIGNMENT); // Căn giữa hàng

        // Ô 1: Nhận ca
        JPanel shiftMessagePanel = createInfoPanel("Nhận ca", "", null);
        row2.add(shiftMessagePanel);
        row2.add(Box.createHorizontalStrut(20));

        // Ô 2: Kết ca
        JPanel shiftEndPanel = createInfoPanel("Kết ca", "", null);
        row2.add(shiftEndPanel);
        row2.add(Box.createHorizontalStrut(20));

        // Ô 3: Chương trình khuyến mãi
        JPanel promotionPanel = createInfoPanel("Chương trình khuyến mãi", "", null);
        row2.add(promotionPanel);

        panel.add(row2); // Thêm hàng 2 vào panel chính
        panel.add(Box.createVerticalStrut(20));

        // Bước 5: Hàng 3 - Hình ảnh tàu
        JLabel trainImageLabel = new JLabel();
        ImageIcon trainIcon = new ImageIcon("main/java/iuh/icons/Doantau.png"); // Đường dẫn ảnh tàu
        trainImageLabel.setIcon(new ImageIcon(trainIcon.getImage().getScaledInstance(700, 200, Image.SCALE_SMOOTH)));
        trainImageLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Căn giữa ảnh
        trainImageLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 102, 204), 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10))); // Viền xanh và padding
        panel.add(trainImageLabel);
    }

    // Hàm tạo một ô thông tin (như Số vé bán/ngày, Doanh thu/ngày, v.v.) với hiệu ứng bóng mờ
    private JPanel createInfoPanel(String title, String value, String iconPath) {
        // Tạo một JPanel tùy chỉnh để bo tròn góc và thêm hiệu ứng bóng mờ
        JPanel infoPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fill(new RoundRectangle2D.Float(10, 10, getWidth() - 20, getHeight() - 20, 15, 15)); // Bo tròn với bán kính 15
                g2.setColor(new Color(0, 0, 0, 50)); // Màu bóng mờ
                g2.fill(new RoundRectangle2D.Float(15, 15, getWidth() - 30, getHeight() - 30, 15, 15)); // Bóng mờ
                g2.dispose();
            }
        };
        infoPanel.setOpaque(false); // Làm nền trong suốt để thấy hiệu ứng
        infoPanel.setBackground(new Color(230, 240, 255)); // Màu xanh nhạt #E6F0FF
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS)); // Xếp dọc bên trong ô
        infoPanel.setPreferredSize(new Dimension(200, 120)); // Kích thước ô
        infoPanel.setMaximumSize(new Dimension(200, 120)); // Giới hạn kích thước
        infoPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); // Padding trong ô

        // Thêm biểu tượng nếu có
        if (iconPath != null) {
            ImageIcon icon = new ImageIcon(iconPath);
            JLabel iconLabel = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
            iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Căn giữa
            infoPanel.add(iconLabel);
            infoPanel.add(Box.createVerticalStrut(5));
        }

        // Thêm giá trị nếu có
        if (!value.isEmpty()) {
            JLabel valueLabel = new JLabel(value);
            valueLabel.setFont(new Font("Arial", Font.PLAIN, 18)); // Font không in đậm
            valueLabel.setForeground(Color.BLACK); // Màu đen
            valueLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Căn giữa
            infoPanel.add(valueLabel);
            infoPanel.add(Box.createVerticalStrut(5));
        }

        // Thêm tiêu đề
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 14)); // Font không in đậm
        titleLabel.setForeground(Color.DARK_GRAY); // Màu xám đậm
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Căn giữa
        infoPanel.add(titleLabel);

        return infoPanel;
    }

    // Hàm trả về panel để dùng trong GaSaiGonUI
    public JPanel getPanel() {
        return panel;
    }

    // Main để test
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Home Screen");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(900, 600);
            frame.add(new HomeScreen().getPanel());
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
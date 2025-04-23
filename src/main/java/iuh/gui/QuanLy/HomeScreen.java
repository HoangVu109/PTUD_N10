package iuh.gui.QuanLy;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HomeScreen extends JPanel {
    private JPanel rightPanel; // Để thay thế nội dung khi nhấn nút

    public HomeScreen() {
        // Thiết lập BorderLayout cho JPanel chính
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // 1. imgPanel
        JPanel imgPanel = new JPanel();
        imgPanel.setBackground(Color.WHITE);
        imgPanel.setPreferredSize(new Dimension(0, 300)); // Chiều cao hợp lý để không vỡ hình
        imgPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        imgPanel.setLayout(new BorderLayout());

        JLabel imageLabel = new JLabel();
        ImageIcon trainImage = new ImageIcon(new ImageIcon("src/main/java/iuh/icons/img.png") // Thay bằng đường dẫn thực tế
                .getImage().getScaledInstance(1350, 300, Image.SCALE_SMOOTH));
        imageLabel.setIcon(trainImage);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        imgPanel.add(imageLabel, BorderLayout.CENTER);


        // 2. mainPanel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // leftPanel
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setPreferredSize(new Dimension(300, 0));
        leftPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK)); // Viền bên phải



        // introduceTitle
        JLabel introduceTitle = new JLabel("Giới thiệu");
        introduceTitle.setFont(new Font("Segoe UI", Font.BOLD, 25));
        introduceTitle.setForeground(new Color(46, 58, 89)); // Màu xanh dương
        introduceTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(introduceTitle);
        leftPanel.add(Box.createVerticalStrut(10));


        // Các trường thông tin
        leftPanel.add(createInfoRow(" Tên nhà ga:", "GA SÀI GÒN"));
        leftPanel.add(Box.createVerticalStrut(25));
        leftPanel.add(createInfoRow(" Địa chỉ:", "Gò Vấp, HCM"));
        leftPanel.add(Box.createVerticalStrut(25));
        leftPanel.add(createInfoRow(" Email:", "gasaigon@gmail.com  "));
        leftPanel.add(Box.createVerticalStrut(25));
        leftPanel.add(createInfoRow(" Hotline:", "038999520"));
        leftPanel.add(Box.createVerticalStrut(25));
        leftPanel.add(createInfoRow(" Ngày lập:", "12/2/2024"));
        leftPanel.add(Box.createVerticalGlue());
        // day tat ca cac truong thong tin sang phai



        // rightPanel
        rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        // pageTitle
        JLabel pageTitle = new JLabel("Trang chủ");
        pageTitle.setFont(new Font("Segoe UI", Font.BOLD, 30));
        pageTitle.setForeground(new Color(46, 58, 89));
        pageTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightPanel.add(pageTitle);
        rightPanel.add(Box.createVerticalStrut(5));

        // subtitle
        JLabel subtitle = new JLabel("Welcome back nhân viên A");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightPanel.add(subtitle);
        rightPanel.add(Box.createVerticalStrut(20));

        // Các nút
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.setBackground(Color.WHITE);

        JButton tinhTrangButton = new JButton("Tình trạng");
        tinhTrangButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tinhTrangButton.setPreferredSize(new Dimension(150, 150));
        tinhTrangButton.addActionListener(e -> switchToPanel(createTinhTrangPanel()));

        JButton doanhThuButton = new JButton("Doanh thu");
        doanhThuButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        doanhThuButton.setPreferredSize(new Dimension(150, 150));
        doanhThuButton.addActionListener(e -> switchToPanel(createDoanhThuPanel()));

        JButton lichTrinhButton = new JButton("Lịch trình");
        lichTrinhButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lichTrinhButton.setPreferredSize(new Dimension(150, 150));
        lichTrinhButton.addActionListener(e -> switchToPanel(createLichTrinhPanel()));

        buttonPanel.add(tinhTrangButton);
        buttonPanel.add(doanhThuButton);
        buttonPanel.add(lichTrinhButton);

        rightPanel.add(buttonPanel);
        rightPanel.add(Box.createVerticalGlue());

        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.CENTER);

        // Thêm các panel vào JPanel chính
        add(imgPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
    }

    // Hàm tạo hàng thông tin cho leftPanel
    private JPanel createInfoRow(String labelText, String textFieldText) {
        JPanel rowPanel = new JPanel();
        rowPanel.setBackground(Color.WHITE);
        rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.X_AXIS));
        rowPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(100, 30));
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JTextField textField = new JTextField(textFieldText);
        textField.setPreferredSize(new Dimension(150, 30));
        textField.setMaximumSize(new Dimension(150, 30));
        textField.setEditable(false); // Không cho chỉnh sửa

        rowPanel.add(label);
        rowPanel.add(Box.createHorizontalStrut(10));
        rowPanel.add(textField);
        rowPanel.add(Box.createHorizontalGlue());

        return rowPanel;
    }

    // Hàm thay thế rightPanel bằng panel mới
    private void switchToPanel(JPanel newPanel) {
        Container parent = rightPanel.getParent();
        parent.remove(rightPanel);
        rightPanel = newPanel;
        parent.add(rightPanel, BorderLayout.CENTER);
        parent.revalidate();
        parent.repaint();
    }

    // Các panel giả lập cho Tình trạng, Doanh thu, Lịch trình
    private JPanel createTinhTrangPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        JLabel label = new JLabel("Đây là panel Tình trạng");
        label.setFont(new Font("Segoe UI", Font.BOLD, 16));
        panel.add(label);
        return panel;
    }

    private JPanel createDoanhThuPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        JLabel label = new JLabel("Đây là panel Doanh thu");
        label.setFont(new Font("Segoe UI", Font.BOLD, 16));
        panel.add(label);
        return panel;
    }

    private JPanel createLichTrinhPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        JLabel label = new JLabel("Đây là panel Lịch trình");
        label.setFont(new Font("Segoe UI", Font.BOLD, 16));
        panel.add(label);
        return panel;
    }
    public JPanel getPanel() {
        return this;
    }

    // Main để kiểm tra giao diện
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
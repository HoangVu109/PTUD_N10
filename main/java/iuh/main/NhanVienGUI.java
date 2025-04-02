package iuh.main;

import iuh.connect.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;

public class NhanVienGUI extends JFrame {
    private JPanel mainArea; // Lưu trữ mainArea để cập nhật nội dung

    public NhanVienGUI() throws SQLException {
        // Kiểm tra kết nối cơ sở dữ liệu
        Connection dbConnection = DatabaseConnection.getConnection();
        if (dbConnection == null) {
            JOptionPane.showMessageDialog(this, "Không thể kết nối cơ sở dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            System.exit(1); // Thoát nếu không kết nối được
        }

        // Thiết lập cửa sổ chính
        setTitle("GA SÀI GÒN - HỆ THỐNG BÁN VÉ NHÂN VIÊN");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 750);

        // Tạo layout chính với BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245, 245, 245)); // Màu xám nhạt #F5F5F5

        // Phần Menu Bên Trái
        JPanel menuPanel = createMenuPanel();
        mainPanel.add(menuPanel, BorderLayout.WEST);

        // Phần Khu Vực Chính (bao gồm thanh taskbar và khu vực trống)
        mainArea = createMainArea();
        mainPanel.add(mainArea, BorderLayout.CENTER);

        // Thêm mainPanel vào frame
        add(mainPanel);

        // Hiển thị cửa sổ
        setLocationRelativeTo(null); // Căn giữa màn hình
        setVisible(true);
    }

    // Tạo Menu Bên Trái
    private JPanel createMenuPanel() {
        // Sử dụng BoxLayout theo trục Y (dọc) để xếp các thành phần
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setPreferredSize(new Dimension(200, 0));
        menuPanel.setBackground(new Color(46, 58, 89)); // Màu xanh đậm #2E3A59

        // Logo "Ga Sài Gòn"
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        ImageIcon logoIcon = new ImageIcon("src/main/java/iuh/icons/LoGoGa.png");
        JLabel logoLabel = new JLabel(new ImageIcon(logoIcon.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
        logoPanel.add(logoLabel);
        logoPanel.setBackground(new Color(46, 58, 89));
        logoPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Căn giữa theo trục X
        menuPanel.add(logoPanel);

        // Thêm khoảng cách dọc
        menuPanel.add(Box.createVerticalStrut(10));

        // Danh sách các chức năng cho nhân viên
        String[] menuItems = {
                "Trang chủ", "Bán vé", "Tra cứu lịch tầu", "Tra cứu vé",
                "Thống kê", "Nhắn ca", "Kết ca", "Tài khoản"
        };
        String[] iconPaths = {
                "src/main/java/iuh/icons/home.png", // Trang chủ
                "src/main/java/iuh/icons/tickets.png", // Bán vé
                "src/main/java/iuh/icons/tracuulichtau.png", // Tra cứu lịch tầu
                "src/main/java/iuh/icons/tracuubanve.png", // Tra cứu vé
                "src/main/java/iuh/icons/thongke.png", // Thống kê
                "src/main/java/iuh/icons/nhanca.png", // Nhắn ca
                "src/main/java/iuh/icons/ketca.png", // Kết ca
                "src/main/java/iuh/icons/Myacount.png" // Tài khoản
        };

        // Thêm các nút menu
        for (int i = 0; i < menuItems.length; i++) {
            JButton menuButton = createMenuButton(menuItems[i], iconPaths[i]);
            menuButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Căn giữa theo trục X
            menuPanel.add(menuButton);
            menuPanel.add(Box.createVerticalStrut(10)); // Thêm khoảng cách giữa các nút
        }

        return menuPanel;
    }

    // Tạo nút menu với biểu tượng và hiệu ứng hover
    private JButton createMenuButton(String text, String iconPath) {
        JButton menuButton = new JButton();
        menuButton.setPreferredSize(new Dimension(200, 60));
        menuButton.setMaximumSize(new Dimension(200, 40)); // Đảm bảo kích thước cố định
        menuButton.setBackground(new Color(46, 58, 89));
        menuButton.setForeground(new Color(93, 121, 177, 100));
        menuButton.setBorderPainted(false); // Bỏ viền nút
        menuButton.setFocusPainted(false); // Bỏ viền focus
        menuButton.setLayout(new BoxLayout(menuButton, BoxLayout.X_AXIS)); // Sử dụng BoxLayout theo trục X
        menuButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        ImageIcon icon = new ImageIcon(iconPath);
        JLabel iconLabel = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        JLabel textLabel = new JLabel(text);
        textLabel.setForeground(new Color(93, 121, 177)); // Màu #5D79B1
        textLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        menuButton.add(Box.createHorizontalStrut(10)); // Thêm khoảng cách bên trái
        menuButton.add(iconLabel);
        menuButton.add(Box.createHorizontalStrut(10)); // Thêm khoảng cách giữa icon và text
        menuButton.add(textLabel);

        // Hiệu ứng hover
        menuButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                menuButton.setBackground(new Color(255, 255, 255)); // Màu trắng khi hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                menuButton.setBackground(new Color(46, 58, 89)); // Trở về màu gốc
            }
        });

        // Sự kiện nhấp chuột để mở màn hình mới
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (text.equals("Trang chủ")) {
                    updateMainArea(createWelcomePanel());
                } else if (text.equals("Bán vé")) {
                    updateMainArea(new JPanel()); // Placeholder for ticket selling screen
                } else if (text.equals("Tra cứu lịch tầu")) {
                    updateMainArea(new JPanel()); // Placeholder for train schedule search
                } else if (text.equals("Tra cứu vé")) {
                    updateMainArea(new JPanel()); // Placeholder for ticket search
                } else if (text.equals("Thống kê")) {
                    updateMainArea(new JPanel()); // Placeholder for statistics
                } else if (text.equals("Nhắn ca")) {
                    updateMainArea(new JPanel()); // Placeholder for shift messages
                } else if (text.equals("Kết ca")) {
                    updateMainArea(new JPanel()); // Placeholder for end shift
                } else if (text.equals("Tài khoản")) {
                    updateMainArea(new JPanel()); // Placeholder for account
                }
            }
        });

        return menuButton;
    }

    // Tạo Khu Vực Chính (bao gồm Taskbar và phần trống)
    private JPanel createMainArea() {
        mainArea = new JPanel(new BorderLayout());
        mainArea.setBackground(new Color(245, 245, 245)); // Màu xám nhạt #F5F5F5

        // Thanh Taskbar
        JPanel taskbar = createTaskbar();
        mainArea.add(taskbar, BorderLayout.NORTH);

        // Phần chào mừng ban đầu
        JPanel welcomePanel = createWelcomePanel();
        mainArea.add(welcomePanel, BorderLayout.CENTER);

        return mainArea;
    }

    // Tạo Thanh Taskbar
    private JPanel createTaskbar() {
        JPanel taskbar = new JPanel();
        taskbar.setLayout(new BoxLayout(taskbar, BoxLayout.X_AXIS)); // Sử dụng BoxLayout theo trục X
        taskbar.setPreferredSize(new Dimension(0, 40));
        taskbar.setBackground(Color.WHITE);
        taskbar.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, new Color(173, 216, 230))); // Viền #ADD8E6

        // Ngày tháng (sẽ được cập nhật tự động)
        JLabel dateLabel = new JLabel();
        dateLabel.setFont(new Font("Arial", Font.BOLD, 20));
        dateLabel.setForeground(new Color(46, 58, 89)); // Màu xanh đậm #2E3A59
        updateDateTime(dateLabel); // Cập nhật lần đầu

        // Spacer để đẩy logo thông báo sang phải
        Component spacer = Box.createHorizontalGlue(); // Sử dụng glue để đẩy sang phải

        // Logo thông báo
        ImageIcon notificationIcon = new ImageIcon("src/main/java/iuh/icons/notification.png"); // Placeholder icon
        JLabel notificationLabel = new JLabel(new ImageIcon(notificationIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));

        // Thêm các thành phần vào taskbar
        taskbar.add(Box.createHorizontalStrut(10)); // Thêm khoảng cách bên trái
        taskbar.add(dateLabel);
        taskbar.add(spacer);
        taskbar.add(notificationLabel);
        taskbar.add(Box.createHorizontalStrut(10)); // Thêm khoảng cách bên phải

        // Cập nhật ngày giờ tự động mỗi giây
        new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDateTime(dateLabel);
            }
        }).start();

        return taskbar;
    }

    // Phương thức để cập nhật ngày giờ
    private void updateDateTime(JLabel label) {
        java.time.LocalDateTime now = java.time.LocalDateTime.now();
        String dateTime = now.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        label.setText(dateTime);
    }

    // Tạo panel chào mừng
    private JPanel createWelcomePanel() {
        JPanel welcomePanel = new JPanel();
        welcomePanel.setBackground(new Color(245, 245, 245));
        welcomePanel.setLayout(new BorderLayout()); // Sử dụng BorderLayout để căn giữa

        JLabel welcomeLabel = new JLabel("Chào mừng NHÂN VIÊN A", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setForeground(new Color(46, 58, 89)); // Màu xanh đậm #2E3A59
        welcomePanel.add(welcomeLabel, BorderLayout.CENTER);

        return welcomePanel;
    }

    // Cập nhật khu vực chính khi nhấp vào nút menu
    private void updateMainArea(JPanel newScreen) {
        mainArea.removeAll();
        JPanel taskbar = createTaskbar(); // Tạo taskbar mới với ngày giờ tự động cập nhật
        mainArea.add(taskbar, BorderLayout.NORTH);
        mainArea.add(newScreen, BorderLayout.CENTER);
        mainArea.revalidate();
        mainArea.repaint();
    }

    public static void main(String[] args) throws SQLException {
        new NhanVienGUI();
    }
}
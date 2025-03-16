package iuh.main;

import iuh.connect.DatabaseConnection;
import iuh.gui.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class GaSaiGonUI extends JFrame {
    private JPanel mainArea; // Lưu trữ mainArea để cập nhật nội dung

    public GaSaiGonUI() {
        // Thiết lập cửa sổ chính
        setTitle("GA SÀI GÒN - HỆ THỐNG QUẢN LÝ BÁN VÉ TÀU HỎA");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 750);
        // Tắt thanh title bar mặc định



        // Tạo layout chính
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
        //Kết nối cơ sở dữ liệu
        DatabaseConnection.getConnection();


        // Hiển thị cửa sổ
        setLocationRelativeTo(null); // Căn giữa màn hình
        setVisible(true);
    }

    // Tạo Menu Bên Trái
    private JPanel createMenuPanel() {
        JPanel menuPanel = new JPanel(new GridBagLayout());
        menuPanel.setPreferredSize(new Dimension(200, 0));
        menuPanel.setBackground(new Color(46, 58, 89)); // Màu xanh đậm #2E3A59

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.NORTHWEST;

        // Logo "Ga Sài Gòn"
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        ImageIcon logoIcon = new ImageIcon("main/java/iuh/icons/LoGoGa.png");
        JLabel logoLabel = new JLabel(new ImageIcon(logoIcon.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));
        JLabel textLabel = new JLabel("");
        textLabel.setForeground(Color.WHITE);
        textLabel.setFont(new Font("Arial", Font.BOLD, 16));
        logoPanel.add(logoLabel);
        logoPanel.add(textLabel);
        logoPanel.setBackground(new Color(46, 58, 89));
        gbc.gridx = 0;
        gbc.gridy = 0;
        menuPanel.add(logoPanel, gbc);


        // Danh sách các chức năng
        String[] menuItems = {
                "Home", "Quản lý nhân viên", "Quản lý chuyến tàu", "Quản lý tuyến tàu",
                "Quản lý tàu", "Quản lý khách hàng", "Tra cứu thông tin", "Thống kê", "My account"
        };
        String[] iconPaths = {
                "main/java/iuh/icons/home.png", "main/java/iuh/icons/quanlynhanvien.png", "main/java/iuh/icons/quanlychuyentau.png", "main/java/iuh/icons/quanlytuyentau.png",
                "main/java/iuh/icons/quanlytau.png", "main/java/iuh/icons/quanlykhachhang.png", "main/java/iuh/icons/tra cuu.png", "main/java/iuh/icons/thong ke.png", "main/java/iuh/icons/Myacount.png"
        };

        // Thêm các nút menu
        for (int i = 0; i < menuItems.length; i++) {
            JButton menuButton = createMenuButton(menuItems[i], iconPaths[i]);
            gbc.gridy = i + 1;
            menuPanel.add(menuButton, gbc);
        }

        return menuPanel;
    }

    // Tạo nút menu với biểu tượng và hiệu ứng hover
    private JButton createMenuButton(String text, String iconPath) {
        JButton menuButton = new JButton();
        menuButton.setPreferredSize(new Dimension(200, 40));
        menuButton.setBackground(new Color(46, 58, 89));
        menuButton.setForeground(new Color(93, 121, 177, 100));
        menuButton.setBorderPainted(false); // Bỏ viền nút
        menuButton.setFocusPainted(false); // Bỏ viền focus
        menuButton.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        ImageIcon icon = new ImageIcon(iconPath);
        JLabel iconLabel = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        JLabel textLabel = new JLabel(text);
        textLabel.setForeground(new Color(93, 121, 177)); // Màu #5D79B1
        textLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        menuButton.add(iconLabel);
        menuButton.add(textLabel);

        // Hiệu ứng hover
        menuButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                menuButton.setBackground(new Color(255, 255, 255)); // Màu #3E4A69 khi hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                menuButton.setBackground(new Color(46, 58, 89)); // Trở về màu gốc
            }
        });

        // Sự kiện nhấp chuột để mở màn hình mới từ lớp khác
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (text.equals("Home")) {
                    updateMainArea(new HomeScreen().getPanel());
                } else if (text.equals("Quản lý nhân viên")) {
                    updateMainArea(new QuanLyNhanVienScreen().getPanel());
                } else if (text.equals("Quản lý chuyến tàu")) {
                    updateMainArea(new QuanLyChuyenTauScreen().getPanel());
                } else if (text.equals("Quản lý tuyến tàu")) {
                    updateMainArea(new QuanLyTuyenTauScreen().getPanel());
                } else if (text.equals("Quản lý tàu")) {
                    updateMainArea(new QuanLyTauScreen().getPanel());
                } else if (text.equals("Quản lý khách hàng")) {
                    updateMainArea(new QuanLyKhachHangScreen().getPanel());
                } else if (text.equals("Tra cứu thông tin")) {
                    updateMainArea(new TraCuuThongTinScreen().getPanel());
                } else if (text.equals("Thống kê")) {
                    updateMainArea(new ThongKeScreen().getPanel());
                } else if (text.equals("My account")) {
                    updateMainArea(new MyAccountScreen().getPanel());
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

        // Phần trống (chờ bổ sung chức năng)
        JPanel emptyArea = new JPanel();
        emptyArea.setBackground(new Color(245, 245, 245));
        mainArea.add(emptyArea, BorderLayout.CENTER);

        return mainArea;
    }

    // Tạo Thanh Taskbar
    private JPanel createTaskbar() {
        JPanel taskbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        taskbar.setPreferredSize(new Dimension(0, 40)); // Tăng chiều rộng lên 800 pixel
        taskbar.setBackground(Color.WHITE);
        taskbar.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, new Color(173, 216, 230))); // Viền #ADD8E6

        // Ngày tháng (sẽ được cập nhật tự động)
        JLabel dateLabel = new JLabel();
        dateLabel.setFont(new Font("Arial", Font.BOLD, 20));
        dateLabel.setForeground(Color.BLACK);
        updateDateTime(dateLabel); // Cập nhật lần đầu

        // Spacer để đẩy logo thông báo sang phải
        JLabel spacer = new JLabel();
        spacer.setPreferredSize(new Dimension(600, 0)); // Điều chỉnh độ rộng tùy ý

        // Logo thông báo
        ImageIcon notificationIcon = new ImageIcon("main/java/iuh/icons/notification.png");
        JLabel notificationLabel = new JLabel(new ImageIcon(notificationIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));

        // Thêm các thành phần vào taskbar
        taskbar.add(dateLabel);
        taskbar.add(spacer);
        taskbar.add(notificationLabel);

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
        // cho  màu xanh phù hợp với màu chủ đạo của giao diện
        label.setText(dateTime);
        label.setForeground(new Color(46, 58, 89)); // Màu xanh đậm #2E3A59

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

    public static void main(String[] args) {

        // Chạy ứng dụng trên Event Dispatch Thread

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GaSaiGonUI();
            }
        });
    }
}
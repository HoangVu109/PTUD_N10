package iuh.gui.QuanLy;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FormSuaThongTinMyAccount extends JFrame {
    public FormSuaThongTinMyAccount() {
        super("Chỉnh sửa thông tin cá nhân");
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // 1. BigTitle
        JPanel bigTitlePanel = new JPanel();
        bigTitlePanel.setBackground(new Color(46, 58, 89));
        bigTitlePanel.setLayout(new BoxLayout(bigTitlePanel, BoxLayout.Y_AXIS));
        bigTitlePanel.setPreferredSize(new Dimension(0, 100));

        JLabel titleLabel = new JLabel("Thông tin cá nhân", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        bigTitlePanel.add(Box.createVerticalGlue());
        bigTitlePanel.add(titleLabel);
        bigTitlePanel.add(Box.createVerticalGlue());

        // 2. inforPanel
        JPanel inforPanel = new JPanel(new BorderLayout());
        inforPanel.setBackground(Color.WHITE);

        // smallPanel
        JPanel smallPanel = new JPanel();
        smallPanel.setBackground(Color.WHITE);
        smallPanel.setLayout(new BoxLayout(smallPanel, BoxLayout.Y_AXIS));
        smallPanel.setPreferredSize(new Dimension(0, 200));

        // Hình tròn avatar
        JPanel avatarPanel = new JPanel();
        avatarPanel.setBackground(Color.WHITE);
        JLabel avatarLabel = new JLabel();
        avatarLabel.setIcon(createCircularAvatar());
        avatarLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        avatarPanel.add(avatarLabel);

        JLabel usernameLabel = new JLabel("Võ Trần Gia Huy", SwingConstants.CENTER);
        usernameLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel emailLabel = new JLabel("vtrangiahuy1602@gmail.com", SwingConstants.CENTER);
        emailLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton uploadButton = new JButton("Upload profile picture");
        uploadButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        smallPanel.add(Box.createVerticalStrut(20));
        smallPanel.add(avatarPanel);
        smallPanel.add(Box.createVerticalStrut(10));
        smallPanel.add(usernameLabel);
        smallPanel.add(Box.createVerticalStrut(5));
        smallPanel.add(emailLabel);
        smallPanel.add(Box.createVerticalStrut(10));
        smallPanel.add(uploadButton);
        smallPanel.add(Box.createVerticalGlue());
        smallPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

        // bigPanel
        JPanel bigPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        bigPanel.setBackground(Color.WHITE);
        bigPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // leftPanel (5 thông tin)
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));

        // Mã Nhân viên
        JPanel maNhanVienRow = new JPanel();
        maNhanVienRow.setBackground(Color.WHITE);
        maNhanVienRow.setLayout(new BoxLayout(maNhanVienRow, BoxLayout.X_AXIS));
        maNhanVienRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        JLabel maNhanVienLabel = new JLabel("Mã Nhân viên:");
        maNhanVienLabel.setPreferredSize(new Dimension(150, 30));
        maNhanVienLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        JTextField maNhanVienField = new JTextField("");
        maNhanVienField.setPreferredSize(new Dimension(200, 30));
        maNhanVienField.setMaximumSize(new Dimension(200, 30));
        maNhanVienField.setEditable(false); // Không cho chỉnh sửa
        maNhanVienRow.add(maNhanVienLabel);
        maNhanVienRow.add(Box.createHorizontalStrut(10));
        maNhanVienRow.add(maNhanVienField);
        maNhanVienRow.add(Box.createHorizontalGlue());

        // Họ và tên
        JPanel hoTenRow = new JPanel();
        hoTenRow.setBackground(Color.WHITE);
        hoTenRow.setLayout(new BoxLayout(hoTenRow, BoxLayout.X_AXIS));
        hoTenRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        JLabel hoTenLabel = new JLabel("Họ và tên:");
        hoTenLabel.setPreferredSize(new Dimension(150, 30));
        hoTenLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        JTextField hoTenField = new JTextField("");
        hoTenField.setPreferredSize(new Dimension(200, 30));
        hoTenField.setMaximumSize(new Dimension(200, 30));
        hoTenRow.add(hoTenLabel);
        hoTenRow.add(Box.createHorizontalStrut(10));
        hoTenRow.add(hoTenField);
        hoTenRow.add(Box.createHorizontalGlue());

        // Giới tính
        JPanel gioiTinhRow = new JPanel();
        gioiTinhRow.setBackground(Color.WHITE);
        gioiTinhRow.setLayout(new BoxLayout(gioiTinhRow, BoxLayout.X_AXIS));
        gioiTinhRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        JLabel gioiTinhLabel = new JLabel("Giới tính:");
        gioiTinhLabel.setPreferredSize(new Dimension(150, 30));
        gioiTinhLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        JTextField gioiTinhField = new JTextField("");
        gioiTinhField.setPreferredSize(new Dimension(200, 30));
        gioiTinhField.setMaximumSize(new Dimension(200, 30));
        gioiTinhRow.add(gioiTinhLabel);
        gioiTinhRow.add(Box.createHorizontalStrut(10));
        gioiTinhRow.add(gioiTinhField);
        gioiTinhRow.add(Box.createHorizontalGlue());

        // Số điện thoại
        JPanel soDienThoaiRow = new JPanel();
        soDienThoaiRow.setBackground(Color.WHITE);
        soDienThoaiRow.setLayout(new BoxLayout(soDienThoaiRow, BoxLayout.X_AXIS));
        soDienThoaiRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        JLabel soDienThoaiLabel = new JLabel("Số điện thoại:");
        soDienThoaiLabel.setPreferredSize(new Dimension(150, 30));
        soDienThoaiLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        JTextField soDienThoaiField = new JTextField("");
        soDienThoaiField.setPreferredSize(new Dimension(200, 30));
        soDienThoaiField.setMaximumSize(new Dimension(200, 30));
        soDienThoaiRow.add(soDienThoaiLabel);
        soDienThoaiRow.add(Box.createHorizontalStrut(10));
        soDienThoaiRow.add(soDienThoaiField);
        soDienThoaiRow.add(Box.createHorizontalGlue());

        // Mật khẩu
        JPanel matKhauRow = new JPanel();
        matKhauRow.setBackground(Color.WHITE);
        matKhauRow.setLayout(new BoxLayout(matKhauRow, BoxLayout.X_AXIS));
        matKhauRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        JLabel matKhauLabel = new JLabel("Mật khẩu:");
        matKhauLabel.setPreferredSize(new Dimension(150, 30));
        matKhauLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        JTextField matKhauField = new JTextField("");
        matKhauField.setPreferredSize(new Dimension(200, 30));
        matKhauField.setMaximumSize(new Dimension(200, 30));
        matKhauRow.add(matKhauLabel);
        matKhauRow.add(Box.createHorizontalStrut(10));
        matKhauRow.add(matKhauField);
        matKhauRow.add(Box.createHorizontalGlue());

        leftPanel.add(maNhanVienRow);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(hoTenRow);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(gioiTinhRow);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(soDienThoaiRow);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(matKhauRow);
        leftPanel.add(Box.createVerticalGlue());

        // rightPanel (4 thông tin)
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        // Số CCCD
        JPanel soCCCDRow = new JPanel();
        soCCCDRow.setBackground(Color.WHITE);
        soCCCDRow.setLayout(new BoxLayout(soCCCDRow, BoxLayout.X_AXIS));
        soCCCDRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        JLabel soCCCDLabel = new JLabel("Số CCCD:");
        soCCCDLabel.setPreferredSize(new Dimension(150, 30));
        soCCCDLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        JTextField soCCCDField = new JTextField("");
        soCCCDField.setPreferredSize(new Dimension(200, 30));
        soCCCDField.setMaximumSize(new Dimension(200, 30));
        soCCCDRow.add(soCCCDLabel);
        soCCCDRow.add(Box.createHorizontalStrut(10));
        soCCCDRow.add(soCCCDField);
        soCCCDRow.add(Box.createHorizontalGlue());

        // Ngày sinh
        JPanel ngaySinhRow = new JPanel();
        ngaySinhRow.setBackground(Color.WHITE);
        ngaySinhRow.setLayout(new BoxLayout(ngaySinhRow, BoxLayout.X_AXIS));
        ngaySinhRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        JLabel ngaySinhLabel = new JLabel("Ngày sinh:");
        ngaySinhLabel.setPreferredSize(new Dimension(150, 30));
        ngaySinhLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        JTextField ngaySinhField = new JTextField("");
        ngaySinhField.setPreferredSize(new Dimension(200, 30));
        ngaySinhField.setMaximumSize(new Dimension(200, 30));
        ngaySinhRow.add(ngaySinhLabel);
        ngaySinhRow.add(Box.createHorizontalStrut(10));
        ngaySinhRow.add(ngaySinhField);
        ngaySinhRow.add(Box.createHorizontalGlue());

        // Địa chỉ
        JPanel diaChiRow = new JPanel();
        diaChiRow.setBackground(Color.WHITE);
        diaChiRow.setLayout(new BoxLayout(diaChiRow, BoxLayout.X_AXIS));
        diaChiRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        JLabel diaChiLabel = new JLabel("Địa chỉ:");
        diaChiLabel.setPreferredSize(new Dimension(150, 30));
        diaChiLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        JTextField diaChiField = new JTextField("");
        diaChiField.setPreferredSize(new Dimension(200, 30));
        diaChiField.setMaximumSize(new Dimension(200, 30));
        diaChiRow.add(diaChiLabel);
        diaChiRow.add(Box.createHorizontalStrut(10));
        diaChiRow.add(diaChiField);
        diaChiRow.add(Box.createHorizontalGlue());

        // Chức vụ
        JPanel chucVuRow = new JPanel();
        chucVuRow.setBackground(Color.WHITE);
        chucVuRow.setLayout(new BoxLayout(chucVuRow, BoxLayout.X_AXIS));
        chucVuRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        JLabel chucVuLabel = new JLabel("Chức vụ:");
        chucVuLabel.setPreferredSize(new Dimension(150, 30));
        chucVuLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        JComboBox<String> chucVuCombo = new JComboBox<>();
        chucVuCombo.setPreferredSize(new Dimension(200, 30));
        chucVuCombo.setMaximumSize(new Dimension(200, 30));
        chucVuRow.add(chucVuLabel);
        chucVuRow.add(Box.createHorizontalStrut(10));
        chucVuRow.add(chucVuCombo);
        chucVuRow.add(Box.createHorizontalGlue());

        rightPanel.add(soCCCDRow);
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(ngaySinhRow);
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(diaChiRow);
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(chucVuRow);
        rightPanel.add(Box.createVerticalGlue());

        bigPanel.add(leftPanel);
        bigPanel.add(rightPanel);

        inforPanel.add(smallPanel, BorderLayout.NORTH);
        inforPanel.add(bigPanel, BorderLayout.CENTER);

        // 3. btnPanel
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnPanel.setBackground(Color.WHITE);

        JButton confirmButton = new JButton("Xác nhận");
        confirmButton.setBackground(new Color(51, 153, 255));
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        confirmButton.setPreferredSize(new Dimension(150, 35));
        confirmButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Thông tin đã được cập nhật thành công!", "Xác nhận", JOptionPane.INFORMATION_MESSAGE);
            dispose(); // Đóng form sau khi xác nhận
        });

        JButton cancelButton = new JButton("Hủy");
        cancelButton.setBackground(new Color(255, 102, 102));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        cancelButton.setPreferredSize(new Dimension(150, 35));
        cancelButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Hủy chỉnh sửa thông tin.", "Hủy", JOptionPane.WARNING_MESSAGE);
            dispose(); // Đóng form khi hủy
        });

        btnPanel.add(confirmButton);
        btnPanel.add(cancelButton);

        // Thêm các panel vào JFrame
        add(bigTitlePanel, BorderLayout.NORTH);
        add(inforPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
    }

    // Hàm tạo avatar tròn (giả lập)
    private ImageIcon createCircularAvatar() {
        int size = 100;
        ImageIcon icon = new ImageIcon(new ImageIcon("path/to/default/avatar.png") // Thay bằng đường dẫn thực tế nếu có
                .getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH));
        return icon;
    }

    // Main để kiểm tra giao diện
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FormSuaThongTinMyAccount().setVisible(true);
        });
    }
}
package iuh.form.QuanLyForm.QuanLyNhanVienForm;

import iuh.dao.QuanLyDAO.QuanLyNhanVienDao;
import iuh.screen.QuanLy.QuanLyNhanVienScreen;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class SuaNhanVienForm extends JFrame {
    private JTextField maNVField, hoTenField, cccdField, soDTField, diaChiField, matKhauField;
    private JDateChooser ngaySinhChooser;
    private JComboBox<String> chucVuComboBox, gioiTinhComboBox;
    private QuanLyNhanVienDao dao;
    private QuanLyNhanVienScreen parentScreen;

    public SuaNhanVienForm(QuanLyNhanVienScreen parentScreen, String maNV, String hoTen, String chucVu, String gioiTinh,
                           String cccd, String soDT, String ngaySinh, String diaChi, String matKhau) {
        this.parentScreen = parentScreen;
        this.dao = new QuanLyNhanVienDao();
        setTitle("Sửa nhân viên");
        setSize(450, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setUndecorated(true);

        // Panel chính
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(245, 248, 250));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Tiêu đề
        JLabel titleLabel = new JLabel("Thông tin nhân viên", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(new Color(30, 144, 255));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(20));

        // Các trường nhập liệu
        mainPanel.add(createInputPanel("Mã nhân viên:", maNVField = new JTextField(maNV, 15)));
        maNVField.setEditable(false);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(createInputPanel("Họ và tên:", hoTenField = new JTextField(hoTen, 15)));
        mainPanel.add(Box.createVerticalStrut(15));
        chucVuComboBox = new JComboBox<>(new String[]{"Quản lý", "Nhân viên"});
        chucVuComboBox.setPreferredSize(new Dimension(200, 30));
        chucVuComboBox.setSelectedItem(chucVu.equals("0") ? "Quản lý" : "Nhân viên");
        mainPanel.add(createInputPanel("Chức vụ:", chucVuComboBox));
        mainPanel.add(Box.createVerticalStrut(15));
        gioiTinhComboBox = new JComboBox<>(new String[]{"Nam", "Nữ"});
        gioiTinhComboBox.setPreferredSize(new Dimension(200, 30));
        gioiTinhComboBox.setSelectedItem(gioiTinh.equals("0") ? "Nam" : "Nữ");
        mainPanel.add(createInputPanel("Giới tính:", gioiTinhComboBox));
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(createInputPanel("Số CCCD:", cccdField = new JTextField(cccd, 15)));
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(createInputPanel("Số ĐT:", soDTField = new JTextField(soDT, 15)));
        mainPanel.add(Box.createVerticalStrut(15));

        ngaySinhChooser = new JDateChooser();
        ngaySinhChooser.setDateFormatString("yyyy-MM-dd");
        ngaySinhChooser.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            ngaySinhChooser.setDate(dateFormat.parse(ngaySinh));
        } catch (Exception e) {
            ngaySinhChooser.setDate(null);
        }
        ngaySinhChooser.setPreferredSize(new Dimension(200, 30));
        mainPanel.add(createInputPanel("Ngày sinh:", ngaySinhChooser));
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(createInputPanel("Địa chỉ:", diaChiField = new JTextField(diaChi, 15)));
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(createInputPanel("Mật khẩu:", matKhauField = new JTextField(matKhau, 15)));
        mainPanel.add(Box.createVerticalStrut(20));

        // Panel nút điều khiển
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(new Color(245, 248, 250));

        JButton confirmButton = new JButton("Xác nhận");
        confirmButton.setBackground(new Color(0, 120, 215));
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        confirmButton.setPreferredSize(new Dimension(100, 35));
        confirmButton.setFocusPainted(false);
        confirmButton.addActionListener(e -> updateNhanVien());
        buttonPanel.add(confirmButton);

        JButton cancelButton = new JButton("Hủy");
        cancelButton.setBackground(new Color(255, 102, 102));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cancelButton.setPreferredSize(new Dimension(100, 35));
        cancelButton.setFocusPainted(false);
        cancelButton.addActionListener(e -> dispose());
        buttonPanel.add(cancelButton);

        mainPanel.add(buttonPanel);
        add(mainPanel, BorderLayout.CENTER);
    }

    private JPanel createInputPanel(String labelText, JComponent inputComponent) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(new Color(245, 248, 250));
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setForeground(new Color(80, 80, 80));
        label.setPreferredSize(new Dimension(120, 30));
        inputComponent.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(label);
        panel.add(inputComponent);
        return panel;
    }

    private void updateNhanVien() {
        try {
            String maNV = maNVField.getText().trim();
            String hoTen = hoTenField.getText().trim();
            String cccd = cccdField.getText().trim();
            String soDT = soDTField.getText().trim();
            java.util.Date ngaySinhUtil = ngaySinhChooser.getDate();
            String diaChi = diaChiField.getText().trim();
            String matKhau = matKhauField.getText().trim();
            int chucVu = chucVuComboBox.getSelectedIndex();
            int gioiTinh = gioiTinhComboBox.getSelectedIndex();
            boolean daNghiViec = false; // Mặc định là đang làm việc

            // Kiểm tra dữ liệu
            if (hoTen.isEmpty() || cccd.isEmpty() || soDT.isEmpty() || ngaySinhUtil == null || matKhau.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!cccd.matches("\\d{12}")) {
                JOptionPane.showMessageDialog(this, "Số CCCD phải có đúng 12 chữ số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!soDT.matches("\\d{10,15}")) {
                JOptionPane.showMessageDialog(this, "Số điện thoại phải có từ 10 đến 15 chữ số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            long ageInMillis = System.currentTimeMillis() - ngaySinhUtil.getTime();
            long ageInYears = ageInMillis / (1000L * 60 * 60 * 24 * 365);
            if (ageInYears < 18) {
                JOptionPane.showMessageDialog(this, "Nhân viên phải từ 18 tuổi trở lên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Date ngaySinh = new Date(ngaySinhUtil.getTime());

            // Cập nhật nhân viên trong cơ sở dữ liệu
            boolean success = dao.updateNhanVien(maNV, cccd, hoTen, ngaySinh, gioiTinh, soDT, diaChi, chucVu, matKhau, daNghiViec);
            if (success) {
                JOptionPane.showMessageDialog(this, "Cập nhật nhân viên thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                parentScreen.getController().loadInitialData();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật nhân viên thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi, vui lòng thử lại: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}
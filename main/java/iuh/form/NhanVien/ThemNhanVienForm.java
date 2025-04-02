package iuh.form.NhanVien;

import iuh.dao.QuanLyDao.NhanVienDao;
import iuh.gui.QuanLy.QuanLyNhanVienScreen;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;

public class ThemNhanVienForm extends JFrame {
    private JTextField maNVField, hoTenField, cccdField, soDTField, diaChiField, matKhauField;
    private JDateChooser ngaySinhChooser;
    private JComboBox<String> chucVuComboBox, gioiTinhComboBox;
    private NhanVienDao dao;
    private QuanLyNhanVienScreen parentScreen;

    public ThemNhanVienForm(QuanLyNhanVienScreen parentScreen) {
        this.parentScreen = parentScreen;
        this.dao = new NhanVienDao();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Thêm nhân viên");
        setSize(450, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setUndecorated(true);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(245, 248, 250));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Thêm thông tin nhân viên mới", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(new Color(30, 144, 255));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(20));

        mainPanel.add(createInputPanel("Mã nhân viên:", maNVField = new JTextField(15)));
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(createInputPanel("Họ và tên:", hoTenField = new JTextField(15)));
        mainPanel.add(Box.createVerticalStrut(15));
        chucVuComboBox = new JComboBox<>(new String[]{"Quản lý", "Nhân viên"});
        chucVuComboBox.setPreferredSize(new Dimension(200, 30));
        mainPanel.add(createInputPanel("Chức vụ:", chucVuComboBox));
        mainPanel.add(Box.createVerticalStrut(15));
        gioiTinhComboBox = new JComboBox<>(new String[]{"Nam", "Nữ"});
        gioiTinhComboBox.setPreferredSize(new Dimension(200, 30));
        mainPanel.add(createInputPanel("Giới tính:", gioiTinhComboBox));
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(createInputPanel("Số CCCD:", cccdField = new JTextField(15)));
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(createInputPanel("Số ĐT:", soDTField = new JTextField(15)));
        mainPanel.add(Box.createVerticalStrut(15));

        ngaySinhChooser = new JDateChooser();
        ngaySinhChooser.setDateFormatString("dd/MM/yyyy");

        ngaySinhChooser.setPreferredSize(new Dimension(200, 30));

        mainPanel.add(createInputPanel("Ngày sinh:", ngaySinhChooser));
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(createInputPanel("Địa chỉ:", diaChiField = new JTextField(15)));
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(createInputPanel("Mật khẩu:", matKhauField = new JTextField(15)));
        mainPanel.add(Box.createVerticalStrut(20));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(new Color(245, 248, 250));

        JButton confirmButton = new JButton("Xác nhận");
        confirmButton.setBackground(new Color(0, 120, 215));
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        confirmButton.setPreferredSize(new Dimension(100, 35));
        confirmButton.addActionListener(e -> addNhanVien());
        buttonPanel.add(confirmButton);

        JButton cancelButton = new JButton("Hủy");
        cancelButton.setBackground(new Color(255, 102, 102));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cancelButton.setPreferredSize(new Dimension(100, 35));
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
        label.setPreferredSize(new Dimension(120, 30));
        inputComponent.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        if (inputComponent instanceof JTextField) {
            ((JTextField) inputComponent).setPreferredSize(new Dimension(250, 30));
        }
        panel.add(label);
        panel.add(inputComponent);
        return panel;
    }

    private void addNhanVien() {
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

            if (maNV.isEmpty() || hoTen.isEmpty() || cccd.isEmpty() || soDT.isEmpty() || ngaySinhUtil == null || matKhau.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!maNV.matches("^(BV|QL)\\d{4}$")) {
                JOptionPane.showMessageDialog(this, "Mã nhân viên phải bắt đầu bằng 'BV' hoặc 'QL' và có 4 chữ số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
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
            boolean success = dao.addNhanVien(maNV, cccd, hoTen, ngaySinh, gioiTinh, soDT, diaChi, chucVu, matKhau, false);

            if (success) {
                JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                parentScreen.getController().loadInitialData();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm nhân viên thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}
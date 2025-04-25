package iuh.form.QuanLyForm;

import iuh.dao.QuanLyDAO.QuanLyHanhKhachDao;
import iuh.screen.QuanLy.QuanLyHanhKhachScreen;

import javax.swing.*;
import java.awt.*;


public class SuaHanhKhachForm extends JFrame {
    private JTextField soCCCDField, hoTenField, soDTField, diaChiField;
    private QuanLyHanhKhachDao dao;
    private QuanLyHanhKhachScreen parentScreen;

    public SuaHanhKhachForm(QuanLyHanhKhachScreen parentScreen, String soCCCD) {
        this.parentScreen = parentScreen;
        this.dao = new QuanLyHanhKhachDao();
        setTitle("Sửa hành khách");
        setSize(450, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setUndecorated(true);

        // Load customer data
        iuh.model.HanhKhach hanhKhach = dao.getHanhKhachByCCCD(soCCCD);

        // Panel chính
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(245, 248, 250));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Tiêu đề
        JLabel titleLabel = new JLabel("Thông tin hành khách", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(new Color(30, 144, 255));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(20));

        // Các trường nhập liệu
        mainPanel.add(createInputPanel("Số CCCD:", soCCCDField = new JTextField(hanhKhach.getSoCCCD(), 15)));
        soCCCDField.setEditable(false);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(createInputPanel("Họ và tên:", hoTenField = new JTextField(hanhKhach.getHoTenHK(), 15)));
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(createInputPanel("Số ĐT:", soDTField = new JTextField(hanhKhach.getSoDT(), 15)));
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(createInputPanel("Địa chỉ:", diaChiField = new JTextField(hanhKhach.getDiaChi(), 15)));
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
        confirmButton.addActionListener(e -> updateHanhKhach());
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

    private void updateHanhKhach() {
        try {
            String soCCCD = soCCCDField.getText().trim();
            String hoTen = hoTenField.getText().trim();
            String soDT = soDTField.getText().trim();
            String diaChi = diaChiField.getText().trim();

            // Kiểm tra dữ liệu
            if (hoTen.isEmpty() || soDT.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin bắt buộc!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!soCCCD.matches("\\d{12}")) {
                JOptionPane.showMessageDialog(this, "Số CCCD phải có đúng 12 chữ số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!soDT.matches("\\d{10,15}")) {
                JOptionPane.showMessageDialog(this, "Số điện thoại phải có từ 10 đến 15 chữ số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Cập nhật hành khách trong cơ sở dữ liệu
            boolean success = dao.updateHanhKhach(soCCCD, hoTen, soDT, diaChi);
            if (success) {
                JOptionPane.showMessageDialog(this, "Cập nhật hành khách thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                parentScreen.getController().loadInitialData();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật hành khách thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi, vui lòng thử lại: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}
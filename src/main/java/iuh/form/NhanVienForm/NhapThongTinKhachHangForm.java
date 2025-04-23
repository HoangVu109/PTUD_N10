package iuh.form.NhanVienForm;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NhapThongTinKhachHangForm extends JFrame {
    // Hằng số màu sắc
    private static final Color BACKGROUND_COLOR = new Color(240, 242, 245); // Màu nền nhẹ
    private static final Color PRIMARY_COLOR = new Color(25, 118, 210); // Xanh dương nhẹ
    private static final Color TEXT_COLOR = new Color(33, 37, 41); // Màu chữ đậm
    private static final Color BUTTON_CONFIRM_COLOR = new Color(25, 118, 210); // Xanh dương
    private static final Color BUTTON_CANCEL_COLOR = new Color(220, 53, 69); // Đỏ nhẹ

    // Font
    private static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 18);
    private static final Font REGULAR_FONT = new Font("Segoe UI", Font.PLAIN, 14);

    // Thành phần giao diện
    private JTextField hoTenField;
    private JTextField soCCCDField;
    private JTextField soDienThoaiField;
    private JDateChooser ngayBanVeChooser;
    private JTextField diaChiField;
    private JComboBox<String> doiTuongComboBox;

    public NhapThongTinKhachHangForm() {
        // Thiết lập JFrame
        setTitle("Nhập thông tin khách hàng");
        setSize(400, 480); // Kích thước nhỏ gọn
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(BACKGROUND_COLOR);

        // Panel chính
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Tiêu đề chính
        JLabel titleLabel = new JLabel("Nhập thông tin khách hàng", SwingConstants.CENTER);
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(PRIMARY_COLOR);
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(20));

        // Tạo viền bo góc cho các trường nhập liệu
        Border fieldBorder = BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true);

        // Tạo các trường nhập liệu
        hoTenField = new JTextField();
        hoTenField.setBorder(fieldBorder);
        soCCCDField = new JTextField();
        soCCCDField.setBorder(fieldBorder);
        soDienThoaiField = new JTextField();
        soDienThoaiField.setBorder(fieldBorder);
        ngayBanVeChooser = new JDateChooser();
        ngayBanVeChooser.setDateFormatString("dd/MM/yyyy");
        ngayBanVeChooser.setBorder(fieldBorder);
        diaChiField = new JTextField();
        diaChiField.setBorder(fieldBorder);
        doiTuongComboBox = new JComboBox<>(new String[]{"Người lớn", "Trẻ em", "Người già"});
        doiTuongComboBox.setBorder(fieldBorder);

        // Tạo các panel cho từng trường nhập liệu
        JPanel namePanel = createInputPanel("Họ và tên", hoTenField);
        JPanel idCardPanel = createInputPanel("Số CCCD", soCCCDField);
        JPanel phonePanel = createInputPanel("Số điện thoại", soDienThoaiField);
        JPanel saleDatePanel = createInputPanel("Ngày bán vé", ngayBanVeChooser);
        JPanel addressPanel = createInputPanel("Địa chỉ", diaChiField);
        JPanel typePanel = createInputPanel("Đối tượng", doiTuongComboBox);

        // Thêm các panel vào main panel
        mainPanel.add(namePanel);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(idCardPanel);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(phonePanel);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(saleDatePanel);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(addressPanel);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(typePanel);
        mainPanel.add(Box.createVerticalStrut(20));

        // Panel nút
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(BACKGROUND_COLOR);

        // Nút "Xác nhận"
        JButton confirmButton = new JButton("Xác nhận");
        confirmButton.setBackground(BUTTON_CONFIRM_COLOR);
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setFont(REGULAR_FONT);
        confirmButton.setFocusPainted(false);
        confirmButton.setBorderPainted(false);
        confirmButton.setPreferredSize(new Dimension(100, 35));

        // Nút "Hủy"
        JButton cancelButton = new JButton("Hủy");
        cancelButton.setBackground(BUTTON_CANCEL_COLOR);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFont(REGULAR_FONT);
        cancelButton.setFocusPainted(false);
        cancelButton.setBorderPainted(false);
        cancelButton.setPreferredSize(new Dimension(100, 35));

        // Thêm nút vào panel
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        // Thêm sự kiện cho nút
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateInput()) {
                    JOptionPane.showMessageDialog(NhapThongTinKhachHangForm.this, "Thông tin đã được xác nhận.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // Thêm các thành phần vào frame
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Tạo panel cho các trường nhập liệu
    private JPanel createInputPanel(String labelText, JComponent inputField) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.setBackground(BACKGROUND_COLOR);

        JLabel label = new JLabel(labelText);
        label.setFont(REGULAR_FONT);
        label.setForeground(TEXT_COLOR);
        label.setPreferredSize(new Dimension(90, 30));

        inputField.setFont(REGULAR_FONT);
        inputField.setPreferredSize(new Dimension(250, 30)); // Chiều dài nhỏ gọn hơn
        inputField.setMaximumSize(new Dimension(220, 30));

        panel.add(label);
        panel.add(Box.createHorizontalStrut(10));
        panel.add(inputField);

        return panel;
    }


    // Kiểm tra dữ liệu nhập vào
    private boolean validateInput() {
        // Kiểm tra trường "Họ và tên"
        String hoTen = hoTenField.getText().trim();
        if (hoTen.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập họ và tên.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Kiểm tra trường "Số CCCD"
        String soCCCD = soCCCDField.getText().trim();
        if (soCCCD.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số CCCD.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!soCCCD.matches("\\d{12}")) { // CCCD phải là 12 số
            JOptionPane.showMessageDialog(this, "Số CCCD phải là 12 chữ số.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Kiểm tra trường "Số điện thoại"
        String soDienThoai = soDienThoaiField.getText().trim();
        if (soDienThoai.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!soDienThoai.matches("\\d{10}")) { // Số điện thoại phải là 10 số
            JOptionPane.showMessageDialog(this, "Số điện thoại phải là 10 chữ số.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Kiểm tra trường "Ngày bán vé"
        if (ngayBanVeChooser.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày bán vé.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Kiểm tra trường "Địa chỉ"
        String diaChi = diaChiField.getText().trim();
        if (diaChi.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập địa chỉ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true; // Tất cả các trường hợp lệ
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NhapThongTinKhachHangForm().setVisible(true));
    }
}
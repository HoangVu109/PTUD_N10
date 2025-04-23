package iuh.form.QuanLyForm.QuanLyChuyenTauForm;

import iuh.dao.QuanLyDAO.QuanLyChuyenTauDao;
import iuh.gui.QuanLy.QuanLyChuyenTauScreen;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ThemChuyenTauForm extends JFrame {
    private final JTextField maChuyenTauField;
    private final JComboBox<String> maTauComboBox;
    private final JComboBox<String> tuyenTauComboBox;
    private final JDateChooser gioKhoiHanhChooser;
    private final QuanLyChuyenTauDao dao;
    private final QuanLyChuyenTauScreen parentScreen;

    public ThemChuyenTauForm(QuanLyChuyenTauScreen parentScreen) {
        this.parentScreen = parentScreen;
        this.dao = new QuanLyChuyenTauDao();
        this.maChuyenTauField = new JTextField(15);
        this.maTauComboBox = new JComboBox<>(loadMaTauList().toArray(new String[0]));
        this.tuyenTauComboBox = new JComboBox<>(loadTuyenTauList().toArray(new String[0]));
        this.gioKhoiHanhChooser = new JDateChooser();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Thêm chuyến tàu");
        setSize(450, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setUndecorated(true);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(245, 248, 250));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        mainPanel.add(createTitleLabel());
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(createInputPanel("Mã chuyến tàu:", maChuyenTauField));
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(createInputPanel("Mã tàu:", maTauComboBox));
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(createInputPanel("Giờ khởi hành:", configureDateChooser()));
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(createInputPanel("Tuyến tàu:", tuyenTauComboBox));
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(createButtonPanel());

        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private JLabel createTitleLabel() {
        JLabel titleLabel = new JLabel("Thêm thông tin chuyến tàu", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(new Color(30, 144, 255));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return titleLabel;
    }

    private JPanel createInputPanel(String labelText, JComponent inputComponent) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(new Color(245, 248, 250));
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setPreferredSize(new Dimension(150, 30));
        inputComponent.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        if (inputComponent instanceof JTextField) {
            ((JTextField) inputComponent).setPreferredSize(new Dimension(200, 30));
        } else {
            inputComponent.setPreferredSize(new Dimension(200, 30));
        }
        panel.add(label);
        panel.add(inputComponent);
        return panel;
    }

    private JDateChooser configureDateChooser() {
        gioKhoiHanhChooser.setDateFormatString("dd/MM/yyyy HH:mm");
        return gioKhoiHanhChooser;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(new Color(245, 248, 250));

        JButton confirmButton = new JButton("Xác nhận");
        confirmButton.setBackground(new Color(0, 120, 215));
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        confirmButton.setPreferredSize(new Dimension(100, 35));
        confirmButton.addActionListener(e -> addChuyenTau());

        JButton cancelButton = new JButton("Hủy");
        cancelButton.setBackground(new Color(255, 102, 102));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cancelButton.setPreferredSize(new Dimension(100, 35));
        cancelButton.addActionListener(e -> dispose());

        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);
        return buttonPanel;
    }

    private List<String> loadMaTauList() {
        try {
            return dao.getMaTauList();
        } catch (Exception e) {
            showError("Lỗi khi tải danh sách mã tàu: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private List<String> loadTuyenTauList() {
        try {
            return dao.getTuyenTauList();
        } catch (Exception e) {
            showError("Lỗi khi tải danh sách tuyến tàu: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private void addChuyenTau() {
        try {
            String maChuyenTau = maChuyenTauField.getText().trim();
            String maTau = (String) maTauComboBox.getSelectedItem();
            java.util.Date gioKhoiHanhUtil = gioKhoiHanhChooser.getDate();
            String tuyenTau = (String) tuyenTauComboBox.getSelectedItem();

            if (!validateInput(maChuyenTau, maTau, gioKhoiHanhUtil, tuyenTau)) {
                return;
            }

            Timestamp gioKhoiHanh = new Timestamp(gioKhoiHanhUtil.getTime());
            boolean daBiHuy = false;

            if (dao.addChuyenTau(maChuyenTau, maTau, gioKhoiHanh, daBiHuy)) {
                JOptionPane.showMessageDialog(this, "Thêm chuyến tàu thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                parentScreen.getController().loadInitialData();
                dispose();
            } else {
                showError("Thêm chuyến tàu thất bại! Vui lòng kiểm tra lại thông tin.");
            }
        } catch (Exception e) {
            showError("Lỗi không xác định: " + e.getMessage());
        }
    }

    private boolean validateInput(String maChuyenTau, String maTau, java.util.Date gioKhoiHanhUtil, String tuyenTau) {
        if (maChuyenTau.isEmpty() || maTau == null || gioKhoiHanhUtil == null || tuyenTau == null) {
            showError("Vui lòng điền đầy đủ thông tin!");
            return false;
        }
        if (!maChuyenTau.matches("^CT\\d{4}$")) {
            showError("Mã chuyến tàu phải bắt đầu bằng 'CT' và có 4 chữ số!");
            return false;
        }
        if (gioKhoiHanhUtil.before(Calendar.getInstance().getTime())) {
            showError("Giờ khởi hành phải ở tương lai!");
            return false;
        }
        return true;
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}
package iuh.form.QuanLyForm.QuanLyChuyenTauForm;

import iuh.dao.QuanLyDAO.QuanLyChuyenTauDao;
import iuh.gui.QuanLy.QuanLyChuyenTauScreen;
import iuh.model.ChuyenTau;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SuaChuyenTauForm extends JFrame {
    private final JTextField maChuyenTauField;
    private final JComboBox<String> maTauComboBox;
    private final JComboBox<String> tuyenTauComboBox;
    private final JDateChooser gioKhoiHanhChooser;
    private final JComboBox<Integer> soLuongHKComboBox;
    private final JComboBox<Integer> soLuongHKToiDaComboBox;
    private final QuanLyChuyenTauDao dao;
    private final QuanLyChuyenTauScreen parentScreen;
    private final String maChuyenTau;

    public SuaChuyenTauForm(QuanLyChuyenTauScreen parentScreen, String maChuyenTau) {
        this.parentScreen = parentScreen;
        this.maChuyenTau = maChuyenTau;
        this.dao = new QuanLyChuyenTauDao();
        this.maChuyenTauField = new JTextField(15);
        this.maTauComboBox = new JComboBox<>(loadMaTauList().toArray(new String[0]));
        this.tuyenTauComboBox = new JComboBox<>(loadTuyenTauList().toArray(new String[0]));
        this.gioKhoiHanhChooser = new JDateChooser();
        this.soLuongHKComboBox = new JComboBox<>(createSoLuongHKOptions());
        this.soLuongHKToiDaComboBox = new JComboBox<>(createSoLuongHKToiDaOptions());
        initializeUI();
        loadChuyenTauData();
    }

    private void initializeUI() {
        setTitle("Sửa chuyến tàu");
        setSize(450, 550);
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
        mainPanel.add(createInputPanel("Mã chuyến tàu:", configureMaChuyenTauField()));
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(createInputPanel("Mã tàu:", maTauComboBox));
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(createInputPanel("Giờ khởi hành:", configureDateChooser()));
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(createInputPanel("Số lượng HK tối đa:", soLuongHKToiDaComboBox));
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(createInputPanel("Số lượng HK:", soLuongHKComboBox));
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(createInputPanel("Tuyến tàu:", tuyenTauComboBox));
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(createButtonPanel());

        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private JLabel createTitleLabel() {
        JLabel titleLabel = new JLabel("Sửa thông tin chuyến tàu", JLabel.CENTER);
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

    private JTextField configureMaChuyenTauField() {
        maChuyenTauField.setEditable(false);
        return maChuyenTauField;
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
        confirmButton.addActionListener(e -> updateChuyenTau());

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

    private Integer[] createSoLuongHKOptions() {
        Integer[] options = new Integer[501];
        for (int i = 0; i <= 500; i++) {
            options[i] = i;
        }
        return options;
    }

    private Integer[] createSoLuongHKToiDaOptions() {
        Integer[] options = new Integer[901];
        for (int i = 0; i <= 900; i++) {
            options[i] = 100 + i;
        }
        return options;
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

    private void loadChuyenTauData() {
        List<ChuyenTau> chuyenTauList = dao.getAllChuyenTau();
        ChuyenTau chuyenTau = chuyenTauList.stream()
                .filter(ct -> ct.getMaChuyenTau().equals(maChuyenTau))
                .findFirst()
                .orElse(null);

        if (chuyenTau != null) {
            maChuyenTauField.setText(chuyenTau.getMaChuyenTau());
            maTauComboBox.setSelectedItem(chuyenTau.getMaTau());
            gioKhoiHanhChooser.setDate(chuyenTau.getGioKhoiHanh());
            soLuongHKToiDaComboBox.setSelectedItem(chuyenTau.getSoLuongHKToiDa());
            soLuongHKComboBox.setSelectedItem(chuyenTau.getSoluongHK());
            tuyenTauComboBox.setSelectedItem(chuyenTau.getTuyenTau());
        } else {
            showError("Không tìm thấy chuyến tàu với mã: " + maChuyenTau);
            dispose();
        }
    }

    private void updateChuyenTau() {
        try {
            String maTau = (String) maTauComboBox.getSelectedItem();
            java.util.Date gioKhoiHanhUtil = gioKhoiHanhChooser.getDate();
            int soLuongHKToiDa = (Integer) soLuongHKToiDaComboBox.getSelectedItem();
            int soLuongHK = (Integer) soLuongHKComboBox.getSelectedItem();
            String tuyenTau = (String) tuyenTauComboBox.getSelectedItem();

            if (!validateInput(maTau, gioKhoiHanhUtil, soLuongHK, soLuongHKToiDa, tuyenTau)) {
                return;
            }

            Timestamp gioKhoiHanh = new Timestamp(gioKhoiHanhUtil.getTime());
            boolean daBiHuy = false;

            if (dao.updateChuyenTau(maChuyenTau, maTau, gioKhoiHanh, daBiHuy, soLuongHKToiDa, soLuongHK)) {
                JOptionPane.showMessageDialog(this, "Cập nhật chuyến tàu thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                parentScreen.getController().loadInitialData();
                dispose();
            } else {
                showError("Cập nhật chuyến tàu thất bại! Vui lòng kiểm tra lại thông tin.");
            }
        } catch (Exception e) {
            showError("Lỗi không xác định: " + e.getMessage());
        }
    }

    private boolean validateInput(String maTau, java.util.Date gioKhoiHanhUtil, int soLuongHK, int soLuongHKToiDa, String tuyenTau) {
        if (maTau == null || gioKhoiHanhUtil == null || tuyenTau == null) {
            showError("Vui lòng điền đầy đủ thông tin!");
            return false;
        }
        if (gioKhoiHanhUtil.before(Calendar.getInstance().getTime())) {
            showError("Giờ khởi hành phải ở tương lai!");
            return false;
        }
        if (soLuongHK > soLuongHKToiDa) {
            showError("Số lượng hành khách không được vượt quá số lượng tối đa!");
            return false;
        }
        return true;
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}
package iuh.form.QuanLyForm.QuanLyChuyenTauForm;

import iuh.controller.QuanLyCon.QuanLyChuyenTauController;
import iuh.dao.QuanLyDAO.QuanLyChuyenTauDao;
import iuh.gui.QuanLy.QuanLyChuyenTauScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class XoaChuyenTauForm extends JFrame {
    private final QuanLyChuyenTauScreen view;
    private final QuanLyChuyenTauController controller;
    private final QuanLyChuyenTauDao dao;
    private final int selectedRow;

    public XoaChuyenTauForm(QuanLyChuyenTauScreen view, QuanLyChuyenTauController controller, int selectedRow) {
        this.view = view;
        this.controller = controller;
        this.dao = new QuanLyChuyenTauDao();
        this.selectedRow = selectedRow;
        initializeUI();
    }

    private void initializeUI() {
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn chuyến tàu!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            dispose();
            return;
        }

        setTitle("Xác nhận xóa");
        setSize(350, 180);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        JPanel panel = new JPanel(new BorderLayout(15, 15)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(240, 240, 240));
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20));
                g2.dispose();
            }
        };
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(createMessageLabel(), BorderLayout.NORTH);
        panel.add(createSubMessageLabel(), BorderLayout.CENTER);
        panel.add(createButtonPanel(), BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }

    private JLabel createMessageLabel() {
        JLabel messageLabel = new JLabel("Xóa chuyến tàu này?");
        messageLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        return messageLabel;
    }

    private JLabel createSubMessageLabel() {
        JLabel subMessageLabel = new JLabel("Dữ liệu sẽ bị xóa vĩnh viễn");
        subMessageLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        subMessageLabel.setForeground(new Color(255, 102, 102));
        subMessageLabel.setHorizontalAlignment(JLabel.CENTER);
        return subMessageLabel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 10));
        buttonPanel.setOpaque(false);

        JButton cancelButton = new JButton("Hủy");
        cancelButton.setBackground(new Color(245, 245, 245));
        cancelButton.setForeground(new Color(50, 50, 50));
        cancelButton.addActionListener(e -> dispose());

        JButton deleteButton = new JButton("Xóa");
        deleteButton.setBackground(new Color(255, 102, 102));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.addActionListener(e -> deleteChuyenTau());

        buttonPanel.add(cancelButton);
        buttonPanel.add(deleteButton);
        return buttonPanel;
    }

    private void deleteChuyenTau() {
        String maChuyenTau = (String) view.getTableModel().getValueAt(selectedRow, 0);
        if (dao.deleteChuyenTau(maChuyenTau)) {
            JOptionPane.showMessageDialog(this, "Xóa chuyến tàu thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            controller.loadInitialData();
        } else {
            JOptionPane.showMessageDialog(this, "Xóa chuyến tàu thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        dispose();
    }
}
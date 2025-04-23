package iuh.form.QuanLyForm.QuanLyNhanVienForm;

import iuh.controller.QuanLyCon.QuanLyNhanVienController;
import iuh.dao.QuanLyDAO.QuanLyNhanVienDao;
import iuh.gui.QuanLy.QuanLyNhanVienScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class XoaNhanVienForm extends JFrame {
    private QuanLyNhanVienScreen view;
    private QuanLyNhanVienController controller;
    private QuanLyNhanVienDao model;
    private int selectedRow;

    public XoaNhanVienForm(QuanLyNhanVienScreen view, QuanLyNhanVienController controller, int selectedRow) {
        this.view = view;
        this.controller = controller;
        this.model = new QuanLyNhanVienDao();
        this.selectedRow = selectedRow;
        initializeUI();
    }

    private void initializeUI() {
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên!", "Thông báo", JOptionPane.WARNING_MESSAGE);
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

        JLabel messageLabel = new JLabel("Xóa nhân viên này?");
        messageLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(messageLabel, BorderLayout.NORTH);

        JLabel subMessageLabel = new JLabel("Nhân viên sẽ được đánh dấu đã nghỉ việc");
        subMessageLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        subMessageLabel.setForeground(new Color(255, 102, 102));
        subMessageLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(subMessageLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 10));
        buttonPanel.setOpaque(false);

        JButton cancelButton = new JButton("Hủy");
        cancelButton.setBackground(new Color(245, 245, 245));
        cancelButton.setForeground(new Color(50, 50, 50));
        cancelButton.addActionListener(e -> dispose());
        buttonPanel.add(cancelButton);

        JButton deleteButton = new JButton("Xóa");
        deleteButton.setBackground(new Color(255, 102, 102));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.addActionListener(e -> {
            int originalIndex = controller.getDanhSachGoc().get(selectedRow);
            String maNV = controller.getFullData()[originalIndex][0];

            boolean deleted = model.deleteNhanVien(maNV);
            if (deleted) {
                JOptionPane.showMessageDialog(this, "Xóa nhân viên thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                controller.loadInitialData();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa nhân viên thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
            dispose();
        });
        buttonPanel.add(deleteButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);
        add(panel);
        setVisible(true);
    }
}
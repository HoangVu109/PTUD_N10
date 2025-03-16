package iuh.form;

import iuh.controller.NhanVienController;
import iuh.dao.NhanVienDao;
import iuh.gui.QuanLyNhanVienScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

public class DeleteForm extends JFrame {
    private QuanLyNhanVienScreen view;
    private NhanVienController controller;
    private NhanVienDao model;
    private int selectedRow;

    public DeleteForm(QuanLyNhanVienScreen view, NhanVienController controller, int selectedRow) {
        this.view = view;
        this.controller = controller;
        this.model = new NhanVienDao();
        this.selectedRow = selectedRow;

        initializeUI();
    }

    private void initializeUI() {
        // Kiểm tra nếu chưa chọn nhân viên
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            dispose(); // Đóng form ngay lập tức
            return;
        }

        // Thiết lập JFrame
        setTitle("Xác nhận xóa");
        setSize(350, 180); // Tăng kích thước để giao diện thoáng hơn
        setLocationRelativeTo(null); // Căn giữa màn hình
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        // Panel chính với viền bo tròn và hiệu ứng đổ bóng
        JPanel panel = new JPanel(new BorderLayout(15, 15)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(240, 240, 240)); // Màu nền nhạt
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20)); // Viền bo tròn
                g2.setColor(new Color(0, 0, 0, 50)); // Đổ bóng nhẹ
                g2.fill(new RoundRectangle2D.Float(5, 5, getWidth() - 10, getHeight() - 10, 15, 15));
                g2.dispose();
            }
        };
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(Color.WHITE);

        // Label thông báo chính
        JLabel messageLabel = new JLabel("Xoá nhân viên này ?");
        messageLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        messageLabel.setForeground(new Color(50, 50, 50));
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(messageLabel, BorderLayout.NORTH);

        // Label phụ
        JLabel subMessageLabel = new JLabel("Nếu đã xoá không thể phục hồi");
        subMessageLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        subMessageLabel.setForeground(// màu đỏ
                new Color(255, 102, 102));
        subMessageLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(subMessageLabel, BorderLayout.CENTER);

        // Panel cho các nút
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 10));
        buttonPanel.setOpaque(false); // Làm trong suốt để hiển thị nền của panel chính

        // Nút Cancel với hiệu ứng
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cancelButton.setFocusPainted(false);
        cancelButton.setBackground(new Color(245, 245, 245)); // Màu nền nhạt
        cancelButton.setForeground(new Color(50, 50, 50)); // Màu chữ tối
        cancelButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
                BorderFactory.createEmptyBorder(8, 15, 8, 15)));
        cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Chỉ đóng JFrame
            }
        });
        cancelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cancelButton.setBackground(new Color(230, 230, 230));
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cancelButton.setBackground(new Color(245, 245, 245));
            }
        });
        buttonPanel.add(cancelButton);

        // Nút Delete với hiệu ứng
        JButton deleteButton = new JButton("Delete");
        deleteButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        deleteButton.setBackground(new Color(255, 102, 102)); // Màu đỏ cam
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFocusPainted(false);
        deleteButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 80, 80), 1, true),
                BorderFactory.createEmptyBorder(8, 15, 8, 15)));
        deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy mã nhân viên từ hàng được chọn
                int originalIndex = controller.getOriginalIndices().get(selectedRow);
                String maNV = controller.getFullData()[originalIndex][0];

                // Xóa khỏi cơ sở dữ liệu
                boolean deleted = model.deleteNhanVien(maNV);
                if (deleted) {
                    // Xóa khỏi JTable
                    view.getTableModel().removeRow(selectedRow);
                    controller.getOriginalIndices().remove(selectedRow);
                    JOptionPane.showMessageDialog(DeleteForm.this, "Xóa nhân viên thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(DeleteForm.this, "Xóa nhân viên thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
                dispose(); // Đóng JFrame sau khi xóa
            }
        });
        deleteButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                deleteButton.setBackground(new Color(220, 80, 80));
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                deleteButton.setBackground(new Color(255, 102, 102));
            }
        });
        buttonPanel.add(deleteButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);
        add(panel);

        // Hiển thị JFrame
        //Tắt chức năng resize
        setResizable(false);
        // tắt thanh tiêu đề
        setUndecorated(true);
        setVisible(true);
    }
}
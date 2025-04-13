package iuh.gui.QuanLy;

import iuh.controller.QuanLyController.QuanLyTuyenTauController;
import iuh.model.TuyenTau;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class QuanLyTuyenTauScreen {
    private JPanel panel;
    private JTable table;
    private DefaultTableModel tableModel;
    private QuanLyTuyenTauController controller;
    private JTextField searchField;

    public QuanLyTuyenTauScreen() {
        // Khởi tạo panel chính
        panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(245, 248, 250));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Tạo các thành phần giao diện
        JPanel findPanel = createFindPanel();
        panel.add(findPanel, BorderLayout.NORTH);

        JPanel tablePanel = createTablePanel();
        panel.add(tablePanel, BorderLayout.CENTER);

        JPanel toolsPanel = createToolsPanel();
        panel.add(toolsPanel, BorderLayout.SOUTH);

        // Khởi tạo controller
        controller = new QuanLyTuyenTauController(tableModel);
        controller.updateTable();
    }

    private JPanel createFindPanel() {
        JPanel findPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 15, 15));
                g2.setColor(new Color(0, 0, 0, 30));
                g2.fill(new RoundRectangle2D.Float(5, 5, getWidth() - 10, getHeight() - 10, 15, 15));
                g2.dispose();
            }
        };
        findPanel.setOpaque(false);
        findPanel.setBackground(Color.WHITE);
        findPanel.setLayout(new BoxLayout(findPanel, BoxLayout.X_AXIS));
        findPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        searchField = new JTextField(20);
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        searchField.setBackground(new Color(245, 245, 245));
        findPanel.add(searchField);

        findPanel.add(Box.createHorizontalStrut(10));

        JButton searchButton = new JButton("Tìm kiếm");
        searchButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchButton.setBackground(new Color(0, 120, 215));
        searchButton.setForeground(Color.WHITE);
        searchButton.setFocusPainted(false);
        searchButton.addActionListener(e -> {
            String searchText = searchField.getText().trim().toLowerCase();
            controller.searchRoute(searchText);
        });

        searchField.addActionListener(e -> {
            String searchText = searchField.getText().trim().toLowerCase();
            controller.searchRoute(searchText);
        });

        findPanel.add(searchButton);

        return findPanel;
    }

    private JPanel createTablePanel() {
        JPanel tablePanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 15, 15));
                g2.setColor(new Color(0, 0, 0, 30));
                g2.fill(new RoundRectangle2D.Float(5, 5, getWidth() - 10, getHeight() - 10, 15, 15));
                g2.dispose();
            }
        };
        tablePanel.setOpaque(false);
        tablePanel.setBackground(Color.WHITE);
        tablePanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titleLabel = new JLabel("Danh sách tuyến tàu", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(new Color(30, 144, 255));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 15, 0));
        tablePanel.add(titleLabel, BorderLayout.NORTH);

        // Tạo bảng
        String[] columnNames = {"STT", "Mã tuyến tàu", "Ga khởi hành", "Ga kết thúc", "Trạng thái"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(30);
        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setShowGrid(true);
        table.setGridColor(new Color(230, 230, 230));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(30, 144, 255));
        table.getTableHeader().setForeground(Color.WHITE);

        // Căn giữa các cột
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        return tablePanel;
    }

    private JPanel createToolsPanel() {
        JPanel toolsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        toolsPanel.setBackground(new Color(240, 240, 240));
        toolsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JButton addButton = new JButton("Thêm mới");
        addButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        addButton.setBackground(new Color(0, 120, 215));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.addActionListener(e -> showAddRouteDialog());

        JButton editButton = new JButton("Sửa");
        editButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        editButton.setBackground(new Color(0, 153, 0));
        editButton.setForeground(Color.WHITE);
        editButton.setFocusPainted(false);
        editButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                showEditRouteDialog(selectedRow);
            } else {
                JOptionPane.showMessageDialog(panel, "Vui lòng chọn một tuyến tàu để sửa!", "Lỗi", JOptionPane.WARNING_MESSAGE);
            }
        });

        JButton deleteButton = new JButton("Xóa");
        deleteButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        deleteButton.setBackground(new Color(255, 102, 102));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFocusPainted(false);
        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                int confirm = JOptionPane.showConfirmDialog(panel, "Bạn có chắc muốn xóa tuyến này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    controller.deleteRoute(selectedRow);
                }
            } else {
                JOptionPane.showMessageDialog(panel, "Vui lòng chọn một tuyến tàu để xóa!", "Lỗi", JOptionPane.WARNING_MESSAGE);
            }
        });

        JButton restoreButton = new JButton("Khôi phục");
        restoreButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        restoreButton.setBackground(new Color(0, 120, 215));
        restoreButton.setForeground(Color.WHITE);
        restoreButton.setFocusPainted(false);
        restoreButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                int confirm = JOptionPane.showConfirmDialog(panel, "Bạn có chắc muốn khôi phục tuyến này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    controller.restoreRoute(selectedRow);
                }
            } else {
                JOptionPane.showMessageDialog(panel, "Vui lòng chọn một tuyến tàu để khôi phục!", "Lỗi", JOptionPane.WARNING_MESSAGE);
            }
        });

        toolsPanel.add(addButton);
        toolsPanel.add(Box.createHorizontalStrut(10));
        toolsPanel.add(editButton);
        toolsPanel.add(Box.createHorizontalStrut(10));
        toolsPanel.add(deleteButton);
        toolsPanel.add(Box.createHorizontalStrut(10));
        toolsPanel.add(restoreButton);

        return toolsPanel;
    }

    private void showAddRouteDialog() {
        JDialog dialog = new JDialog((Frame) null, "Thêm tuyến tàu", true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(400, 250);
        dialog.setLocationRelativeTo(panel);

        // Panel chính chứa các trường nhập liệu
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Khoảng cách giữa các thành phần
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Mã tuyến tàu
        JLabel maTuyenLabel = new JLabel("Mã tuyến tàu:");
        maTuyenLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3; // Tỷ lệ chiều rộng của nhãn
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(maTuyenLabel, gbc);

        JTextField maTuyenField = new JTextField();
        maTuyenField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.weightx = 0.7; // Tỷ lệ chiều rộng của text field
        contentPanel.add(maTuyenField, gbc);

        // Ga khởi hành
        JLabel gaKhoiHanhLabel = new JLabel("Ga khởi hành:");
        gaKhoiHanhLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        contentPanel.add(gaKhoiHanhLabel, gbc);

        JTextField gaKhoiHanhField = new JTextField();
        gaKhoiHanhField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        contentPanel.add(gaKhoiHanhField, gbc);

        // Ga kết thúc
        JLabel gaKetThucLabel = new JLabel("Ga kết thúc:");
        gaKetThucLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        contentPanel.add(gaKetThucLabel, gbc);

        JTextField gaKetThucField = new JTextField();
        gaKetThucField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        contentPanel.add(gaKetThucField, gbc);

        // Panel chứa các nút
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        JButton saveButton = new JButton("Lưu");
        saveButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        saveButton.setBackground(new Color(0, 120, 215));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);
        saveButton.setPreferredSize(new Dimension(80, 30)); // Đặt kích thước cố định cho nút
        saveButton.addActionListener(e -> {
            String maTuyen = maTuyenField.getText();
            String gaKhoiHanh = gaKhoiHanhField.getText();
            String gaKetThuc = gaKetThucField.getText();
            if (!maTuyen.isEmpty() && !gaKhoiHanh.isEmpty() && !gaKetThuc.isEmpty()) {
                controller.addRoute(maTuyen, gaKhoiHanh, gaKetThuc);
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.WARNING_MESSAGE);
            }
        });

        JButton cancelButton = new JButton("Hủy");
        cancelButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cancelButton.setPreferredSize(new Dimension(80, 30)); // Đặt kích thước cố định cho nút
        cancelButton.addActionListener(e -> dialog.dispose());

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        dialog.add(contentPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

    private void showEditRouteDialog(int row) {
        TuyenTau route = controller.getRoute(row);
        if (route == null) {
            JOptionPane.showMessageDialog(panel, "Không tìm thấy tuyến tàu để sửa!", "Lỗi", JOptionPane.WARNING_MESSAGE);
            return;
        }
        JDialog dialog = new JDialog((Frame) null, "Sửa tuyến tàu", true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(panel);

        JPanel contentPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel maTuyenLabel = new JLabel("Mã tuyến tàu:");
        maTuyenLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JTextField maTuyenField = new JTextField(route.getMaTuyenTau());
        maTuyenField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel gaKhoiHanhLabel = new JLabel("Ga khởi hành:");
        gaKhoiHanhLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JTextField gaKhoiHanhField = new JTextField(route.getGaKhoiHanh());
        gaKhoiHanhField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel gaKetThucLabel = new JLabel("Ga kết thúc:");
        gaKetThucLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JTextField gaKetThucField = new JTextField(route.getGaKetThuc());
        gaKetThucField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        contentPanel.add(maTuyenLabel);
        contentPanel.add(maTuyenField);
        contentPanel.add(gaKhoiHanhLabel);
        contentPanel.add(gaKhoiHanhField);
        contentPanel.add(gaKetThucLabel);
        contentPanel.add(gaKetThucField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        JButton saveButton = new JButton("Lưu");
        saveButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        saveButton.setBackground(new Color(0, 120, 215));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);
        saveButton.addActionListener(e -> {
            String maTuyen = maTuyenField.getText();
            String gaKhoiHanh = gaKhoiHanhField.getText();
            String gaKetThuc = gaKetThucField.getText();
            if (!maTuyen.isEmpty() && !gaKhoiHanh.isEmpty() && !gaKetThuc.isEmpty()) {
                controller.editRoute(row, maTuyen, gaKhoiHanh, gaKetThuc);
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.WARNING_MESSAGE);
            }
        });

        JButton cancelButton = new JButton("Hủy");
        cancelButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cancelButton.addActionListener(e -> dialog.dispose());

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        dialog.add(contentPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

    public JPanel getPanel() {
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Quản Lý Tuyến Tàu");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(900, 600);
            frame.add(new QuanLyTuyenTauScreen().getPanel());
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
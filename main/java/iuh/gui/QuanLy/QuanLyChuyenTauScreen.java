package iuh.gui.QuanLy;

import iuh.controller.QuanLyController.QuanLyChuyenTauController;
import iuh.form.ChuyenTau.ThemChuyenTauForm;
import iuh.form.ChuyenTau.SuaChuyenTauForm;
import iuh.form.ChuyenTau.XoaChuyenTauForm;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class QuanLyChuyenTauScreen {
    private JPanel panel;
    private JPanel toolsPanel;
    private JPanel inforPanel;
    private JTable table;
    private DefaultTableModel tableModel;
    private QuanLyChuyenTauController controller;
    // Menu
    public QuanLyChuyenTauScreen() {
        String[] columnNames = {
                "Mã chuyến tàu", "Mã tàu", "Giờ khởi hành", "Tuyến tàu", "Số lượng hành khách", "Số lượng HK tối đa"
        };
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        controller = new QuanLyChuyenTauController(this);

        panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240, 243, 246)); // Màu nền nhẹ
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        toolsPanel = createToolsPanel();
        panel.add(toolsPanel, BorderLayout.NORTH);

        inforPanel = createInforPanel();
        panel.add(inforPanel, BorderLayout.CENTER);

        controller.loadInitialData();
    }
    // Thanh task chua tiim kiem
    private JPanel createToolsPanel() {
        JPanel toolsPanel = new JPanel(new BorderLayout());
        toolsPanel.setBackground(new Color(240, 243, 246));
        toolsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 15, 0));

        // Tiêu đề
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setBackground(new Color(240, 243, 246));
        JLabel titleLabel = new JLabel("Quản lý chuyến tàu");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(new Color(25, 118, 210)); // Màu xanh dương đậm
        titlePanel.add(titleLabel);
        toolsPanel.add(titlePanel, BorderLayout.NORTH);

        // Thanh tìm kiếm
        JPanel findPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 30, 30));
                g2.setColor(new Color(0, 0, 0, 30));
                g2.fill(new RoundRectangle2D.Float(2, 2, getWidth() - 4, getHeight() - 4, 30, 30));
                g2.dispose();
            }
        };
        findPanel.setOpaque(false);
        findPanel.setBackground(Color.WHITE);
        findPanel.setLayout(new BoxLayout(findPanel, BoxLayout.X_AXIS));
        findPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        findPanel.setMaximumSize(new Dimension(400, 40));

        JTextField searchField = new JTextField();
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        searchField.setBackground(new Color(255, 255, 255));
        searchField.setForeground(new Color(33, 33, 33));
        findPanel.add(searchField);

        findPanel.add(Box.createHorizontalStrut(10));

        ImageIcon searchIcon = new ImageIcon("src/main/java/iuh/icons/Find.png");
        JLabel searchLabel = new JLabel(new ImageIcon(searchIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH)));
        searchLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        searchLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        searchLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                String searchText = searchField.getText().trim().toLowerCase();
                controller.search(searchText);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                searchLabel.setOpaque(true);
                searchLabel.setBackground(new Color(230, 230, 230));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                searchLabel.setOpaque(false);
                searchLabel.setBackground(Color.WHITE);
            }
        });
        findPanel.add(searchLabel);

        toolsPanel.add(findPanel, BorderLayout.CENTER);

        // Nút bấm
        JPanel miniToolsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        miniToolsPanel.setBackground(new Color(240, 243, 246));

        JButton addButton = createStyledButton("Thêm mới", new Color(25, 118, 210));
        addButton.addActionListener(e -> {
            ThemChuyenTauForm themChuyenTauForm = new ThemChuyenTauForm(this);
            themChuyenTauForm.setVisible(true);
        });

        // Trong phương thức createToolsPanel của QuanLyChuyenTauScreen
        JButton editButton = createStyledButton("Sửa", new Color(0, 153, 0));
        editButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                String maChuyenTau = (String) tableModel.getValueAt(selectedRow, 0);
                SuaChuyenTauForm suaChuyenTauForm = new SuaChuyenTauForm(this, maChuyenTau);
                suaChuyenTauForm.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Hãy chọn một chuyến tàu để sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        });

        JButton deleteButton = createStyledButton("Xoá", new Color(239, 83, 80));
        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                XoaChuyenTauForm xoaChuyenTauForm = new XoaChuyenTauForm(this, controller, selectedRow);
                xoaChuyenTauForm.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Hãy chọn một chuyến tàu để xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        });

        miniToolsPanel.add(addButton);
        miniToolsPanel.add(editButton);
        miniToolsPanel.add(deleteButton);
        toolsPanel.add(miniToolsPanel, BorderLayout.SOUTH);

        return toolsPanel;
    }

    private JButton createStyledButton(String text, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(backgroundColor.brighter());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(backgroundColor);
            }
        });
        return button;
    }

    private JPanel createInforPanel() {
        JPanel inforPanel = new JPanel(new BorderLayout());
        inforPanel.setBackground(new Color(240, 243, 246));
        inforPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        table = new JTable(tableModel);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(35);
        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setShowGrid(true);
        table.setGridColor(new Color(220, 220, 220));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(25, 118, 210));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setBackground(Color.WHITE);

        // Điều chỉnh độ rộng cột
        table.getColumnModel().getColumn(0).setPreferredWidth(100); // Mã chuyến tàu
        table.getColumnModel().getColumn(1).setPreferredWidth(80);  // Mã tàu
        table.getColumnModel().getColumn(2).setPreferredWidth(150); // Giờ khởi hành
        table.getColumnModel().getColumn(3).setPreferredWidth(150); // Tuyến tàu
        table.getColumnModel().getColumn(4).setPreferredWidth(120); // Số lượng hành khách
        table.getColumnModel().getColumn(5).setPreferredWidth(120); // Số lượng HK tối đa

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
        scrollPane.setBackground(new Color(240, 243, 246));
        scrollPane.getViewport().setBackground(Color.WHITE);
        inforPanel.add(scrollPane, BorderLayout.CENTER);

        return inforPanel;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JPanel getPanel() {
        return panel;
    }

    public QuanLyChuyenTauController getController() {
        return controller;
    }
}
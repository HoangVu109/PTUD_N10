package iuh.gui.QuanLy;

import iuh.controller.QuanLyController.QuanLyChuyenTauController;
import iuh.form.ChuyenTau.SuaChuyenTauForm;
import iuh.form.ChuyenTau.ThemChuyenTauForm;
import iuh.form.ChuyenTau.XoaChuyenTauForm;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class QuanLyChuyenTauScreen {
    private final JPanel panel;
    private final JPanel toolsPanel;
    private final JPanel inforPanel;
    private final DefaultTableModel tableModel;
    private final QuanLyChuyenTauController controller;
    private JTable table;

    public QuanLyChuyenTauScreen() {
        tableModel = new DefaultTableModel(
                new String[]{"Mã chuyến tàu", "Mã tàu", "Giờ khởi hành", "Tuyến tàu", "Số lượng hành khách", "Số lượng HK tối đa"},
                0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        controller = new QuanLyChuyenTauController(this);
        panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240, 243, 246));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        toolsPanel = createToolsPanel();
        inforPanel = createInforPanel();

        panel.add(toolsPanel, BorderLayout.NORTH);
        panel.add(inforPanel, BorderLayout.CENTER);

        controller.loadInitialData();
    }

    private JPanel createToolsPanel() {
        JPanel toolsPanel = new JPanel(new BorderLayout());
        toolsPanel.setBackground(new Color(240, 243, 246));
        toolsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 15, 0));

        JPanel titlePanel = createTitlePanel();
        JPanel findPanel = createSearchPanel();
        JPanel miniToolsPanel = createButtonPanel();

        toolsPanel.add(titlePanel, BorderLayout.NORTH);
        toolsPanel.add(findPanel, BorderLayout.CENTER);
        toolsPanel.add(miniToolsPanel, BorderLayout.SOUTH);

        return toolsPanel;
    }

    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setBackground(new Color(240, 243, 246));
        JLabel titleLabel = new JLabel("Quản lý chuyến tàu");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(new Color(25, 118, 210));
        titlePanel.add(titleLabel);
        return titlePanel;
    }

    private JPanel createSearchPanel() {
        JPanel findPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
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
        searchField.setBackground(Color.WHITE);
        searchField.setForeground(new Color(33, 33, 33));

        JLabel searchLabel = createSearchIconLabel(searchField);

        findPanel.add(searchField);
        findPanel.add(Box.createHorizontalStrut(10));
        findPanel.add(searchLabel);

        return findPanel;
    }

    private JLabel createSearchIconLabel(JTextField searchField) {
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
        return searchLabel;
    }

    private JPanel createButtonPanel() {
        JPanel miniToolsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        miniToolsPanel.setBackground(new Color(240, 243, 246));

        JButton addButton = createStyledButton("Thêm mới", new Color(25, 118, 210));
        addButton.addActionListener(e -> new ThemChuyenTauForm(this).setVisible(true));

        JButton editButton = createStyledButton("Sửa", new Color(0, 153, 0));
        editButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                String maChuyenTau = (String) tableModel.getValueAt(selectedRow, 0);
                new SuaChuyenTauForm(this, maChuyenTau).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Hãy chọn một chuyến tàu để sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        });

        JButton deleteButton = createStyledButton("Xoá", new Color(239, 83, 80));
        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                new XoaChuyenTauForm(this, controller, selectedRow).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Hãy chọn một chuyến tàu để xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        });

        miniToolsPanel.add(addButton);
        miniToolsPanel.add(editButton);
        miniToolsPanel.add(deleteButton);
        return miniToolsPanel;
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
        configureTable();

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
        scrollPane.setBackground(new Color(240, 243, 246));
        scrollPane.getViewport().setBackground(Color.WHITE);

        inforPanel.add(scrollPane, BorderLayout.CENTER);
        return inforPanel;
    }

    private void configureTable() {
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

        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(80);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        table.getColumnModel().getColumn(3).setPreferredWidth(150);
        table.getColumnModel().getColumn(4).setPreferredWidth(120);
        table.getColumnModel().getColumn(5).setPreferredWidth(120);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
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
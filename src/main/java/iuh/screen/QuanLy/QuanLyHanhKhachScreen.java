package iuh.screen.QuanLy;

import iuh.controller.QuanLyCon.QuanLyHanhKhachController;
import iuh.form.QuanLyForm.SuaHanhKhachForm;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class QuanLyHanhKhachScreen {
    private final JPanel panel;
    private final JPanel toolsPanel;
    private final JPanel inforPanel;
    private final DefaultTableModel tableModel;
    private final QuanLyHanhKhachController controller;
    private JTable table;

    public QuanLyHanhKhachScreen() {
        tableModel = new DefaultTableModel(
                new String[]{"STT", "Số CCCD", "Họ và tên", "Số ĐT", "Địa chỉ"},
                0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        controller = new QuanLyHanhKhachController(this);
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
        JLabel titleLabel = new JLabel("Quản lý hành khách");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
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
        searchField.addActionListener(e -> {
            String searchText = searchField.getText().trim().toLowerCase();
            controller.search(searchText);
        });
        return searchLabel;
    }

    private JPanel createButtonPanel() {
        JPanel miniToolsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        miniToolsPanel.setBackground(new Color(240, 243, 246));

        JButton editButton = createStyledButton("Sửa", new Color(0, 153, 0));
        editButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                String soCCCD = (String) tableModel.getValueAt(selectedRow, 1);
                new SuaHanhKhachForm(this, soCCCD).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Hãy chọn một hành khách để sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        });

        miniToolsPanel.add(editButton);
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

        table.getColumnModel().getColumn(0).setPreferredWidth(50); // STT
        table.getColumnModel().getColumn(1).setPreferredWidth(120); // Số CCCD
        table.getColumnModel().getColumn(2).setPreferredWidth(150); // Họ và tên
        table.getColumnModel().getColumn(3).setPreferredWidth(100); // Số ĐT
        table.getColumnModel().getColumn(4).setPreferredWidth(200); // Địa chỉ

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

    public QuanLyHanhKhachController getController() {
        return controller;
    }
}
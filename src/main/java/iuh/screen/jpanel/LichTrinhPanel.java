package iuh.screen.jpanel;

import iuh.controller.QuanLyCon.LichTrinhController;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class LichTrinhPanel extends JPanel {
    private final DefaultTableModel tableModel;
    private final LichTrinhController controller;
    private JTable table;

    public LichTrinhPanel() {
        tableModel = new DefaultTableModel(
                new String[]{"STT", "Mã chuyến tàu", "Mã tàu", "Ga khởi hành", "Ga kết thúc", "Ngày và giờ"},
                0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        controller = new LichTrinhController(this);
        setLayout(new BorderLayout());
        setBackground(new Color(240, 243, 246));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel northPanel = createNorthPanel();
        JPanel centerPanel = createCenterPanel();

        add(northPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);

        controller.loadInitialData();
    }

    private JPanel createNorthPanel() {
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.setBackground(new Color(240, 243, 246));
        northPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 15, 0));

        JLabel titleLabel = new JLabel("Danh sách chuyến đang hoạt động");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(new Color(25, 118, 210));

        JPanel searchPanel = new JPanel() {
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
        searchPanel.setOpaque(false);
        searchPanel.setBackground(Color.WHITE);
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
        searchPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        searchPanel.setMaximumSize(new Dimension(400, 40));

        JTextField searchField = new JTextField(20);
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        searchField.setBackground(Color.WHITE);
        searchField.setForeground(new Color(33, 33, 33));

        JLabel searchLabel = createSearchIconLabel(searchField);

        searchPanel.add(searchField);
        searchPanel.add(Box.createHorizontalStrut(10));
        searchPanel.add(searchLabel);

        northPanel.add(titleLabel, BorderLayout.WEST);
        northPanel.add(searchPanel, BorderLayout.EAST);
        return northPanel;
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

    private JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(new Color(240, 243, 246));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        table = new JTable(tableModel);
        configureTable();

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
        scrollPane.setBackground(new Color(240, 243, 246));
        scrollPane.getViewport().setBackground(Color.WHITE);

        centerPanel.add(scrollPane, BorderLayout.CENTER);
        return centerPanel;
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

        // Adjust column widths
        table.getColumnModel().getColumn(0).setPreferredWidth(50); // STT
        table.getColumnModel().getColumn(1).setPreferredWidth(100); // Mã chuyến tàu
        table.getColumnModel().getColumn(2).setPreferredWidth(80); // Mã tàu
        table.getColumnModel().getColumn(3).setPreferredWidth(150); // Ga khởi hành
        table.getColumnModel().getColumn(4).setPreferredWidth(150); // Ga kết thúc
        table.getColumnModel().getColumn(5).setPreferredWidth(150); // Ngày và giờ

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
}
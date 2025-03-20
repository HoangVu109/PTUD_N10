package iuh.gui;

import iuh.connect.DatabaseConnection;
import iuh.controller.QuanLyChuyenTauController;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.RoundRectangle2D;

public class QuanLyChuyenTauScreen {
    private JPanel panel;
    private JPanel toolsPanel;
    private JPanel inforPanel;
    private JTable table;
    private DefaultTableModel tableModel;
    private QuanLyChuyenTauController controller;

    public QuanLyChuyenTauScreen() {
        String[] columnNames = {
                "Mã chuyến tàu", "Mã tàu", "Giờ khởi hành", "Số lượng toa", "Tuyến tàu", "Số lượng hành khách"
        };
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        controller = new QuanLyChuyenTauController(this);

        panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(245, 248, 250));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        toolsPanel = createToolsPanel();
        panel.add(toolsPanel, BorderLayout.NORTH);

        inforPanel = createInforPanel();
        panel.add(inforPanel, BorderLayout.CENTER);

        controller.loadInitialData();
    }

    private JPanel createToolsPanel() {
        JPanel toolsPanel = new JPanel(new BorderLayout());
        toolsPanel.setBackground(new Color(245, 248, 250));
        toolsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setBackground(new Color(245, 248, 250));
        JLabel titleLabel = new JLabel("Quản lý chuyến tàu");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(new Color(30, 144, 255));
        titlePanel.add(titleLabel);
        toolsPanel.add(titlePanel, BorderLayout.NORTH);

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

        JTextField searchField = new JTextField();
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        searchField.setBackground(new Color(245, 245, 245));
        findPanel.add(searchField);

        findPanel.add(Box.createHorizontalStrut(10));

        ImageIcon searchIcon = new ImageIcon("main/java/iuh/icons/Find.png");
        JLabel searchLabel = new JLabel(new ImageIcon(searchIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH)));
        searchLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        searchLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                String searchText = searchField.getText().trim().toLowerCase();
                controller.search(searchText);
            }
        });
        findPanel.add(searchLabel);

        toolsPanel.add(findPanel, BorderLayout.CENTER);

        JPanel miniToolsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        miniToolsPanel.setBackground(new Color(245, 248, 250));

        JButton addButton = new JButton("Thêm mới");
        addButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        addButton.setBackground(new Color(0, 120, 215));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.setPreferredSize(new Dimension(100, 30));
        addButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Chức năng Thêm mới sẽ được triển khai!"));

        JButton editButton = new JButton("Sửa");
        editButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        editButton.setBackground(new Color(0, 153, 0));
        editButton.setForeground(Color.WHITE);
        editButton.setFocusPainted(false);
        editButton.setPreferredSize(new Dimension(100, 30));
        editButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Chức năng Sửa sẽ được triển khai!"));

        JButton deleteButton = new JButton("Xoá");
        deleteButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        deleteButton.setBackground(new Color(255, 102, 102));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFocusPainted(false);
        deleteButton.setPreferredSize(new Dimension(100, 30));
        deleteButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Chức năng Xoá sẽ được triển khai!"));

        miniToolsPanel.add(addButton);
        miniToolsPanel.add(editButton);
        miniToolsPanel.add(deleteButton);
        toolsPanel.add(miniToolsPanel, BorderLayout.SOUTH);

        return toolsPanel;
    }

    private JPanel createInforPanel() {
        JPanel inforPanel = new JPanel(new BorderLayout());
        inforPanel.setBackground(new Color(245, 248, 250));
        inforPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

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

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        scrollPane.setBackground(new Color(245, 248, 250));
        scrollPane.getViewport().setBackground(Color.WHITE);
        inforPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel paginationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        paginationPanel.setBackground(new Color(245, 248, 250));

        JButton prevButton = new JButton("<<");
        prevButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        prevButton.setBackground(Color.WHITE);
        prevButton.setForeground(new Color(30, 144, 255));
        prevButton.setFocusPainted(false);
        prevButton.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        prevButton.setPreferredSize(new Dimension(40, 40));

        JButton page1Button = new JButton("1");
        page1Button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        page1Button.setBackground(Color.WHITE);
        page1Button.setForeground(new Color(30, 144, 255));
        page1Button.setFocusPainted(false);
        page1Button.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        page1Button.setPreferredSize(new Dimension(40, 40));

        JButton page2Button = new JButton("2");
        page2Button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        page2Button.setBackground(Color.WHITE);
        page2Button.setForeground(new Color(30, 144, 255));
        page2Button.setFocusPainted(false);
        page2Button.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        page2Button.setPreferredSize(new Dimension(40, 40));

        JButton page3Button = new JButton("3");
        page3Button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        page3Button.setBackground(Color.WHITE);
        page3Button.setForeground(new Color(30, 144, 255));
        page3Button.setFocusPainted(false);
        page3Button.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        page3Button.setPreferredSize(new Dimension(40, 40));

        JButton nextButton = new JButton(">>");
        nextButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        nextButton.setBackground(Color.WHITE);
        nextButton.setForeground(new Color(30, 144, 255));
        nextButton.setFocusPainted(false);
        nextButton.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        nextButton.setPreferredSize(new Dimension(40, 40));

        paginationPanel.add(prevButton);
        paginationPanel.add(page1Button);
        paginationPanel.add(page2Button);
        paginationPanel.add(page3Button);
        paginationPanel.add(nextButton);
        inforPanel.add(paginationPanel, BorderLayout.SOUTH);

        return inforPanel;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JPanel getPanel() {
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Khởi tạo kết nối khi ứng dụng bắt đầu
            DatabaseConnection.initializeConnection();

            JFrame frame = new JFrame("Quản Lý Chuyến Tàu");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(900, 600);
            frame.add(new QuanLyChuyenTauScreen().getPanel());
            frame.setLocationRelativeTo(null);

            // Đóng kết nối khi cửa sổ chính đóng
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    DatabaseConnection.closeConnection();
                }
            });

            frame.setVisible(true);
        });
    }
}
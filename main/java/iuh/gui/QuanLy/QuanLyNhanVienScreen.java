package iuh.gui.QuanLy;

import iuh.controller.QuanLyController.NhanVienController;
import iuh.form.NhanVien.XoaNhanVienForm;
import iuh.form.NhanVien.SuaNhanVienForm;
import iuh.form.NhanVien.ThemNhanVienForm;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class QuanLyNhanVienScreen {
    private JPanel panel;
    private JTable table;
    private JTextField maNVField, hoTenField, cccdField, ngaySinhField, diaChiField, tinhTrangField;
    private JComboBox<String> chucVuComboBox, gioiTinhComboBox;
    private DefaultTableModel tableModel;
    private NhanVienController controller;

    public QuanLyNhanVienScreen() {
        String[] columnNames = {"Mã nhân viên", "Họ tên", "Chức vụ", "Giới tính"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        controller = new NhanVienController(this);

        panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(245, 248, 250));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel findPanel = createFindPanel();
        panel.add(findPanel, BorderLayout.NORTH);

        JPanel inforPanel = createInforPanel();
        panel.add(inforPanel, BorderLayout.CENTER);

        JPanel toolsPanel = createToolsPanel();
        panel.add(toolsPanel, BorderLayout.SOUTH);

        controller.loadInitialData();
    }

    private JPanel createRightPanel() {
        JPanel rightPanel = new JPanel() {
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
        rightPanel.setOpaque(false);
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titleLabel = new JLabel("Thông tin nhân viên");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(new Color(30, 144, 255));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 15, 0));
        rightPanel.add(titleLabel);

        JPanel employeePanel = new JPanel();
        employeePanel.setLayout(new BoxLayout(employeePanel, BoxLayout.Y_AXIS));
        employeePanel.setBackground(Color.WHITE);
        employeePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Các panel hiện có giữ nguyên...
        JPanel maNVPanel = new JPanel();
        maNVPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        maNVPanel.setBackground(Color.WHITE);
        JLabel maNVLabel = new JLabel("Mã nhân viên:");
        maNVLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        maNVLabel.setForeground(new Color(80, 80, 80));
        maNVLabel.setPreferredSize(new Dimension(100, 30));
        maNVField = new JTextField(15);
        maNVField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        maNVField.setPreferredSize(new Dimension(200, 30));
        maNVField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
        maNVField.setEditable(false);
        maNVPanel.add(maNVLabel);
        maNVPanel.add(maNVField);
        employeePanel.add(maNVPanel);
        employeePanel.add(Box.createVerticalStrut(10));

        JPanel hoTenPanel = new JPanel();
        hoTenPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        hoTenPanel.setBackground(Color.WHITE);
        JLabel hoTenLabel = new JLabel("Họ và tên:");
        hoTenLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        hoTenLabel.setForeground(new Color(80, 80, 80));
        hoTenLabel.setPreferredSize(new Dimension(100, 30));
        hoTenField = new JTextField(15);
        hoTenField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        hoTenField.setPreferredSize(new Dimension(200, 30));
        hoTenField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
        hoTenField.setEditable(false);
        hoTenPanel.add(hoTenLabel);
        hoTenPanel.add(hoTenField);
        employeePanel.add(hoTenPanel);
        employeePanel.add(Box.createVerticalStrut(10));

        JPanel chucVuPanel = new JPanel();
        chucVuPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        chucVuPanel.setBackground(Color.WHITE);
        JLabel chucVuLabel = new JLabel("Chức vụ:");
        chucVuLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        chucVuLabel.setForeground(new Color(80, 80, 80));
        chucVuLabel.setPreferredSize(new Dimension(100, 30));
        chucVuComboBox = new JComboBox<>(new String[]{"Quản lý", "Nhân viên"});
        chucVuComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        chucVuComboBox.setPreferredSize(new Dimension(200, 30));
        chucVuComboBox.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
        chucVuComboBox.setEnabled(false);
        chucVuPanel.add(chucVuLabel);
        chucVuPanel.add(chucVuComboBox);
        employeePanel.add(chucVuPanel);
        employeePanel.add(Box.createVerticalStrut(10));

        JPanel gioiTinhPanel = new JPanel();
        gioiTinhPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        gioiTinhPanel.setBackground(Color.WHITE);
        JLabel gioiTinhLabel = new JLabel("Giới tính:");
        gioiTinhLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gioiTinhLabel.setForeground(new Color(80, 80, 80));
        gioiTinhLabel.setPreferredSize(new Dimension(100, 30));
        gioiTinhComboBox = new JComboBox<>(new String[]{"Nam", "Nữ"});
        gioiTinhComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gioiTinhComboBox.setPreferredSize(new Dimension(200, 30));
        gioiTinhComboBox.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
        gioiTinhComboBox.setEnabled(false);
        gioiTinhPanel.add(gioiTinhLabel);
        gioiTinhPanel.add(gioiTinhComboBox);
        employeePanel.add(gioiTinhPanel);
        employeePanel.add(Box.createVerticalStrut(10));

        JPanel cccdPanel = new JPanel();
        cccdPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        cccdPanel.setBackground(Color.WHITE);
        JLabel cccdLabel = new JLabel("Số CCCD:");
        cccdLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cccdLabel.setForeground(new Color(80, 80, 80));
        cccdLabel.setPreferredSize(new Dimension(100, 30));
        cccdField = new JTextField(15);
        cccdField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cccdField.setPreferredSize(new Dimension(200, 30));
        cccdField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
        cccdField.setEditable(false);
        cccdPanel.add(cccdLabel);
        cccdPanel.add(cccdField);
        employeePanel.add(cccdPanel);
        employeePanel.add(Box.createVerticalStrut(10));

        JPanel ngaySinhPanel = new JPanel();
        ngaySinhPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        ngaySinhPanel.setBackground(Color.WHITE);
        JLabel ngaySinhLabel = new JLabel("Ngày sinh:");
        ngaySinhLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        ngaySinhLabel.setForeground(new Color(80, 80, 80));
        ngaySinhLabel.setPreferredSize(new Dimension(100, 30));
        ngaySinhField = new JTextField(15);
        ngaySinhField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        ngaySinhField.setPreferredSize(new Dimension(200, 30));
        ngaySinhField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
        ngaySinhField.setEditable(false);
        ngaySinhPanel.add(ngaySinhLabel);
        ngaySinhPanel.add(ngaySinhField);
        employeePanel.add(ngaySinhPanel);
        employeePanel.add(Box.createVerticalStrut(10));

        JPanel diaChiPanel = new JPanel();
        diaChiPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        diaChiPanel.setBackground(Color.WHITE);
        JLabel diaChiLabel = new JLabel("Địa chỉ:");
        diaChiLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        diaChiLabel.setForeground(new Color(80, 80, 80));
        diaChiLabel.setPreferredSize(new Dimension(100, 30));
        diaChiField = new JTextField(15);
        diaChiField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        diaChiField.setPreferredSize(new Dimension(200, 30));
        diaChiField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
        diaChiField.setEditable(false);
        diaChiPanel.add(diaChiLabel);
        diaChiPanel.add(diaChiField);
        employeePanel.add(diaChiPanel);
        employeePanel.add(Box.createVerticalStrut(10));

        // Thêm panel Tình trạng
        JPanel tinhTrangPanel = new JPanel();
        tinhTrangPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        tinhTrangPanel.setBackground(Color.WHITE);
        JLabel tinhTrangLabel = new JLabel("Tình trạng:");
        tinhTrangLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tinhTrangLabel.setForeground(new Color(80, 80, 80));
        tinhTrangLabel.setPreferredSize(new Dimension(100, 30));
        tinhTrangField = new JTextField(15);
        tinhTrangField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tinhTrangField.setPreferredSize(new Dimension(200, 30));
        tinhTrangField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
        tinhTrangField.setEditable(false);
        tinhTrangPanel.add(tinhTrangLabel);
        tinhTrangPanel.add(tinhTrangField);
        employeePanel.add(tinhTrangPanel);

        rightPanel.add(employeePanel);

        return rightPanel;
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

        JTextField searchField = new JTextField(20);
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        searchField.setBackground(new Color(245, 245, 245));
        findPanel.add(searchField);

        findPanel.add(Box.createHorizontalStrut(10));

        ImageIcon searchIcon = new ImageIcon("src/main/java/iuh/icons/Find.png");
        JLabel searchLabel = new JLabel(new ImageIcon(searchIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH)));
        searchLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        searchLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                String searchText = searchField.getText().trim().toLowerCase();
                controller.searchEmployee(searchText);
            }
        });
        //Them phim tat Enter
        searchField.addActionListener(e -> {
            String searchText = searchField.getText().trim().toLowerCase();
            controller.searchEmployee(searchText);
        });

        findPanel.add(searchLabel);

        return findPanel;
    }

    private JPanel createInforPanel() {
        JPanel inforPanel = new JPanel();
        inforPanel.setLayout(new BoxLayout(inforPanel, BoxLayout.X_AXIS));
        inforPanel.setBackground(new Color(245, 248, 250));
        inforPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));

        JPanel leftPanel = createLeftPanel();
        leftPanel.setPreferredSize(new Dimension(800, 0));
        inforPanel.add(leftPanel);
        inforPanel.add(Box.createHorizontalStrut(15));

        JPanel rightPanel = createRightPanel();
        rightPanel.setPreferredSize(new Dimension(450, 0));
        inforPanel.add(rightPanel);

        return inforPanel;
    }

    private JPanel createLeftPanel() {
        JPanel leftPanel = new JPanel(new BorderLayout()) {
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
        leftPanel.setOpaque(false);
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titleLabel = new JLabel("Danh sách nhân viên", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(new Color(30, 144, 255));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 15, 0));
        leftPanel.add(titleLabel, BorderLayout.NORTH);

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

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    controller.updateEmployeeInfo(selectedRow);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        leftPanel.add(scrollPane, BorderLayout.CENTER);

        return leftPanel;
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
        addButton.addActionListener(e -> new ThemNhanVienForm(this).setVisible(true));
        toolsPanel.add(addButton);

        JButton editButton = new JButton("Sửa");
        editButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        editButton.setBackground(new Color(0, 153, 0));
        editButton.setForeground(Color.WHITE);
        editButton.setFocusPainted(false);
        editButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(panel, "Vui lòng chọn nhân viên để sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int originalIndex = controller.getDanhSachGoc().get(selectedRow);
            String[] employee = controller.getFullData()[originalIndex];
            new SuaNhanVienForm(this, employee[0], employee[1],
                    employee[2].equals("0") ? "Quản lý" : "Nhân viên",
                    employee[3].equals("0") ? "Nam" : "Nữ",
                    employee[4], employee[7], employee[5], employee[6], employee[8]).setVisible(true);
        });
        toolsPanel.add(editButton);

        JButton deleteButton = new JButton("Xoá");
        deleteButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        deleteButton.setBackground(new Color(255, 102, 102));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFocusPainted(false);
        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            new XoaNhanVienForm(this, controller, selectedRow);
        });
        toolsPanel.add(deleteButton);

        return toolsPanel;
    }

    public void updateEmployeePanel(String maNV, String hoTen, String chucVu, String gioiTinh, String cccd, String ngaySinh, String diaChi,boolean daNghiViec) {
        maNVField.setText(maNV);
        hoTenField.setText(hoTen);
        chucVuComboBox.setSelectedItem(chucVu);
        gioiTinhComboBox.setSelectedItem(gioiTinh);
        cccdField.setText(cccd);
        ngaySinhField.setText(ngaySinh);
        diaChiField.setText(diaChi);
        tinhTrangField.setText(daNghiViec ? "Đã nghỉ việc" : "Đang làm việc");
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JPanel getPanel() {
        return panel;
    }

    public NhanVienController getController() {
        return controller;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Quản Lý Nhân Viên");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(900, 600);
            frame.add(new QuanLyNhanVienScreen().getPanel());
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
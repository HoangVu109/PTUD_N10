package iuh.gui.NhanVien;

import com.toedter.calendar.JDateChooser;
import iuh.controller.BanVeController;
import iuh.form.NhanVienForm.NhapThongTinKhachHangForm;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class BanVeScreen extends JPanel {
    private JComboBox<String> tuyenTauComboBox;
    private JDateChooser ngayDiChooser;
    private JList<String> chuyenTauList;
    private JTable danhSachChoDatTable, gioVeTable;
    private DefaultTableModel danhSachChoDatModel, gioVeModel;
    private JPanel viTriPanel;
    private BanVeController controller;

    public BanVeScreen() {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 248, 250));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Phần MainInforPanel (phía trên)
        JPanel mainInforPanel = createMainInforPanel();
        add(mainInforPanel, BorderLayout.NORTH);

        // Phần ListPanel (phía dưới)
        JPanel listPanel = createListPanel();
        add(listPanel, BorderLayout.CENTER);

        // Khởi tạo controller
        controller = new BanVeController(this);
    }

    private JPanel createMainInforPanel() {
        JPanel mainInforPanel = new JPanel();
        mainInforPanel.setLayout(new BoxLayout(mainInforPanel, BoxLayout.Y_AXIS));
        mainInforPanel.setBackground(new Color(245, 248, 250));
        mainInforPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Tiêu đề "Bán Vé - Ga Sài Gòn"
        JLabel gaSaiGonTitle = new JLabel("Bán Vé - Ga Sài Gòn", SwingConstants.CENTER);
        gaSaiGonTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        gaSaiGonTitle.setForeground(new Color(30, 144, 255));
        mainInforPanel.add(gaSaiGonTitle);
        mainInforPanel.add(Box.createVerticalStrut(5));

        // Tiêu đề phụ "Hệ thống bán vé của Ga Sài Gòn"
        JLabel smallTitle = new JLabel("Hệ thống bán vé của Ga Sài Gòn", SwingConstants.CENTER);
        smallTitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        smallTitle.setForeground(new Color(80, 80, 80));
        mainInforPanel.add(smallTitle);
        mainInforPanel.add(Box.createVerticalStrut(20));

        // ToolsPanel chứa Tuyến Tàu, Ngày đi, và nút Hủy (nằm cùng hàng)
        JPanel toolsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)) {
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
        toolsPanel.setOpaque(false);
        toolsPanel.setBackground(Color.WHITE);
        toolsPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        // Tuyến Tàu (JComboBox)
        JLabel tuyenTauLabel = new JLabel("Tuyến Tàu:");
        tuyenTauLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tuyenTauLabel.setForeground(new Color(80, 80, 80));
        tuyenTauLabel.setPreferredSize(new Dimension(80, 30));
        tuyenTauComboBox = new JComboBox<>();
        tuyenTauComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tuyenTauComboBox.setPreferredSize(new Dimension(150, 30));
        toolsPanel.add(tuyenTauLabel);
        toolsPanel.add(tuyenTauComboBox);

        // Ngày đi (JDateChooser)
        JLabel ngayDiLabel = new JLabel("Ngày đi:");
        ngayDiLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        ngayDiLabel.setForeground(new Color(80, 80, 80));
        ngayDiLabel.setPreferredSize(new Dimension(80, 30));
        ngayDiChooser = new JDateChooser();
        ngayDiChooser.setDateFormatString("dd/MM/yyyy");
        ngayDiChooser.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        ngayDiChooser.setPreferredSize(new Dimension(150, 30));
        toolsPanel.add(ngayDiLabel);
        toolsPanel.add(ngayDiChooser);

        // Nút Hủy
        JButton huyButton = new JButton("Hủy");
        huyButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        huyButton.setBackground(new Color(255, 102, 102));
        huyButton.setForeground(Color.WHITE);
        huyButton.setFocusPainted(false);
        huyButton.addActionListener(e -> resetTuyenTauVaNgayGio()); // Thêm sự kiện cho nút Hủy
        toolsPanel.add(huyButton);

        mainInforPanel.add(toolsPanel);
        return mainInforPanel;
    }

    private JPanel createListPanel() {
        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.setBackground(new Color(245, 248, 250));
        listPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));

        // Phần DanhSachChuyenTauPanel (bên trái trên)
        JPanel danhSachChuyenTauPanel = createDanhSachChuyenTauPanel();
        danhSachChuyenTauPanel.setPreferredSize(new Dimension(0, 150));
        listPanel.add(danhSachChuyenTauPanel, BorderLayout.NORTH);

        // Phần TrainDetailsPanel và bảng bên phải
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(new Color(245, 248, 250));

        // TrainDetailsPanel (bên trái dưới) - 60%
        JPanel trainDetailsPanel = createTrainDetailsPanel();
        trainDetailsPanel.setPreferredSize(new Dimension(900, 0));
        centerPanel.add(trainDetailsPanel, BorderLayout.CENTER);

        // Bảng Danh sách chỗ đặt và Giỏ vé (bên phải) - 40%
        JPanel rightPanel = createRightPanel();
        rightPanel.setPreferredSize(new Dimension(600, 0));
        centerPanel.add(rightPanel, BorderLayout.EAST);

        listPanel.add(centerPanel, BorderLayout.CENTER);
        return listPanel;
    }

    private JPanel createDanhSachChuyenTauPanel() {
        JPanel danhSachChuyenTauPanel = new JPanel(new BorderLayout()) {
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
        danhSachChuyenTauPanel.setOpaque(false);
        danhSachChuyenTauPanel.setBackground(Color.WHITE);
        danhSachChuyenTauPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Tiêu đề "Danh sách chuyến tàu"
        JLabel danhSachChuyenTitle = new JLabel("Danh sách chuyến tàu");
        danhSachChuyenTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        danhSachChuyenTitle.setForeground(new Color(30, 144, 255));
        danhSachChuyenTitle.setBorder(BorderFactory.createEmptyBorder(5, 0, 15, 0));
        danhSachChuyenTauPanel.add(danhSachChuyenTitle, BorderLayout.NORTH);

        // Vẽ đường thẳng màu đen phía dưới tiêu đề
        JPanel linePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), 1); // Vẽ đường thẳng ngang
            }
        };
        linePanel.setPreferredSize(new Dimension(0, 10));
        linePanel.setBackground(Color.WHITE);
        danhSachChuyenTauPanel.add(linePanel, BorderLayout.CENTER);

        chuyenTauList = new JList<>();
        chuyenTauList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        chuyenTauList.setVisibleRowCount(1);
        chuyenTauList.setCellRenderer(new createTrainIcon());
        chuyenTauList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        chuyenTauList.setSelectionBackground(Color.WHITE); // Remove selection background
        chuyenTauList.setSelectionForeground(Color.BLACK); // Keep text color normal when selected
        JScrollPane scrollPane = new JScrollPane(chuyenTauList);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        danhSachChuyenTauPanel.add(scrollPane, BorderLayout.SOUTH);

        return danhSachChuyenTauPanel;
    }

    private JPanel createTrainDetailsPanel() {
        JPanel trainDetailsPanel = new JPanel(new BorderLayout()) {
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
        trainDetailsPanel.setOpaque(false);
        trainDetailsPanel.setBackground(Color.WHITE);
        trainDetailsPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Thông tin toa (inforToa) - Căn giữa
        JLabel inforToa = new JLabel("Toa 6: Ghế mềm điều hòa", SwingConstants.CENTER);
        inforToa.setFont(new Font("Segoe UI", Font.BOLD, 18));
        inforToa.setForeground(new Color(30, 144, 255));
        inforToa.setBorder(BorderFactory.createEmptyBorder(5, 0, 15, 0));
        trainDetailsPanel.add(inforToa, BorderLayout.NORTH);

        // Danh sách toa mini (dùng JPanel với các JLabel) - Căn giữa
        JPanel toaMiniPanel = new JPanel();
        toaMiniPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        toaMiniPanel.setBackground(Color.WHITE);
        for (int i = 1; i <= 5; i++) {
            JLabel toaLabel = new JLabel("Toa " + i, new ImageIcon("src/main/java/iuh/icons/train.png"), JLabel.CENTER);
            toaLabel.setVerticalTextPosition(JLabel.BOTTOM);
            toaLabel.setHorizontalTextPosition(JLabel.CENTER);
            toaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            toaLabel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
            toaLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            toaLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    toaLabel.setBorder(BorderFactory.createLineBorder(new Color(30, 144, 255), 2));
                }
            });
            toaMiniPanel.add(toaLabel);
        }
        trainDetailsPanel.add(toaMiniPanel, BorderLayout.CENTER);

        // Danh sách vị trí ngồi
        viTriPanel = new JPanel(new GridLayout(5, 13, 5, 5));
        viTriPanel.setBackground(Color.WHITE);
        viTriPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        for (int i = 1; i <= 65; i++) {
            JButton seatButton = new JButton(String.valueOf(i));
            seatButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            seatButton.setBackground(new Color(30, 144, 255));
            seatButton.setForeground(Color.WHITE);
            seatButton.setFocusPainted(false);
            viTriPanel.add(seatButton);
        }
        JScrollPane viTriScrollPane = new JScrollPane(viTriPanel);
        viTriScrollPane.setBorder(BorderFactory.createEmptyBorder());
        viTriScrollPane.setPreferredSize(new Dimension(0, 200));
        trainDetailsPanel.add(viTriScrollPane, BorderLayout.SOUTH);

        return trainDetailsPanel;
    }

    private JPanel createRightPanel() {
        JPanel rightPanel = new JPanel(new BorderLayout()) {
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
        rightPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Phần trên: Danh sách chỗ đặt
        JPanel danhSachChoDatPanel = new JPanel(new BorderLayout());
        danhSachChoDatPanel.setBackground(Color.WHITE);

        JLabel danhSachChoDatTitle = new JLabel("Danh sách chỗ đặt", SwingConstants.CENTER);
        danhSachChoDatTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        danhSachChoDatTitle.setForeground(new Color(30, 144, 255));
        danhSachChoDatTitle.setBorder(BorderFactory.createEmptyBorder(5, 0, 15, 0));
        danhSachChoDatPanel.add(danhSachChoDatTitle, BorderLayout.NORTH);

        String[] danhSachChoDatColumns = {"Toa", "Số chỗ đã đặt", "Số chỗ trống"};
        danhSachChoDatModel = new DefaultTableModel(danhSachChoDatColumns, 0);
        danhSachChoDatTable = new JTable(danhSachChoDatModel);
        danhSachChoDatTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        danhSachChoDatTable.setRowHeight(30);
        danhSachChoDatTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        danhSachChoDatTable.getTableHeader().setBackground(new Color(30, 144, 255));
        danhSachChoDatTable.getTableHeader().setForeground(Color.WHITE);
        JScrollPane danhSachChoDatScrollPane = new JScrollPane(danhSachChoDatTable);
        danhSachChoDatScrollPane.setBorder(BorderFactory.createEmptyBorder());
        danhSachChoDatPanel.add(danhSachChoDatScrollPane, BorderLayout.CENTER);

        // Phần dưới: Giỏ vé
        JPanel gioVePanel = new JPanel(new BorderLayout());
        gioVePanel.setBackground(Color.WHITE);

        JLabel gioVeTitle = new JLabel("Giỏ vé", SwingConstants.CENTER);
        gioVeTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        gioVeTitle.setForeground(new Color(30, 144, 255));
        gioVeTitle.setBorder(BorderFactory.createEmptyBorder(5, 0, 15, 0));
        gioVePanel.add(gioVeTitle, BorderLayout.NORTH);

        String[] gioVeColumns = {"Tàu", "Toa Tàu", "Vị trí ghế", "Tuyến tàu", "Ngày và giờ", "Giá vé"};
        gioVeModel = new DefaultTableModel(gioVeColumns, 0);
        gioVeTable = new JTable(gioVeModel);
        gioVeTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gioVeTable.setRowHeight(30);
        gioVeTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        gioVeTable.getTableHeader().setBackground(new Color(30, 144, 255));
        gioVeTable.getTableHeader().setForeground(Color.WHITE);

        gioVeTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        gioVeTable.getColumnModel().getColumn(1).setPreferredWidth(60);
        gioVeTable.getColumnModel().getColumn(2).setPreferredWidth(60);
        gioVeTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        gioVeTable.getColumnModel().getColumn(4).setPreferredWidth(100);
        gioVeTable.getColumnModel().getColumn(5).setPreferredWidth(60);

        JScrollPane gioVeScrollPane = new JScrollPane(gioVeTable);
        gioVeScrollPane.setBorder(BorderFactory.createEmptyBorder());
        gioVePanel.add(gioVeScrollPane, BorderLayout.CENTER);

        // Nút "Mua vé" và "Hủy vé"
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.WHITE);
        JButton muaVeButton = new JButton("Mua vé");
        muaVeButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        muaVeButton.setBackground(new Color(0, 120, 215));
        muaVeButton.setForeground(Color.WHITE);
        muaVeButton.setFocusPainted(false);
        //add mua ve khi click nhay vao nhapThongTinKhachHangForm
        muaVeButton.addActionListener(e -> {
            NhapThongTinKhachHangForm nhapThongTinKhachHangForm = new NhapThongTinKhachHangForm();
            nhapThongTinKhachHangForm.setVisible(true);
        });
        buttonPanel.add(muaVeButton);

        JButton huyVeButton = new JButton("Hủy vé");
        huyVeButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        huyVeButton.setBackground(new Color(255, 102, 102));
        huyVeButton.setForeground(Color.WHITE);
        huyVeButton.setFocusPainted(false);
        buttonPanel.add(huyVeButton);
        gioVePanel.add(buttonPanel, BorderLayout.SOUTH);

        rightPanel.add(danhSachChoDatPanel, BorderLayout.CENTER);
        rightPanel.add(gioVePanel, BorderLayout.SOUTH);

        danhSachChoDatPanel.setPreferredSize(new Dimension(600, 200));
        gioVePanel.setPreferredSize(new Dimension(600, 200));

        return rightPanel;
    }

    // Renderer tùy chỉnh để hiển thị icon tàu trong JList
    // Renderer tùy chỉnh để hiển thị icon tàu với mã chuyến tàu bên dưới
    private class createTrainIcon extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel maTauIcon = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            // Đặt icon tàu
            maTauIcon.setIcon(new ImageIcon("src/main/java/iuh/icons/quanlychuyentau.png"));

            // Đặt mã chuyến tàu bên dưới icon
            maTauIcon.setText(value.toString());
            maTauIcon.setHorizontalTextPosition(JLabel.CENTER);
            maTauIcon.setVerticalTextPosition(JLabel.BOTTOM);   // Đặt text bên dưới icon
            maTauIcon.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            maTauIcon.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));


            return maTauIcon;
        }
    }

    // Getter để sử dụng trong controller
    public JComboBox<String> getTuyenTauComboBox() {
        return tuyenTauComboBox;
    }

    public JDateChooser getNgayDiChooser() {
        return ngayDiChooser;
    }

    public JList<String> getChuyenTauList() {
        return chuyenTauList;
    }

    public DefaultTableModel getDanhSachChoDatModel() {
        return danhSachChoDatModel;
    }

    public DefaultTableModel getGioVeModel() {
        return gioVeModel;
    }

    public JPanel getViTriPanel() {
        return viTriPanel;
    }
    // Reset tuyến tàu, ngày giờ và danh sách chuyến tàu
    public void resetTuyenTauVaNgayGio() {
        tuyenTauComboBox.setSelectedIndex(0);
        ngayDiChooser.setDate(null);
        chuyenTauList.setModel(new DefaultListModel<>());
    }
    public JPanel getPanel() {
        return this;
    }

    // Main để chạy thử
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Bán Vé - Ga Sài Gòn");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1500, 800);
            frame.add(new BanVeScreen());
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
//package iuh.controller;
//
//import iuh.dao.NhanVienDAO.BanVeDAO;
//import iuh.gui.NhanVien.BanVeScreen;
//import com.toedter.calendar.JDateChooser;
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.SQLException;
//import java.util.Date;
//import java.util.List;
//
//public class BanVeController {
//    private BanVeScreen banVeScreen;
//    private BanVeDAO BanVeDAO;
//
//    public BanVeController(BanVeScreen banVeScreen) {
//        this.banVeScreen = banVeScreen;
//        this.BanVeDAO = new BanVeDAO();
//        khoiTaoController();
//    }
//
//    private void khoiTaoController() {
//        // Load danh sách tuyến tàu vào ComboBox
//        loadDanhSachTuyenTau();
//
//        // Thêm sự kiện cho tuyenTauComboBox
//        banVeScreen.getTuyenTauComboBox().addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                capNhatDanhSachChuyenTau();
//            }
//        });
//
//        // Thêm sự kiện cho ngayDiChooser
//        banVeScreen.getNgayDiChooser().getDateEditor().addPropertyChangeListener("date", evt -> {
//            capNhatDanhSachChuyenTau();
//        });
//    }
//
//    // Load danh sách tuyến tàu vào ComboBox
//    private void loadDanhSachTuyenTau() {
//        try {
//            List<TuyenTau> danhSachTuyenTau = BanVeDAO.layDanhSachTuyenTau();
//            JComboBox<String> tuyenTauComboBox = banVeScreen.getTuyenTauComboBox();
//            tuyenTauComboBox.removeAllItems();
//            tuyenTauComboBox.addItem("Chọn tuyến tàu");
//
//            for (TuyenTau tuyenTau : danhSachTuyenTau) {
//                tuyenTauComboBox.addItem(tuyenTau.getGaKhoiHanh() + " - " + tuyenTau.getGaKetThuc());
//            }
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Lỗi khi tải danh sách tuyến tàu: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//
//    // Cập nhật danh sách chuyến tàu dựa trên tuyến tàu và ngày đi
//    private void capNhatDanhSachChuyenTau() {
//        JComboBox<String> tuyenTauComboBox = banVeScreen.getTuyenTauComboBox();
//        JDateChooser ngayDiChooser = banVeScreen.getNgayDiChooser();
//        DefaultListModel<String> chuyenTauModel = new DefaultListModel<>();
//
//        try {
//            // Lấy mã tuyến tàu từ lựa chọn
//            int selectedIndex = tuyenTauComboBox.getSelectedIndex();
//            if (selectedIndex <= 0) {
//                banVeScreen.getChuyenTauList().setModel(chuyenTauModel); // Xóa danh sách nếu không chọn tuyến
//                return;
//            }
//
//            List<TuyenTau> danhSachTuyenTau = BanVeDAO.layDanhSachTuyenTau();
//            String maTuyenTau = danhSachTuyenTau.get(selectedIndex - 1).getMaTuyenTau();
//
//            // Lấy ngày đi
//            Date ngayDi = ngayDiChooser.getDate();
//
//            // Lấy danh sách chuyến tàu
//            List<ChuyenTau> danhSachChuyenTau = BanVeDAO.layDanhSachChuyenTauTheoTuyen(maTuyenTau, ngayDi);
//
//            // Chỉ thêm mã chuyến tàu vào model
//            for (ChuyenTau chuyenTau : danhSachChuyenTau) {
//                chuyenTauModel.addElement(chuyenTau.getMaChuyenTau());
//            }
//
//            // Cập nhật JList
//            banVeScreen.getChuyenTauList().setModel(chuyenTauModel);
//
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Lỗi khi tải danh sách chuyến tàu: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//}
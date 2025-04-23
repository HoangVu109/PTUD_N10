//package iuh.controller.QuanLyCon;
//
//import iuh.dao.QuanLyDAO.QuanLyTauDAO;
//
//import javax.swing.table.DefaultTableModel;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class QuanLyTauController {
//    private List<Tau> tauList;
//    private DefaultTableModel tableModel;
//    private QuanLyTauDAO dao;
//
//    public QuanLyTauController(DefaultTableModel tableModel) throws SQLException {
//        this.tableModel = tableModel;
//        this.tauList = new ArrayList<>();
//        this.dao = new QuanLyTauDAO();
//        loadInitialData();
//    }
//
//    private void loadInitialData() throws SQLException {
//        tauList = dao.layDanhSachTau(); // Chỉ load dữ liệu từ database
//        updateTable();
//    }
//
//    public void updateTable() {
//        tableModel.setRowCount(0);
//        int stt = 1;
//        for (Tau tau : tauList) {
//            tableModel.addRow(new Object[]{
//                    stt++,
//                    tau.getMaTau(),
//                    tau.getMaTuyenTau(),
//                    tau.isDaBiXoa() ? "Đã xóa" : "Hoạt động"
//            });
//        }
//    }
//
//    public void addTau(String maTau, String maTuyenTau) throws SQLException {
//        Tau newTau = new Tau(maTau, maTuyenTau);
//        dao.themTau(newTau); // Thêm vào database qua DAO
//        tauList.add(newTau); // Thêm vào danh sách trong bộ nhớ
//        updateTable();
//    }
//
//    public void editTau(int row, String maTau, String maTuyenTau) throws SQLException {
//        if (row >= 0 && row < tauList.size()) {
//            Tau tau = tauList.get(row);
//            tau.setMaTau(maTau);
//            tau.setMaTuyenTau(maTuyenTau);
//            dao.suaTau(tau); // Cập nhật trong database qua DAO
//            updateTable();
//        }
//    }
//
//    public void deleteTau(int row) throws SQLException {
//        if (row >= 0 && row < tauList.size()) {
//            Tau tau = tauList.get(row);
//            dao.xoaTau(tau.getMaTau()); // Xóa trong database qua DAO
//            tau.setDaBiXoa(true); // Cập nhật trong danh sách
//            updateTable();
//        }
//    }
//
//    public void restoreTau(int row) throws SQLException {
//        if (row >= 0 && row < tauList.size()) {
//            Tau tau = tauList.get(row);
//            dao.khoiPhucTau(tau.getMaTau()); // Khôi phục trong database qua DAO
//            tau.setDaBiXoa(false); // Cập nhật trong danh sách
//            updateTable();
//        }
//    }
//
//    public Tau getTau(int row) {
//        if (row >= 0 && row < tauList.size()) {
//            return tauList.get(row);
//        }
//        return null;
//    }
//
//    public void searchTau(String keyword) throws SQLException {
//        if (keyword == null || keyword.trim().isEmpty()) {
//            tauList = dao.layDanhSachTau(); // Nếu không có từ khóa, load lại toàn bộ
//        } else {
//            tauList = dao.timKiemTau(keyword.toLowerCase()); // Tìm kiếm qua DAO
//        }
//        updateTable();
//    }
//}
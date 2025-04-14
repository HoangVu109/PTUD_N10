package iuh.controller.QuanLyCon;

import iuh.dao.QuanLyDAO.QuanLyTuyenTauDao;
import iuh.model.TuyenTau;

import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuanLyTuyenTauController {
    private List<TuyenTau> routes;
    private DefaultTableModel tableModel;
    private QuanLyTuyenTauDao dao;

    public QuanLyTuyenTauController(DefaultTableModel tableModel) throws SQLException {
        this.tableModel = tableModel;
        this.routes = new ArrayList<>();
        this.dao = new QuanLyTuyenTauDao();
        loadInitialData();
    }

    private void loadInitialData() throws SQLException {
        routes = dao.layDanhSachTuyenTau(); // Chỉ load dữ liệu từ database
        updateTable();
    }

    public void updateTable() {
        tableModel.setRowCount(0);
        int stt = 1;
        for (TuyenTau route : routes) {
            tableModel.addRow(new Object[]{
                    stt++,
                    route.getMaTuyenTau(),
                    route.getGaKhoiHanh(),
                    route.getGaKetThuc(),
                    route.isDaBiXoa() ? "Đã xóa" : "Hoạt động"
            });
        }
    }

    public void addRoute(String maTuyenTau, String gaKhoiHanh, String gaKetThuc) throws SQLException {
        TuyenTau newRoute = new TuyenTau(maTuyenTau, gaKhoiHanh, gaKetThuc);
        dao.themTuyenTau(newRoute); // Thêm vào database qua DAO
        routes.add(newRoute); // Thêm vào danh sách trong bộ nhớ
        updateTable();
    }

    public void editRoute(int row, String maTuyenTau, String gaKhoiHanh, String gaKetThuc) throws SQLException {
        if (row >= 0 && row < routes.size()) {
            TuyenTau route = routes.get(row);
            route.setMaTuyenTau(maTuyenTau);
            route.setGaKhoiHanh(gaKhoiHanh);
            route.setGaKetThuc(gaKetThuc);
            dao.suaTuyenTau(route); // Cập nhật trong database qua DAO
            updateTable();
        }
    }

    public void deleteRoute(int row) throws SQLException {
        if (row >= 0 && row < routes.size()) {
            TuyenTau route = routes.get(row);
            dao.xoaTuyenTau(route.getMaTuyenTau()); // Xóa trong database qua DAO
            route.setDaBiXoa(true); // Cập nhật trong danh sách
            updateTable();
        }
    }

    public void restoreRoute(int row) throws SQLException {
        if (row >= 0 && row < routes.size()) {
            TuyenTau route = routes.get(row);
            dao.khoiPhucTuyenTau(route.getMaTuyenTau()); // Khôi phục trong database qua DAO
            route.setDaBiXoa(false); // Cập nhật trong danh sách
            updateTable();
        }
    }

    public TuyenTau getRoute(int row) {
        if (row >= 0 && row < routes.size()) {
            return routes.get(row);
        }
        return null;
    }

    public void searchRoute(String keyword) throws SQLException {
        if (keyword == null || keyword.trim().isEmpty()) {
            routes = dao.layDanhSachTuyenTau(); // Nếu không có từ khóa, load lại toàn bộ
        } else {
            routes = dao.timKiemTuyenTau(keyword.toLowerCase()); // Tìm kiếm qua DAO
        }
        updateTable();
    }
}
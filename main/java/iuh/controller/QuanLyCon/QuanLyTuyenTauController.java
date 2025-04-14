package iuh.controller.QuanLyController;

import iuh.model.TuyenTau;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class QuanLyTuyenTauController {
    private List<TuyenTau> routes;
    private List<TuyenTau> filteredRoutes; // For search functionality
    private DefaultTableModel tableModel;
    private boolean isSearching = false;

    public QuanLyTuyenTauController(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
        this.routes = new ArrayList<>();
        this.filteredRoutes = new ArrayList<>();
        loadInitialData();
    }

    // Tải dữ liệu ban đầu
    private void loadInitialData() {
        routes.add(new TuyenTau("T01","Hà Nội", "Sài Gòn" ));
        routes.add(new TuyenTau("T002", "Đà Nẵng", "Huế"));
        routes.get(1).setDaBiXoa(true); // Đánh dấu tuyến T002 là đã xóa
        updateTable();
    }

    // Cập nhật bảng từ danh sách routes
    public void updateTable() {
        tableModel.setRowCount(0); // Xóa dữ liệu cũ
        int stt = 1;

        List<TuyenTau> displayRoutes = isSearching ? filteredRoutes : routes;

        for (TuyenTau route : displayRoutes) {
            tableModel.addRow(new Object[]{
                    stt++,
                    route.getMaTuyenTau(),
                    route.getGaKhoiHanh(),
                    route.getGaKetThuc(),
                    route.isDaBiXoa() ? "Đã xóa" : "Hoạt động",
                    ""
            });
        }
    }

    // Thêm tuyến tàu
    public void addRoute(String maTuyenTau, String gaKhoiHanh, String gaKetThuc) {
        TuyenTau newRoute = new TuyenTau(maTuyenTau, gaKhoiHanh, gaKetThuc);
        routes.add(newRoute);
        updateTable();
    }

    // Sửa tuyến tàu
    public void editRoute(int row, String maTuyenTau, String gaKhoiHanh, String gaKetThuc) {
        // Get the correct route based on whether we're searching or not
        TuyenTau route = isSearching ? filteredRoutes.get(row) : routes.get(row);

        // Update the original route in the main list
        int originalIndex = routes.indexOf(route);
        if (originalIndex != -1) {
            routes.get(originalIndex).setMaTuyenTau(maTuyenTau);
            routes.get(originalIndex).setGaKhoiHanh(gaKhoiHanh);
            routes.get(originalIndex).setGaKetThuc(gaKetThuc);
        }

        updateTable();
    }

    // Xóa tuyến tàu (đánh dấu daBiXoa = true)
    public void deleteRoute(int row) {
        // Get the correct route based on whether we're searching or not
        TuyenTau route = isSearching ? filteredRoutes.get(row) : routes.get(row);

        // Update the original route in the main list
        int originalIndex = routes.indexOf(route);
        if (originalIndex != -1) {
            routes.get(originalIndex).setDaBiXoa(true);
        }

        updateTable();
    }

    // Khôi phục tuyến tàu
    public void restoreRoute(int row) {
        // Get the correct route based on whether we're searching or not
        TuyenTau route = isSearching ? filteredRoutes.get(row) : routes.get(row);

        // Update the original route in the main list
        int originalIndex = routes.indexOf(route);
        if (originalIndex != -1) {
            routes.get(originalIndex).setDaBiXoa(false);
        }

        updateTable();
    }

    // Lấy thông tin tuyến tàu để sửa
    public TuyenTau getRoute(int row) {
        return isSearching ? filteredRoutes.get(row) : routes.get(row);
    }

    // Tìm kiếm tuyến tàu
    public void searchRoute(String keyword) {
        filteredRoutes.clear();
        keyword = keyword.toLowerCase().trim();

        if (keyword.isEmpty()) {
            isSearching = false;
            updateTable();
            return;
        }

        for (TuyenTau route : routes) {
            if (route.getMaTuyenTau().toLowerCase().contains(keyword) ||
                    route.getGaKhoiHanh().toLowerCase().contains(keyword) ||
                    route.getGaKetThuc().toLowerCase().contains(keyword)) {
                filteredRoutes.add(route);
            }
        }

        isSearching = true;
        updateTable();
    }

    // Reset search and show all routes
    public void resetSearch() {
        isSearching = false;
        updateTable();
    }
}
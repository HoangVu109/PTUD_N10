package iuh.controller;

import iuh.dao.QuanLyChuyenTauDao;
import iuh.gui.QuanLyChuyenTauScreen;
import iuh.model.ChuyenTau;

import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.List;

public class QuanLyChuyenTauController {
    private final QuanLyChuyenTauScreen view;
    private final QuanLyChuyenTauDao dao;

    public QuanLyChuyenTauController(QuanLyChuyenTauScreen view) {
        this.view = view;
        this.dao = new QuanLyChuyenTauDao();
    }

    // Tải dữ liệu ban đầu
    public void loadInitialData() {
        List<ChuyenTau> chuyenTauList = dao.getAllChuyenTau();
        updateTableData(chuyenTauList);
    }

    // Cập nhật dữ liệu bảng
    public void updateTableData(List<ChuyenTau> chuyenTauList) {
        DefaultTableModel tableModel = view.getTableModel();
        tableModel.setRowCount(0); // Xóa dữ liệu cũ

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        for (ChuyenTau chuyenTau : chuyenTauList) {
            Object[] row = {
                    chuyenTau.getMaChuyenTau(),
                    chuyenTau.getMaTau(),
                    dateFormat.format(chuyenTau.getGioKhoiHanh()),
                    chuyenTau.getSoLuongToa(),
                    chuyenTau.getTuyenTau(),
                    chuyenTau.getSoLuongHanhKhach()
            };
            tableModel.addRow(row);
        }
    }

    // Tìm kiếm chuyến tàu
    public void search(String keyword) {
        List<ChuyenTau> chuyenTauList = dao.searchChuyenTau(keyword);
        updateTableData(chuyenTauList);
    }
}
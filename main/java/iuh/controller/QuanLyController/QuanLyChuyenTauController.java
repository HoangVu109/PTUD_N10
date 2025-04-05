package iuh.controller.QuanLyController;

import iuh.dao.QuanLyDao.QuanLyChuyenTauDao;
import iuh.gui.QuanLy.QuanLyChuyenTauScreen;
import iuh.model.ChuyenTau;

import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.List;

public class QuanLyChuyenTauController {
    private final QuanLyChuyenTauScreen view;
    private final QuanLyChuyenTauDao dao;
    private final SimpleDateFormat dateFormat;

    public QuanLyChuyenTauController(QuanLyChuyenTauScreen view) {
        this.view = view;
        this.dao = new QuanLyChuyenTauDao();
        this.dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    }

    public void loadInitialData() {
        List<ChuyenTau> chuyenTauList = dao.getAllChuyenTau();
        updateTableData(chuyenTauList);
    }

    public void updateTableData(List<ChuyenTau> chuyenTauList) {
        DefaultTableModel tableModel = view.getTableModel();
        tableModel.setRowCount(0);

        for (ChuyenTau chuyenTau : chuyenTauList) {
            Object[] row = {
                    chuyenTau.getMaChuyenTau(),
                    chuyenTau.getMaTau(),
                    dateFormat.format(chuyenTau.getGioKhoiHanh()),
                    chuyenTau.getTuyenTau(),
                    chuyenTau.getSoluongHK(),
                    chuyenTau.getSoLuongHKToiDa()
            };
            tableModel.addRow(row);
        }
    }

    public void search(String keyword) {
        List<ChuyenTau> chuyenTauList = dao.searchChuyenTau(keyword);
        updateTableData(chuyenTauList);
    }
}
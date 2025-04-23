package iuh.controller.QuanLyCon;

import iuh.dao.QuanLyDAO.QuanLyChuyenTauDao;
import iuh.gui.QuanLy.QuanLyChuyenTauScreen;
import iuh.model.ChuyenTau;

import javax.swing.table.DefaultTableModel;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class QuanLyChuyenTauController {
    private final QuanLyChuyenTauScreen view;
    private final QuanLyChuyenTauDao dao;
    private final DateTimeFormatter dateFormatter;

    public QuanLyChuyenTauController(QuanLyChuyenTauScreen view) {
        this.view = view;
        this.dao = new QuanLyChuyenTauDao();
        this.dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
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
                    chuyenTau.getTau().getMaTau(),
                    chuyenTau.getGioKhoiHanh().format(dateFormatter)
            };
            tableModel.addRow(row);
        }
    }

    public void search(String keyword) {
        List<ChuyenTau> chuyenTauList = dao.searchChuyenTau(keyword);
        updateTableData(chuyenTauList);
    }
}
package iuh.controller.QuanLyCon;

import iuh.dao.QuanLyDAO.LichTrinhDao;
import iuh.model.ChuyenTau;
import iuh.screen.jpanel.LichTrinhPanel;

import javax.swing.table.DefaultTableModel;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class LichTrinhController {
    private final LichTrinhPanel view;
    private final LichTrinhDao dao;
    private final DateTimeFormatter dateFormatter;

    public LichTrinhController(LichTrinhPanel view) {
        this.view = view;
        this.dao = new LichTrinhDao();
        this.dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    }


    public void loadInitialData() {
        List<ChuyenTau> chuyenTauList = dao.getActiveChuyenTau();
        updateTableData(chuyenTauList);
    }

    public void updateTableData(List<ChuyenTau> chuyenTauList) {
        DefaultTableModel tableModel = view.getTableModel();
        tableModel.setRowCount(0);

        int stt = 1; // Start STT from 1
        for (ChuyenTau chuyenTau : chuyenTauList) {
            Object[] row = {
                    stt++, // Incremental STT
                    chuyenTau.getMaChuyenTau(),
                    chuyenTau.getTau().getMaTau(),
                    chuyenTau.getTau().getTuyenTau().getGaKhoiHanh(),
                    chuyenTau.getTau().getTuyenTau().getGaKetThuc(),
                    chuyenTau.getGioKhoiHanh().format(dateFormatter)
            };
            tableModel.addRow(row);
        }
    }

    public void search(String keyword) {
        List<ChuyenTau> chuyenTauList = dao.searchActiveChuyenTau(keyword);
        updateTableData(chuyenTauList);
    }
}
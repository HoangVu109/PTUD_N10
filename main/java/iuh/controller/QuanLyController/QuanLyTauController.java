package iuh.controller.QuanLyController;

import iuh.model.Tau;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class QuanLyTauController {
    private List<Tau> tauList;
    private DefaultTableModel tableModel;

    public QuanLyTauController(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
        this.tauList = new ArrayList<>();
        loadInitialData();
    }

    private void loadInitialData() {
        tauList.add(new Tau("TAU001", "CT001"));
        tauList.add(new Tau("TAU002", "CT002"));
        tauList.get(1).setDaBiXoa(true); // Đánh dấu tàu TAU002 là đã xóa
        updateTable();
    }

    public void updateTable() {
        tableModel.setRowCount(0);
        int stt = 1;
        for (Tau tau : tauList) {
            tableModel.addRow(new Object[]{
                    stt++,
                    tau.getMaTau(),
                    tau.getMaTuyenTau(),
                    tau.isDaBiXoa() ? "Đã xóa" : "Hoạt động"
            });
        }
    }

    public void addTau(String maTau, String maTuyenTau) {
        Tau newTau = new Tau(maTau, maTuyenTau);
        tauList.add(newTau);
        updateTable();
    }

    public void editTau(int row, String maTau, String maTuyenTau) {
        if (row >= 0 && row < tauList.size()) {
            Tau tau = tauList.get(row);
            tau.setMaTau(maTau);
            tau.setMaTuyenTau(maTuyenTau);
            updateTable();
        }
    }

    public void deleteTau(int row) {
        if (row >= 0 && row < tauList.size()) {
            tauList.get(row).setDaBiXoa(true);
            updateTable();
        }
    }

    public void restoreTau(int row) {
        if (row >= 0 && row < tauList.size()) {
            tauList.get(row).setDaBiXoa(false);
            updateTable();
        }
    }

    public Tau getTau(int row) {
        if (row >= 0 && row < tauList.size()) {
            return tauList.get(row);
        }
        return null;
    }

    public void searchTau(String keyword) {
        tableModel.setRowCount(0);
        int stt = 1;
        for (Tau tau : tauList) {
            if (tau.getMaTau().toLowerCase().contains(keyword) ||
                    tau.getMaTuyenTau().toLowerCase().contains(keyword)) {
                tableModel.addRow(new Object[]{
                        stt++,
                        tau.getMaTau(),
                        tau.getMaTuyenTau(),
                        tau.isDaBiXoa() ? "Đã xóa" : "Hoạt động"
                });
            }
        }
    }
}
package iuh.controller.QuanLyCon;

import iuh.dao.QuanLyDAO.QuanLyHanhKhachDao;
import iuh.screen.QuanLy.QuanLyHanhKhachScreen;
import iuh.model.HanhKhach;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class QuanLyHanhKhachController {
    private final QuanLyHanhKhachScreen view;
    private final QuanLyHanhKhachDao dao;

    public QuanLyHanhKhachController(QuanLyHanhKhachScreen view) {
        this.view = view;
        this.dao = new QuanLyHanhKhachDao();
    }

    public void loadInitialData() {
        List<HanhKhach> hanhKhachList = dao.getAllHanhKhach();
        updateTableData(hanhKhachList);
    }

    public void updateTableData(List<HanhKhach> hanhKhachList) {
        DefaultTableModel tableModel = view.getTableModel();
        tableModel.setRowCount(0);

        int stt = 1;
        for (HanhKhach hanhKhach : hanhKhachList) {
            Object[] row = {
                    stt++,
                    hanhKhach.getSoCCCD(),
                    hanhKhach.getHoTenHK(),
                    hanhKhach.getSoDT(),
                    hanhKhach.getDiaChi() != null ? hanhKhach.getDiaChi() : ""
            };
            tableModel.addRow(row);
        }
    }

    public void search(String keyword) {
        List<HanhKhach> hanhKhachList = dao.searchHanhKhach(keyword);
        updateTableData(hanhKhachList);
    }
}
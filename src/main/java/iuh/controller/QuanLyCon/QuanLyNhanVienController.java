package iuh.controller.QuanLyCon;

import iuh.dao.QuanLyDAO.QuanLyNhanVienDao;
import iuh.screen.QuanLy.QuanLyNhanVienScreen;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class QuanLyNhanVienController {
    private QuanLyNhanVienScreen view;
    private QuanLyNhanVienDao model;
    private String[][] fullData;
    private List<Integer> danhsachgoc;

    public QuanLyNhanVienController(QuanLyNhanVienScreen view) {
        this.view = view;
        this.model = new QuanLyNhanVienDao();
        this.danhsachgoc = new ArrayList<>();
        loadInitialData();
    }

    public void loadInitialData() {
        List<String[]> nhanVienList = model.getAllNhanVien();
        fullData = nhanVienList.toArray(new String[0][]);
        danhsachgoc.clear();
        for (int i = 0; i < fullData.length; i++) {
            danhsachgoc.add(i);
        }
        updateTableData(fullData);
    }

    private void updateTableData(String[][] data) {
        view.getTableModel().setRowCount(0);
        for (String[] row : data) {
            Object[] displayRow = new Object[5];
            displayRow[0] = row[0]; // maSoNV
            displayRow[1] = row[1]; // hoTenNV
            displayRow[2] = row[2].equals("0") ? "Quản lý" : "Nhân viên"; // chucVu
            displayRow[3] = row[3].equals("0") ? "Nam" : "Nữ"; // gioiTinh
            view.getTableModel().addRow(displayRow);
        }
    }

    public void searchEmployee(String searchText) {
        searchText = searchText.trim().toLowerCase();
        List<String[]> filteredData = new ArrayList<>();
        danhsachgoc.clear();

        for (int i = 0; i < fullData.length; i++) {
            String[] row = fullData[i];
            boolean match = false;
            for (int j = 0; j < row.length; j++) {
                String cellText = row[j];

                if (j == 2) { // chucVu
                    cellText = cellText.equals("0") ? "quản lý" : "nhân viên";
                } else if (j == 3) { // gioiTinh
                    cellText = cellText.equals("0") ? "nam" : "nữ";
                } else if (j == 9) { // daNghiViec
                    cellText = cellText.equals("1") ? "đã nghỉ việc" : "đang làm việc";
                }
                if (cellText != null && cellText.toLowerCase().contains(searchText)) {
                    match = true;
                    break;
                }
            }
            if (match) {
                filteredData.add(row);
                danhsachgoc.add(i);
            }
        }

        if (filteredData.isEmpty()) {
            JOptionPane.showMessageDialog(view.getPanel(), "Không tìm thấy nhân viên!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            updateTableData(fullData);
            danhsachgoc.clear();
            for (int i = 0; i < fullData.length; i++) {
                danhsachgoc.add(i);
            }
        } else {
            updateTableData(filteredData.toArray(new String[0][]));
        }
    }

    public void updateEmployeeInfo(int selectedRow) {
        if (selectedRow != -1 && selectedRow < danhsachgoc.size()) {
            int originalIndex = danhsachgoc.get(selectedRow);
            String[] data = fullData[originalIndex];
            view.updateEmployeePanel(
                    data[0], // maNV
                    data[1], // hoTen
                    data[2].equals("0") ? "Quản lý" : "Nhân viên", // chucVu
                    data[3].equals("0") ? "Nam" : "Nữ", // gioiTinh
                    data[4], // cccd
                    data[5], // ngaySinh
                    data[6], // diaChi
                    data[9].equals("1") // daNghiViec
            );
        }
    }

    public String[][] getFullData() {
        return fullData;
    }

    public List<Integer> getDanhSachGoc() {
        return danhsachgoc;
    }
}
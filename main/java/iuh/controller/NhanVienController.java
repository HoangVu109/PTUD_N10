package iuh.controller;

import iuh.dao.NhanVienDao;
import iuh.gui.QuanLyNhanVienScreen;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class NhanVienController {
    private QuanLyNhanVienScreen view;
    private NhanVienDao model;
    private String[][] fullData;
    private List<Integer> danhsachgoc;

    public NhanVienController(QuanLyNhanVienScreen view) {
        this.view = view;
        this.model = new NhanVienDao();
        this.danhsachgoc = new ArrayList<>();
        loadInitialData();
    }

    private void loadInitialData() {
        List<String[]> nhanVienList = model.getAllNhanVien();
        fullData = nhanVienList.toArray(new String[0][]);
        danhsachgoc.clear();
        for (int i = 0; i < fullData.length; i++) {
            danhsachgoc.add(i);
        }
        updateTableData(fullData);
    }

    // Cập nhật dữ liệu cho JTable
    private void updateTableData(String[][] data) {
        view.getTableModel().setRowCount(0);
        for (int i = 0; i < data.length; i++) {
            String[] row = data[i];
            Object[] displayRow = new Object[4];
            displayRow[0] = row[0]; // maSoNV
            displayRow[1] = row[1]; // hoTenNV
            displayRow[2] = row[2].equals("0") ? "Quản lý" : "Nhân viên"; // chucVu: 0 = Quản lý, 1 = Nhân viên
            displayRow[3] = row[3].equals("0") ? "Nam" : "Nữ"; // gioiTinh: 0 = Nam, 1 = Nữ
            view.getTableModel().addRow(displayRow);
        }
    }

    // Xử lý tìm kiếm
    public void searchEmployee(String searchText) {
        searchText = searchText.trim().toLowerCase();
        List<String[]> filteredData = new ArrayList<>();
        danhsachgoc.clear();

        for (int i = 0; i < fullData.length; i++) {
            String[] row = fullData[i];
            boolean match = false;
            for (String cell : row) {
                if (cell != null && cell.toLowerCase().contains(searchText)) {
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

    // Cập nhật thông tin cho employeePanel
    public void updateEmployeeInfo(int selectedRow) {
        if (selectedRow != -1 && selectedRow < danhsachgoc.size()) {
            int originalIndex = danhsachgoc.get(selectedRow);
            String maNV = fullData[originalIndex][0];      // maSoNV
            String hoTen = fullData[originalIndex][1];     // hoTenNV
            String chucVu = fullData[originalIndex][2].equals("0") ? "Quản lý" : "Nhân viên"; // chucVu: 0 = Quản lý, 1 = Nhân viên
            String gioiTinh = fullData[originalIndex][3].equals("0") ? "Nam" : "Nữ";          // gioiTinh: 0 = Nam, 1 = Nữ
            String cccd = fullData[originalIndex][4];      // soCCCD
            String ngaySinh = fullData[originalIndex][5];  // ngaySinh
            String diaChi = fullData[originalIndex][6];    // diaChi
            view.updateEmployeePanel(maNV, hoTen, chucVu, gioiTinh, cccd, ngaySinh, diaChi);
        }
    }

    public String[][] getFullData() {
        return fullData;
    }

    public List<Integer> getOriginalIndices() {
        return danhsachgoc;
    }
}
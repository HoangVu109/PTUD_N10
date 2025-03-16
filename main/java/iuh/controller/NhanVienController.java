package iuh.controller;

import iuh.dao.NhanVienDao;
import iuh.gui.QuanLyNhanVienScreen;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class NhanVienController {
    private QuanLyNhanVienScreen view;
    private NhanVienDao model;
    private String[][] fullData; // Lưu toàn bộ dữ liệu
    private List<Integer> danhsachgoc; // Lưu chỉ số gốc của các hàng hiển thị

    public NhanVienController(QuanLyNhanVienScreen view) {
        this.view = view;
        this.model = new NhanVienDao();
        this.danhsachgoc = new ArrayList<>();
        loadInitialData();
    }

    // Tải dữ liệu ban đầu từ Model
    private void loadInitialData() {
        List<String[]> nhanVienList = model.getAllNhanVien();
        fullData = nhanVienList.toArray(new String[0][]);
        danhsachgoc.clear();
        for (int i = 0; i < fullData.length; i++) {
            danhsachgoc.add(Integer.valueOf(i));
        }
        updateTableData(fullData);
    }

    // Cập nhật dữ liệu vào JTable trong View
    private void updateTableData(String[][] data) {
        view.getTableModel().setRowCount(0);
        for (String[] row : data) {
            Object[] displayRow = new Object[4];
            displayRow[0] = row[0]; // Mã nhân viên
            displayRow[1] = row[1]; // Họ tên
            displayRow[2] = row[2]; // Chức vụ
            displayRow[3] = row[3]; // Giới tính
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
                danhsachgoc.add(Integer.valueOf(i));
            }
        }

        if (filteredData.isEmpty()) {
            JOptionPane.showMessageDialog(view.getPanel(), "Không tìm thấy nhân viên!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            // Khôi phục danh sách ban đầu
            updateTableData(fullData);
            danhsachgoc.clear();
            for (int i = 0; i < fullData.length; i++) {
                danhsachgoc.add(Integer.valueOf(i));
            }
        } else {
            updateTableData(filteredData.toArray(new String[0][]));
        }
    }

    // Cập nhật thông tin nhân viên khi chọn từ JTable
    public void updateEmployeeInfo(int selectedRow) {
        if (selectedRow != -1 && selectedRow < danhsachgoc.size()) {
            int originalIndex = danhsachgoc.get(selectedRow);
            String maNV = fullData[originalIndex][0];
            String hoTen = fullData[originalIndex][1];
            String chucVu = fullData[originalIndex][2];
            String gioiTinh = fullData[originalIndex][3];
            String cccd = fullData[originalIndex][4];
            String ngaySinh = fullData[originalIndex][5];
            String diaChi = fullData[originalIndex][6];
            view.updateEmployeePanel(maNV, hoTen, chucVu, gioiTinh, cccd, ngaySinh, diaChi);
        }
    }

    // Getter cho fullData
    public String[][] getFullData() {
        return fullData;
    }

    // Getter cho danhsachgoc
    public List<Integer> getOriginalIndices() {
        return danhsachgoc;
    }
}
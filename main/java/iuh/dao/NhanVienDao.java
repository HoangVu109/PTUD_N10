package iuh.dao;

import iuh.connect.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDao extends Dao {
    private final Connection connection;

    public NhanVienDao() {
        connection = DatabaseConnection.getConnection();
        if (connection == null) {
            System.err.println("Không thể khởi tạo kết nối trong NhanVienDao!");
        }
    }

    public List<String[]> getAllNhanVien() {
        List<String[]> nhanVienList = new ArrayList<>();
        if (connection == null) {
            System.err.println("Connection is null in getAllNhanVien!");
            return nhanVienList;
        }
        // Cập nhật truy vấn để khớp với các cột trong bảng
        String sql = "SELECT maSoNV, hoTenNV, chucVu, gioiTinh, soCCCD, ngaySinh, diaChi, soDT, matKhau FROM NhanVien";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                // Tạo mảng String với 9 phần tử tương ứng 9 cột
                String[] nhanVien = new String[9];
                nhanVien[0] = rs.getString("maSoNV");     // Mã số nhân viên
                nhanVien[1] = rs.getString("hoTenNV");    // Họ tên nhân viên
                nhanVien[2] = rs.getString("chucVu");     // Chức vụ (0 hoặc 1)
                nhanVien[3] = rs.getString("gioiTinh");   // Giới tính (0 hoặc 1)
                nhanVien[4] = rs.getString("soCCCD");     // Số CCCD
                nhanVien[5] = rs.getString("ngaySinh");   // Ngày sinh
                nhanVien[6] = rs.getString("diaChi");     // Địa chỉ
                nhanVien[7] = rs.getString("soDT");       // Số điện thoại
                nhanVien[8] = rs.getString("matKhau");    // Mật khẩu
                nhanVienList.add(nhanVien);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi truy vấn getAllNhanVien: " + e.getMessage());
            e.printStackTrace();
        }
        return nhanVienList;
    }

    public boolean deleteNhanVien(String maNV) {
        // Cập nhật tên cột MaNV thành maSoNV
        String sql = "DELETE FROM NhanVien WHERE maSoNV = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, maNV);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
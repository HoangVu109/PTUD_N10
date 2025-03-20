package iuh.dao;

import iuh.connect.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDao extends Dao {

    public NhanVienDao() {
    }

    public List<String[]> getAllNhanVien() {
        List<String[]> nhanVienList = new ArrayList<>();
        String sql = "SELECT maSoNV, hoTenNV, chucVu, gioiTinh, soCCCD, ngaySinh, diaChi, soDT, matKhau FROM NhanVien";

        try {
            Connection conn = DatabaseConnection.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    String[] nhanVien = new String[9];
                    nhanVien[0] = rs.getString("maSoNV");
                    nhanVien[1] = rs.getString("hoTenNV");
                    nhanVien[2] = rs.getString("chucVu");
                    nhanVien[3] = rs.getString("gioiTinh");
                    nhanVien[4] = rs.getString("soCCCD");
                    nhanVien[5] = rs.getString("ngaySinh");
                    nhanVien[6] = rs.getString("diaChi");
                    nhanVien[7] = rs.getString("soDT");
                    nhanVien[8] = rs.getString("matKhau");
                    nhanVienList.add(nhanVien);
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi truy vấn getAllNhanVien: " + e.getMessage());
            e.printStackTrace();
        }
        return nhanVienList;
    }

    public boolean deleteNhanVien(String maNV) {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();

            // Xóa nhân viên từ bảng NhanVien
            String deleteNhanVienSql = "DELETE FROM NhanVien WHERE maSoNV = ?";
            try (PreparedStatement stmtNhanVien = conn.prepareStatement(deleteNhanVienSql)) {
                stmtNhanVien.setString(1, maNV);
                int rowsAffected = stmtNhanVien.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            System.err.println("Lỗi truy vấn deleteNhanVien: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
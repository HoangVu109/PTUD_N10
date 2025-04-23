package iuh.dao.QuanLyDAO;

import iuh.connect.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuanLyNhanVienDao {

    public List<String[]> getAllNhanVien() {
        List<String[]> nhanVienList = new ArrayList<>();
        String sql = "{call sp_LayDanhSachNhanVienDangLamViec}";

        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String[] nhanVien = new String[10];
                nhanVien[0] = rs.getString("maSoNV");
                nhanVien[1] = rs.getString("hoTenNV");
                nhanVien[2] = String.valueOf(rs.getInt("chucVu"));
                nhanVien[3] = String.valueOf(rs.getInt("gioiTinh"));
                nhanVien[4] = rs.getString("soCCCD");
                nhanVien[5] = rs.getString("ngaySinh");
                nhanVien[6] = rs.getString("diaChi");
                nhanVien[7] = rs.getString("soDT");
                nhanVien[8] = rs.getString("matKhau");
                nhanVien[9] = String.valueOf(rs.getBoolean("daNghiViec") ? 1 : 0);
                nhanVienList.add(nhanVien);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi truy vấn getAllNhanVien: " + e.getMessage());
            e.printStackTrace();
        }
        return nhanVienList;
    }

    public boolean addNhanVien(String maNV, String cccd, String hoTen, Date ngaySinh, int gioiTinh,
                               String soDT, String diaChi, int chucVu, String matKhau, boolean daNghiViec) {
        String sql = "{call sp_ThemNhanVien(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setString(1, maNV);
            stmt.setString(2, cccd);
            stmt.setString(3, hoTen);
            stmt.setDate(4, ngaySinh);
            stmt.setInt(5, gioiTinh);
            stmt.setString(6, soDT);
            stmt.setString(7, diaChi);
            stmt.setInt(8, chucVu);
            stmt.setString(9, matKhau);
            stmt.setBoolean(10, daNghiViec);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi truy vấn addNhanVien: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateNhanVien(String maNV, String cccd, String hoTen, Date ngaySinh, int gioiTinh,
                                  String soDT, String diaChi, int chucVu, String matKhau, boolean daNghiViec) {
        String sql = "{call sp_CapNhatNhanVien(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setString(1, maNV);
            stmt.setString(2, cccd);
            stmt.setString(3, hoTen);
            stmt.setDate(4, ngaySinh);
            stmt.setInt(5, gioiTinh);
            stmt.setString(6, soDT);
            stmt.setString(7, diaChi);
            stmt.setInt(8, chucVu);
            stmt.setString(9, matKhau);
            stmt.setBoolean(10, daNghiViec);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi truy vấn updateNhanVien: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteNhanVien(String maNV) {
        String sql = "{call sp_XoaNhanVien(?)}";
        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setString(1, maNV);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi truy vấn deleteNhanVien: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
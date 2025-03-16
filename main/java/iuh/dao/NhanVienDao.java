package iuh.dao;

import iuh.connect.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDao extends Dao{
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
        String sql = "SELECT MaNV, HoTen, ChucVu, GioiTinh, CCCD, NgaySinh, DiaChi FROM NhanVien";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String[] nhanVien = new String[7];
                nhanVien[0] = rs.getString("MaNV");
                nhanVien[1] = rs.getString("HoTen");
                nhanVien[2] = rs.getString("ChucVu");
                nhanVien[3] = rs.getString("GioiTinh");
                nhanVien[4] = rs.getString("CCCD");
                nhanVien[5] = rs.getString("NgaySinh");
                nhanVien[6] = rs.getString("DiaChi");
                nhanVienList.add(nhanVien);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi truy vấn getAllNhanVien: " + e.getMessage());
            e.printStackTrace();
        }
        return nhanVienList;
    }
    public boolean deleteNhanVien(String maNV) {
        String sql = "DELETE FROM NhanVien WHERE MaNV = ?";
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



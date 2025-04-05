package iuh.dao.QuanLyDao;

import iuh.connect.DatabaseConnection;
import iuh.model.ChuyenTau;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuanLyChuyenTauDao {

    public List<ChuyenTau> getAllChuyenTau() {
        List<ChuyenTau> chuyenTauList = new ArrayList<>();
        String procedureCall = "{call sp_LayDanhSachChuyenTau}";

        try {
            Connection conn = DatabaseConnection.getConnection();
            try (CallableStatement cstmt = conn.prepareCall(procedureCall);
                 ResultSet rs = cstmt.executeQuery()) {

                while (rs.next()) {
                    ChuyenTau chuyenTau = new ChuyenTau();
                    chuyenTau.setMaChuyenTau(rs.getString("maChuyenTau"));
                    chuyenTau.setMaTau(rs.getString("maTau"));
                    chuyenTau.setGioKhoiHanh(rs.getTimestamp("gioKhoiHanh"));
                    chuyenTau.setDaBiHuy(rs.getBoolean("daBiHuy"));
                    chuyenTau.setSoLuongHKToiDa(rs.getInt("soLuongHKToiDa"));
                    chuyenTau.setSoluongHK(rs.getInt("soLuongHK"));

                    String tuyenTau = rs.getString("gaKhoiHanh") + " - " + rs.getString("gaKetThuc");
                    chuyenTau.setTuyenTau(tuyenTau);

                    chuyenTauList.add(chuyenTau);
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi truy vấn sp_LayDanhSachChuyenTau: " + e.getMessage());
            e.printStackTrace();
        }
        return chuyenTauList;
    }

    public boolean addChuyenTau(String maChuyenTau, String maTau, Timestamp gioKhoiHanh, boolean daBiHuy, int soLuongHKToiDa, int soLuongHK) {
        String procedureCall = "{call sp_ThemChuyenTau(?, ?, ?, ?, ?, ?)}";

        try {
            Connection conn = DatabaseConnection.getConnection();
            try (CallableStatement cstmt = conn.prepareCall(procedureCall)) {
                cstmt.setString(1, maChuyenTau);
                cstmt.setString(2, maTau);
                cstmt.setTimestamp(3, new Timestamp(gioKhoiHanh.getTime()));
                cstmt.setBoolean(4, daBiHuy);
                cstmt.setInt(5, soLuongHKToiDa);
                cstmt.setInt(6, soLuongHK);

                int rowsAffected = cstmt.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm chuyến tàu: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<ChuyenTau> searchChuyenTau(String keyword) {
        List<ChuyenTau> chuyenTauList = new ArrayList<>();
        String query = "SELECT ct.maChuyenTau, ct.maTau, ct.gioKhoiHanh, t.maTuyenTau, tt.gaKhoiHanh, tt.gaKetThuc, " +
                "ct.soLuongHKToiDa, ct.soLuongHK " +
                "FROM ChuyenTau ct " +
                "JOIN Tau t ON ct.maTau = t.maTau " +
                "JOIN TuyenTau tt ON t.maTuyenTau = tt.maTuyenTau " +
                "WHERE ct.daBiHuy = 0 AND (ct.maChuyenTau LIKE ? OR ct.maTau LIKE ?)";

        try {
            Connection conn = DatabaseConnection.getConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                String timkiem = "%" + keyword + "%";
                pstmt.setString(1, timkiem);
                pstmt.setString(2, timkiem);

                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        ChuyenTau chuyenTau = new ChuyenTau();
                        chuyenTau.setMaChuyenTau(rs.getString("maChuyenTau"));
                        chuyenTau.setMaTau(rs.getString("maTau"));
                        chuyenTau.setGioKhoiHanh(rs.getTimestamp("gioKhoiHanh"));
                        chuyenTau.setSoLuongHKToiDa(rs.getInt("soLuongHKToiDa"));
                        chuyenTau.setSoluongHK(rs.getInt("soLuongHK"));

                        String tuyenTau = rs.getString("gaKhoiHanh") + " - " + rs.getString("gaKetThuc");
                        chuyenTau.setTuyenTau(tuyenTau);

                        chuyenTauList.add(chuyenTau);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi truy vấn searchChuyenTau: " + e.getMessage());
            e.printStackTrace();
        }
        return chuyenTauList;
    }
    // phuong thuc lay danh sach tau
    public List<String> getMaTauList() {
        List<String> maTauList = new ArrayList<>();
        String query = "SELECT maTau FROM Tau WHERE daBiXoa = 0";

        try {
            Connection conn = DatabaseConnection.getConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(query);
                 ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    maTauList.add(rs.getString("maTau"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách mã tàu: " + e.getMessage());
            e.printStackTrace();
        }
        return maTauList;
    }

    //  phương thức lấy danh sách tuyến tàu
    public List<String> getTuyenTauList() {
        List<String> tuyenTauList = new ArrayList<>();
        String query = "SELECT gaKhoiHanh, gaKetThuc FROM TuyenTau WHERE daBiXoa = 0";

        try {
            Connection conn = DatabaseConnection.getConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(query);
                 ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String tuyenTau = rs.getString("gaKhoiHanh") + " - " + rs.getString("gaKetThuc");
                    tuyenTauList.add(tuyenTau);
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách tuyến tàu: " + e.getMessage());
            e.printStackTrace();
        }
        return tuyenTauList;
    }
    public boolean updateChuyenTau(String maChuyenTau, String maTau, Timestamp gioKhoiHanh, boolean daBiHuy, int soLuongHKToiDa, int soLuongHK) {
        String procedureCall = "{call sp_CapNhatChuyenTau(?, ?, ?, ?, ?, ?)}";

        try {
            Connection conn = DatabaseConnection.getConnection();
            try (CallableStatement cstmt = conn.prepareCall(procedureCall)) {
                cstmt.setString(1, maChuyenTau);
                cstmt.setString(2, maTau);
                cstmt.setTimestamp(3, gioKhoiHanh);
                cstmt.setBoolean(4, daBiHuy);
                cstmt.setInt(5, soLuongHKToiDa);
                cstmt.setInt(6, soLuongHK);

                int rowsAffected = cstmt.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật chuyến tàu: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteChuyenTau(String maChuyenTau) {
        String query = "UPDATE ChuyenTau SET daBiHuy = 1 WHERE maChuyenTau = ?"; // xoa nhan vien khong xoa khoi data ma chuyen sang -1

        try {
            Connection conn = DatabaseConnection.getConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, maChuyenTau);
                int rowsAffected = pstmt.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi xóa chuyến tàu: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

}
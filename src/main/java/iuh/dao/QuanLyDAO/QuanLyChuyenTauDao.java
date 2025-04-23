package iuh.dao.QuanLyDAO;

import iuh.connect.DatabaseConnection;
import iuh.model.ChuyenTau;
import iuh.model.Tau;

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
                    Tau tau = new Tau();
                    tau.setMaTau(rs.getString("maTau"));
                    chuyenTau.setTau(tau);
                    chuyenTau.setGioKhoiHanh(rs.getTimestamp("gioKhoiHanh").toLocalDateTime());
                    chuyenTau.setDaBiHuy(rs.getBoolean("daBiHuy"));
                    chuyenTauList.add(chuyenTau);
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi truy vấn sp_LayDanhSachChuyenTau: " + e.getMessage());
            e.printStackTrace();
        }
        return chuyenTauList;
    }

    public boolean addChuyenTau(String maChuyenTau, String maTau, Timestamp gioKhoiHanh, boolean daBiHuy) {
        String procedureCall = "{call sp_ThemChuyenTau(?, ?, ?, ?)}";

        try {
            Connection conn = DatabaseConnection.getConnection();
            try (CallableStatement cstmt = conn.prepareCall(procedureCall)) {
                cstmt.setString(1, maChuyenTau);
                cstmt.setString(2, maTau);
                cstmt.setTimestamp(3, gioKhoiHanh);
                cstmt.setBoolean(4, daBiHuy);

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
        String query = "SELECT ct.maChuyenTau, ct.maTau, ct.gioKhoiHanh, ct.daBiHuy " +
                "FROM ChuyenTau ct " +
                "JOIN Tau t ON ct.maTau = t.maTau " +
                "JOIN TuyenTau tt ON t.maTuyenTau = tt.maTuyenTau " +
                "WHERE ct.daBiHuy = 0 AND (ct.maChuyenTau LIKE ? OR ct.maTau LIKE ? OR tt.gaKhoiHanh LIKE ? OR tt.gaKetThuc LIKE ?)";

        try {
            Connection conn = DatabaseConnection.getConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                String timkiem = "%" + keyword + "%";
                pstmt.setString(1, timkiem);
                pstmt.setString(2, timkiem);
                pstmt.setString(3, timkiem);
                pstmt.setString(4, timkiem);

                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        ChuyenTau chuyenTau = new ChuyenTau();
                        chuyenTau.setMaChuyenTau(rs.getString("maChuyenTau"));
                        Tau tau = new Tau();
                        tau.setMaTau(rs.getString("maTau"));
                        chuyenTau.setTau(tau);
                        chuyenTau.setGioKhoiHanh(rs.getTimestamp("gioKhoiHanh").toLocalDateTime());
                        chuyenTau.setDaBiHuy(rs.getBoolean("daBiHuy"));
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

    public boolean updateChuyenTau(String maChuyenTau, String maTau, Timestamp gioKhoiHanh, boolean daBiHuy) {
        String procedureCall = "{call sp_CapNhatChuyenTau(?, ?, ?, ?)}";

        try {
            Connection conn = DatabaseConnection.getConnection();
            try (CallableStatement cstmt = conn.prepareCall(procedureCall)) {
                cstmt.setString(1, maChuyenTau);
                cstmt.setString(2, maTau);
                cstmt.setTimestamp(3, gioKhoiHanh);
                cstmt.setBoolean(4, daBiHuy);

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
        String query = "UPDATE ChuyenTau SET daBiHuy = 1 WHERE maChuyenTau = ?";

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
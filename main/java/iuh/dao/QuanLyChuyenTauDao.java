package iuh.dao;

import iuh.connect.DatabaseConnection;
import iuh.model.ChuyenTau;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuanLyChuyenTauDao {

    public List<ChuyenTau> getAllChuyenTau() {
        List<ChuyenTau> chuyenTauList = new ArrayList<>();
        String query = "SELECT ct.maChuyenTau, ct.maTau, ct.gioKhoiHanh, t.maTuyenTau, tt.gaKhoiHanh, tt.gaKetThuc, " +
                "(SELECT COUNT(*) FROM ToaTau tt WHERE tt.maTau = ct.maTau) AS soLuongToa, " +
                "(SELECT COUNT(*) FROM VeTau vt WHERE vt.maChuyenTau = ct.maChuyenTau AND vt.daBiHuy = 0) AS soLuongHanhKhach " +
                "FROM ChuyenTau ct " +
                "JOIN Tau t ON ct.maTau = t.maTau " +
                "JOIN TuyenTau tt ON t.maTuyenTau = tt.maTuyenTau " +
                "WHERE ct.daBiHuy = 0"; // Chỉ lấy các chuyến tàu chưa bị hủy

        try {
            Connection conn = DatabaseConnection.getConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(query);
                 ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    ChuyenTau chuyenTau = new ChuyenTau();
                    chuyenTau.setMaChuyenTau(rs.getString("maChuyenTau"));
                    chuyenTau.setMaTau(rs.getString("maTau"));
                    chuyenTau.setGioKhoiHanh(rs.getTimestamp("gioKhoiHanh"));
                    chuyenTau.setSoLuongToa(rs.getInt("soLuongToa"));

                    String tuyenTau = rs.getString("gaKhoiHanh") + " - " + rs.getString("gaKetThuc");
                    chuyenTau.setTuyenTau(tuyenTau);

                    chuyenTau.setSoLuongHanhKhach(rs.getInt("soLuongHanhKhach"));
                    chuyenTauList.add(chuyenTau);
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi truy vấn getAllChuyenTau: " + e.getMessage());
            e.printStackTrace();
        }
        return chuyenTauList;
    }

    public List<ChuyenTau> searchChuyenTau(String keyword) {
        List<ChuyenTau> chuyenTauList = new ArrayList<>();
        String query = "SELECT ct.maChuyenTau, ct.maTau, ct.gioKhoiHanh, t.maTuyenTau, tt.gaKhoiHanh, tt.gaKetThuc, " +
                "(SELECT COUNT(*) FROM ToaTau tt WHERE tt.maTau = ct.maTau) AS soLuongToa, " +
                "(SELECT COUNT(*) FROM VeTau vt WHERE vt.maChuyenTau = ct.maChuyenTau AND vt.daBiHuy = 0) AS soLuongHanhKhach " +
                "FROM ChuyenTau ct " +
                "JOIN Tau t ON ct.maTau = t.maTau " +
                "JOIN TuyenTau tt ON t.maTuyenTau = tt.maTuyenTau " +
                "WHERE ct.daBiHuy = 0 AND (ct.maChuyenTau LIKE ? OR ct.maTau LIKE ?)";

        try {
            Connection conn = DatabaseConnection.getConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                String searchPattern = "%" + keyword + "%";
                pstmt.setString(1, searchPattern);
                pstmt.setString(2, searchPattern);

                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        ChuyenTau chuyenTau = new ChuyenTau();
                        chuyenTau.setMaChuyenTau(rs.getString("maChuyenTau"));
                        chuyenTau.setMaTau(rs.getString("maTau"));
                        chuyenTau.setGioKhoiHanh(rs.getTimestamp("gioKhoiHanh"));
                        chuyenTau.setSoLuongToa(rs.getInt("soLuongToa"));

                        String tuyenTau = rs.getString("gaKhoiHanh") + " - " + rs.getString("gaKetThuc");
                        chuyenTau.setTuyenTau(tuyenTau);

                        chuyenTau.setSoLuongHanhKhach(rs.getInt("soLuongHanhKhach"));
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
}
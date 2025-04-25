package iuh.dao.QuanLyDAO;

import iuh.connect.DatabaseConnection;
import iuh.model.HanhKhach;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuanLyHanhKhachDao {

    public List<HanhKhach> getAllHanhKhach() {
        List<HanhKhach> hanhKhachList = new ArrayList<>();
        String procedureCall = "{call sp_LayThongTinHanhKhach}";
        try {
            Connection conn = DatabaseConnection.getConnection();
            try (CallableStatement cstmt = conn.prepareCall(procedureCall);
                 ResultSet rs = cstmt.executeQuery()) {
                while (rs.next()) {
                    HanhKhach hanhKhach = new HanhKhach();
                    hanhKhach.setSoCCCD(rs.getString("soCCCD"));
                    hanhKhach.setHoTenHK(rs.getString("hoTenHK"));
                    hanhKhach.setSoDT(rs.getString("soDT"));
                    hanhKhach.setDiaChi(rs.getString("diaChi"));
                    hanhKhachList.add(hanhKhach);
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi truy vấn sp_LayThongTinHanhKhach: " + e.getMessage());
            e.printStackTrace();
        }
        return hanhKhachList;
    }

    public HanhKhach getHanhKhachByCCCD(String soCCCD) {
        HanhKhach hanhKhach = null;
        String query = "SELECT soCCCD, hoTenHK, soDT, diaChi FROM HanhKhach WHERE soCCCD = ?";
        try {
            Connection conn = DatabaseConnection.getConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, soCCCD);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        hanhKhach = new HanhKhach();
                        hanhKhach.setSoCCCD(rs.getString("soCCCD"));
                        hanhKhach.setHoTenHK(rs.getString("hoTenHK"));
                        hanhKhach.setSoDT(rs.getString("soDT"));
                        hanhKhach.setDiaChi(rs.getString("diaChi"));
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi truy vấn getHanhKhachByCCCD: " + e.getMessage());
            e.printStackTrace();
        }
        return hanhKhach;
    }

    public boolean updateHanhKhach(String soCCCD, String hoTen, String soDT, String diaChi) {
        String query = "UPDATE HanhKhach SET hoTenHK = ?, soDT = ?, diaChi = ? WHERE soCCCD = ?";
        try {
            Connection conn = DatabaseConnection.getConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, hoTen);
                pstmt.setString(2, soDT);
                pstmt.setString(3, diaChi);
                pstmt.setString(4, soCCCD);
                int rowsAffected = pstmt.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật hành khách: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<HanhKhach> searchHanhKhach(String keyword) {
        List<HanhKhach> hanhKhachList = new ArrayList<>();
        String query = "SELECT soCCCD, hoTenHK, soDT, diaChi FROM HanhKhach " +
                "WHERE soCCCD LIKE ? OR hoTenHK LIKE ? OR soDT LIKE ? OR diaChi LIKE ?";
        try {
            Connection conn = DatabaseConnection.getConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                String searchPattern = "%" + keyword + "%";
                pstmt.setString(1, searchPattern);
                pstmt.setString(2, searchPattern);
                pstmt.setString(3, searchPattern);
                pstmt.setString(4, searchPattern);
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        HanhKhach hanhKhach = new HanhKhach();
                        hanhKhach.setSoCCCD(rs.getString("soCCCD"));
                        hanhKhach.setHoTenHK(rs.getString("hoTenHK"));
                        hanhKhach.setSoDT(rs.getString("soDT"));
                        hanhKhach.setDiaChi(rs.getString("diaChi"));
                        hanhKhachList.add(hanhKhach);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi truy vấn searchHanhKhach: " + e.getMessage());
            e.printStackTrace();
        }
        return hanhKhachList;
    }
}
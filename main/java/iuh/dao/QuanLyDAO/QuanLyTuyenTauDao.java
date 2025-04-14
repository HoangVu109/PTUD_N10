package iuh.dao.QuanLyDAO;

import iuh.model.TuyenTau;
import iuh.connect.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuanLyTuyenTauDao {

    // Lấy danh sách tất cả tuyến tàu
    public List<TuyenTau> layDanhSachTuyenTau() throws SQLException {
        List<TuyenTau> danhSachTuyenTau = new ArrayList<>();
        String query = "SELECT * FROM TuyenTau";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                TuyenTau tuyenTau = new TuyenTau();
                tuyenTau.setMaTuyenTau(rs.getString("maTuyenTau"));
                tuyenTau.setGaKhoiHanh(rs.getString("gaKhoiHanh"));
                tuyenTau.setGaKetThuc(rs.getString("gaKetThuc"));
                tuyenTau.setDaBiXoa(rs.getBoolean("daBiXoa"));
                danhSachTuyenTau.add(tuyenTau);
            }
        }
        return danhSachTuyenTau;
    }

    // Thêm một tuyến tàu mới
    public void themTuyenTau(TuyenTau tuyenTau) throws SQLException {
        String query = "INSERT INTO TuyenTau (maTuyenTau, gaKhoiHanh, gaKetThuc, daBiXoa) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, tuyenTau.getMaTuyenTau());
            stmt.setString(2, tuyenTau.getGaKhoiHanh());
            stmt.setString(3, tuyenTau.getGaKetThuc());
            stmt.setBoolean(4, tuyenTau.isDaBiXoa());
            stmt.executeUpdate();
        }
    }

    // Sửa thông tin một tuyến tàu
    public void suaTuyenTau(TuyenTau tuyenTau) throws SQLException {
        String query = "UPDATE TuyenTau SET gaKhoiHanh = ?, gaKetThuc = ? WHERE maTuyenTau = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, tuyenTau.getGaKhoiHanh());
            stmt.setString(2, tuyenTau.getGaKetThuc());
            stmt.setString(3, tuyenTau.getMaTuyenTau());
            stmt.executeUpdate();
        }
    }

    // Xóa tuyến tàu (đánh dấu daBiXoa = true)
    public void xoaTuyenTau(String maTuyenTau) throws SQLException {
        String query = "UPDATE TuyenTau SET daBiXoa = ? WHERE maTuyenTau = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setBoolean(1, true);
            stmt.setString(2, maTuyenTau);
            stmt.executeUpdate();
        }
    }

    // Khôi phục tuyến tàu (đánh dấu daBiXoa = false)
    public void khoiPhucTuyenTau(String maTuyenTau) throws SQLException {
        String query = "UPDATE TuyenTau SET daBiXoa = ? WHERE maTuyenTau = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setBoolean(1, false);
            stmt.setString(2, maTuyenTau);
            stmt.executeUpdate();
        }
    }

    // Tìm kiếm tuyến tàu theo từ khóa
    public List<TuyenTau> timKiemTuyenTau(String keyword) throws SQLException {
        List<TuyenTau> danhSachTuyenTau = new ArrayList<>();
        String query = "SELECT * FROM TuyenTau WHERE maTuyenTau LIKE ? OR gaKhoiHanh LIKE ? OR gaKetThuc LIKE ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            String searchPattern = "%" + keyword + "%";
            stmt.setString(1, searchPattern);
            stmt.setString(2, searchPattern);
            stmt.setString(3, searchPattern);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    TuyenTau tuyenTau = new TuyenTau();
                    tuyenTau.setMaTuyenTau(rs.getString("maTuyenTau"));
                    tuyenTau.setGaKhoiHanh(rs.getString("gaKhoiHanh"));
                    tuyenTau.setGaKetThuc(rs.getString("gaKetThuc"));
                    tuyenTau.setDaBiXoa(rs.getBoolean("daBiXoa"));
                    danhSachTuyenTau.add(tuyenTau);
                }
            }
        }
        return danhSachTuyenTau;
    }
}
//package iuh.dao.NhanVienDAO;
//
//import iuh.connect.DatabaseConnection;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//public class BanVeDAO {
//
//    // Lấy danh sách tất cả tuyến tàu chưa bị xóa
//    public List<TuyenTau> layDanhSachTuyenTau() throws SQLException {
//        List<TuyenTau> danhSachTuyenTau = new ArrayList<>();
//        String query = "SELECT * FROM TuyenTau WHERE daBiXoa = 0";
//
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(query);
//             ResultSet rs = stmt.executeQuery()) {
//
//            while (rs.next()) {
//                TuyenTau tuyenTau = new TuyenTau();
//                tuyenTau.setMaTuyenTau(rs.getString("maTuyenTau"));
//                tuyenTau.setGaKhoiHanh(rs.getString("gaKhoiHanh"));
//                tuyenTau.setGaKetThuc(rs.getString("gaKetThuc"));
//                tuyenTau.setDaBiXoa(rs.getBoolean("daBiXoa"));
//                danhSachTuyenTau.add(tuyenTau);
//            }
//        }
//        return danhSachTuyenTau;
//    }
//
//    // Lấy danh sách chuyến tàu theo mã tuyến tàu và ngày đi (nếu có)
//    public List<ChuyenTau> layDanhSachChuyenTauTheoTuyen(String maTuyenTau, Date ngayDi) throws SQLException {
//        List<ChuyenTau> danhSachChuyenTau = new ArrayList<>();
//        String query = "SELECT ct.* FROM ChuyenTau ct " +
//                "JOIN Tau t ON ct.maTau = t.maTau " +
//                "WHERE t.maTuyenTau = ? AND ct.daBiHuy = 0";
//
//        if (ngayDi != null) {
//            query += " AND CAST(ct.gioKhoiHanh AS DATE) = ?";
//        }
//
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//
//            stmt.setString(1, maTuyenTau);
//            if (ngayDi != null) {
//                stmt.setDate(2, new java.sql.Date(ngayDi.getTime()));
//            }
//
//            try (ResultSet rs = stmt.executeQuery()) {
//                while (rs.next()) {
//                    ChuyenTau chuyenTau = new ChuyenTau();
//                    chuyenTau.setMaChuyenTau(rs.getString("maChuyenTau"));
//                    chuyenTau.setMaTau(rs.getString("maTau"));
//                    chuyenTau.setGioKhoiHanh(rs.getTimestamp("gioKhoiHanh"));
//                    chuyenTau.setDaBiHuy(rs.getBoolean("daBiHuy"));
//                    chuyenTau.setSoLuongHKToiDa(rs.getInt("soLuongHKToiDa"));
//                    chuyenTau.setSoluongHK(rs.getInt("soLuongHK"));
//                    danhSachChuyenTau.add(chuyenTau);
//                }
//            }
//        }
//        return danhSachChuyenTau;
//    }
//}
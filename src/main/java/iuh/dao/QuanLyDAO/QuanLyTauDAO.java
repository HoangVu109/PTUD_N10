//package iuh.dao.QuanLyDAO;
//
//import iuh.connect.DatabaseConnection;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class QuanLyTauDAO {
//
//    // Lấy danh sách tất cả tàu
//    public List<Tau> layDanhSachTau() throws SQLException {
//        List<Tau> danhSachTau = new ArrayList<>();
//        String query = "SELECT * FROM Tau";
//
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(query);
//             ResultSet rs = stmt.executeQuery()) {
//
//            while (rs.next()) {
//                Tau tau = new Tau();
//                tau.setMaTau(rs.getString("maTau"));
//                tau.setMaTuyenTau(rs.getString("maTuyenTau"));
//                tau.setDaBiXoa(rs.getBoolean("daBiXoa"));
//                danhSachTau.add(tau);
//            }
//        }
//        return danhSachTau;
//    }
//
//    // Thêm một tàu mới
//    public void themTau(Tau tau) throws SQLException {
//        String query = "INSERT INTO Tau (maTau, uyenTau, daBiXoa) VALUES (?, ?, ?)";
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//            stmt.setString(1, tau.getMaTau());
//            stmt.setString(2, tau.getMaTuyenTau());
//            stmt.setBoolean(3, tau.isDaBiXoa());
//            stmt.executeUpdate();
//        }
//    }
//
//    // Sửa thông tin một tàu
//    public void suaTau(Tau tau) throws SQLException {
//        String query = "UPDATE Tau SET mauyenTau = ? WHERE maTau = ?";
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//            stmt.setString(1, tau.getMaTuyenTau());
//            stmt.setString(2, tau.getMaTau());
//            stmt.executeUpdate();
//        }
//    }
//
//    // Xóa tàu (đánh dấu daBiXoa = true)
//    public void xoaTau(String maTau) throws SQLException {
//        String query = "UPDATE Tau SET daBiXoa = ? WHERE maTau = ?";
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//            stmt.setBoolean(1, true);
//            stmt.setString(2, maTau);
//            stmt.executeUpdate();
//        }
//    }
//
//    // Khôi phục tàu (đánh dấu daBiXoa = false)
//    public void khoiPhucTau(String maTau) throws SQLException {
//        String query = "UPDATE Tau SET daBiXoa = ? WHERE maTau = ?";
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//            stmt.setBoolean(1, false);
//            stmt.setString(2, maTau);
//            stmt.executeUpdate();
//        }
//    }
//
//    // Tìm kiếm tàu theo từ khóa
//    public List<Tau> timKiemTau(String keyword) throws SQLException {
//        List<Tau> danhSachTau = new ArrayList<>();
//        String query = "SELECT * FROM Tau WHERE maTau LIKE ? OR maTuyenTau LIKE ?";
//
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//            String searchPattern = "%" + keyword + "%";
//            stmt.setString(1, searchPattern);
//            stmt.setString(2, searchPattern);
//
//            try (ResultSet rs = stmt.executeQuery()) {
//                while (rs.next()) {
//                    Tau tau = new Tau();
//                    tau.setMaTau(rs.getString("maTau"));
//                    tau.setMaTuyenTau(rs.getString("mauyenTau"));
//                    tau.setDaBiXoa(rs.getBoolean("daBiXoa"));
//                    danhSachTau.add(tau);
//                }
//            }
//        }
//        return danhSachTau;
//    }
//}
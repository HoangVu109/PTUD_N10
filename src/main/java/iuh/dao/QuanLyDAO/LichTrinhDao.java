package iuh.dao.QuanLyDAO;

import iuh.connect.DatabaseConnection;
import iuh.model.ChuyenTau;
import iuh.model.Tau;
import iuh.model.TuyenTau;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LichTrinhDao {

    public List<ChuyenTau> getActiveChuyenTau() {
        List<ChuyenTau> chuyenTauList = new ArrayList<>();
        String procedureCall = "{call sp_LayLichTrinhChuyenTau}";

        try {
            Connection conn = DatabaseConnection.getConnection();
            try (CallableStatement cstmt = conn.prepareCall(procedureCall);
                 ResultSet rs = cstmt.executeQuery()) {

                while (rs.next()) {
                    ChuyenTau chuyenTau = createChuyenTauFromResultSet(rs);
                    chuyenTauList.add(chuyenTau);
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi truy vấn sp_LayLichTrinhChuyenTau: " + e.getMessage());
            e.printStackTrace();
        }
        return chuyenTauList;
    }

    public List<ChuyenTau> searchActiveChuyenTau(String keyword) {
        List<ChuyenTau> chuyenTauList = new ArrayList<>();
        String query = "SELECT ct.maChuyenTau AS 'Mã chuyến tàu', ct.maTau AS 'Mã tàu', " +
                "tt.gaKhoiHanh AS 'Ga khởi hành', tt.gaKetThuc AS 'Ga kết thúc', " +
                "ct.gioKhoiHanh AS 'Ngày giờ khởi hành' " +
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
                        ChuyenTau chuyenTau = createChuyenTauFromResultSet(rs);
                        chuyenTauList.add(chuyenTau);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi truy vấn searchActiveChuyenTau: " + e.getMessage());
            e.printStackTrace();
        }
        return chuyenTauList;
    }

    private ChuyenTau createChuyenTauFromResultSet(ResultSet rs) throws SQLException {
        ChuyenTau chuyenTau = new ChuyenTau();
        chuyenTau.setMaChuyenTau(rs.getString("Mã chuyến tàu"));
        chuyenTau.setGioKhoiHanh(rs.getTimestamp("Ngày giờ khởi hành").toLocalDateTime());
        chuyenTau.setDaBiHuy(false); // Since the stored procedure only returns non-canceled trips

        Tau tau = new Tau();
        tau.setMaTau(rs.getString("Mã tàu"));

        TuyenTau tuyenTau = new TuyenTau();
        tuyenTau.setGaKhoiHanh(rs.getString("Ga khởi hành"));
        tuyenTau.setGaKetThuc(rs.getString("Ga kết thúc"));
        // Note: maTuyenTau is not provided by the stored procedure, so it will be null
        tuyenTau.setMaTuyenTau(null);

        tau.setTuyenTau(tuyenTau);
        chuyenTau.setTau(tau);

        return chuyenTau;
    }
}
package iuh.dao.QuanLyDAO;

import iuh.connect.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoanhThuDao {
    public static class DoanhThu {
        private String thoiDiem;
        private double doanhThu; // Revenue in millions
        private String loaiThoiGian;

        public DoanhThu(String thoiDiem, double doanhThu, String loaiThoiGian) {
            this.thoiDiem = thoiDiem;
            this.doanhThu = doanhThu;
            this.loaiThoiGian = loaiThoiGian;
        }

        public String getThoiDiem() {
            return thoiDiem;
        }

        public double getDoanhThu() {
            return doanhThu;
        }

        public String getLoaiThoiGian() {
            return loaiThoiGian;
        }
    }

    public List<DoanhThu> getDoanhThuVe(String loaiThoiGian, Date selectedDate, Integer selectedYear) {
        List<DoanhThu> doanhThuList = new ArrayList<>();
        String procedureCall = "{call sp_LayDoanhThuVe(?,?,?)}";

        try {
            Connection conn = DatabaseConnection.getConnection();
            try (CallableStatement cstmt = conn.prepareCall(procedureCall)) {
                cstmt.setString(1, loaiThoiGian); // Time period
                if (selectedDate != null) {
                    cstmt.setDate(2, selectedDate); // Selected date for daily
                } else {
                    cstmt.setNull(2, Types.DATE);
                }
                if (selectedYear != null) {
                    cstmt.setInt(3, selectedYear); // Selected year for monthly
                } else {
                    cstmt.setNull(3, Types.INTEGER);
                }
                try (ResultSet rs = cstmt.executeQuery()) {
                    while (rs.next()) {
                        String thoiDiem = rs.getString("ThoiDiem");
                        double doanhThu = rs.getDouble("DoanhThu");
                        String loai = rs.getString("LoaiThoiGian");
                        doanhThuList.add(new DoanhThu(thoiDiem, doanhThu, loai));
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi truy vấn sp_LayDoanhThuVe: " + e.getMessage());
            e.printStackTrace();
        }
        return doanhThuList;
    }
}
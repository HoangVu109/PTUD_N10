package iuh.dao.QuanLyDao;

import iuh.connect.DatabaseConnection;
import iuh.dao.Dao;
import iuh.model.HanhKhach;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HanhKhachDao {
    private Connection connection = DatabaseConnection.getConnection();

    public HanhKhachDao(Connection connection) throws SQLException {
        this.connection = connection;
    }

    // Thêm hành khách
    public boolean themHanhKhach(HanhKhach hk) throws SQLException {
        String sql = "{CALL sp_ThemHanhKhach(?, ?, ?, ?)}";
        try (CallableStatement cs = connection.prepareCall(sql)) {
            cs.setString(1, hk.getSoCCCD());
            cs.setNString(2, hk.getHoTenHK());
            cs.setString(3, hk.getSoDT());
            cs.setNString(4, hk.getDiaChi());
            return cs.executeUpdate() > 0;
        }
    }

    // Cập nhật hành khách
    public boolean capNhatHanhKhach(HanhKhach hk) throws SQLException {
        String sql = "{CALL sp_CapNhatHanhKhach(?, ?, ?, ?)}";
        try (CallableStatement cs = connection.prepareCall(sql)) {
            cs.setString(1, hk.getSoCCCD());
            cs.setNString(2, hk.getHoTenHK());
            cs.setString(3, hk.getSoDT());
            cs.setNString(4, hk.getDiaChi());
            return cs.executeUpdate() > 0;
        }
    }

    // Xóa hành khách theo CCCD
    public boolean xoaHanhKhach(String soCCCD) throws SQLException {
        String sql = "{CALL sp_XoaHanhKhach(?)}";
        try (CallableStatement cs = connection.prepareCall(sql)) {
            cs.setString(1, soCCCD);
            return cs.executeUpdate() > 0;
        }
    }

    // Tìm hành khách theo CCCD
    public HanhKhach timTheoCCCD(String soCCCD) throws SQLException {
        String sql = "{CALL sp_TimHanhKhachTheoCCCD(?)}";
        try (CallableStatement cs = connection.prepareCall(sql)) {
            cs.setString(1, soCCCD);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToHanhKhach(rs);
                }
            }
        }
        return null;
    }

    // Tìm hành khách theo số điện thoại
    public HanhKhach timTheoSoDT(String soDT) throws SQLException {
        String sql = "{CALL sp_TimHanhKhachTheoSDT(?)}";
        try (CallableStatement cs = connection.prepareCall(sql)) {
            cs.setString(1, soDT);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToHanhKhach(rs);
                }
            }
        }
        return null;
    }

    // Lấy danh sách tất cả hành khách
    public List<HanhKhach> layDanhSachHanhKhach() throws SQLException {
        List<HanhKhach> ds = new ArrayList<>();
        String sql = "{CALL sp_LayDanhSachHanhKhach()}";
        try (CallableStatement cs = connection.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                ds.add(mapResultSetToHanhKhach(rs));
            }
        }
        return ds;
    }

    // Helper method để ánh xạ từ ResultSet sang đối tượng HanhKhach
    private HanhKhach mapResultSetToHanhKhach(ResultSet rs) throws SQLException {
        HanhKhach hk = new HanhKhach();
        hk.setSoCCCD(rs.getString("soCCCD"));
        hk.setHoTenHK(rs.getNString("hoTenHK"));
        hk.setSoDT(rs.getString("soDT"));
        hk.setDiaChi(rs.getNString("diaChi"));
        return hk;
    }
}

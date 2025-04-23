package iuh.model;

import java.time.LocalDate;

public class HoaDon {

    private String maHoaDon;
    private NhanVien nhanVien;
    private LocalDate thoiDiemXuat;
    private String hoTenNguoiMua;
    private double thanhTien;
    private String soCCCDNguoiMua;
    private String soDTNguoiMua;

    // Constructor rỗng
    public HoaDon() {
    }

    // Constructor đầy đủ tham số
    public HoaDon(String maHoaDon, NhanVien nhanVien, LocalDate thoiDiemXuat, String hoTenNguoiMua,
                  double thanhTien, String soCCCDNguoiMua, String soDTNguoiMua) {
        this.maHoaDon = maHoaDon;
        this.nhanVien = nhanVien;
        this.thoiDiemXuat = thoiDiemXuat;
        this.hoTenNguoiMua = hoTenNguoiMua;
        this.thanhTien = thanhTien;
        this.soCCCDNguoiMua = soCCCDNguoiMua;
        this.soDTNguoiMua = soDTNguoiMua;
    }

    // Getter và Setter
    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public LocalDate getThoiDiemXuat() {
        return thoiDiemXuat;
    }

    public void setThoiDiemXuat(LocalDate thoiDiemXuat) {
        this.thoiDiemXuat = thoiDiemXuat;
    }

    public String getHoTenNguoiMua() {
        return hoTenNguoiMua;
    }

    public void setHoTenNguoiMua(String hoTenNguoiMua) {
        this.hoTenNguoiMua = hoTenNguoiMua;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    public String getSoCCCDNguoiMua() {
        return soCCCDNguoiMua;
    }

    public void setSoCCCDNguoiMua(String soCCCDNguoiMua) {
        this.soCCCDNguoiMua = soCCCDNguoiMua;
    }

    public String getSoDTNguoiMua() {
        return soDTNguoiMua;
    }

    public void setSoDTNguoiMua(String soDTNguoiMua) {
        this.soDTNguoiMua = soDTNguoiMua;
    }

    // Phương thức toString
    @Override
    public String toString() {
        return "HoaDon{" +
                "maHoaDon='" + maHoaDon + '\'' +
                ", nhanVien=" + (nhanVien != null ? nhanVien.getMaSoNV() : "null") +
                ", thoiDiemXuat=" + thoiDiemXuat +
                ", hoTenNguoiMua='" + hoTenNguoiMua + '\'' +
                ", thanhTien=" + thanhTien +
                ", soCCCDNguoiMua='" + soCCCDNguoiMua + '\'' +
                ", soDTNguoiMua='" + soDTNguoiMua + '\'' +
                '}';
    }
}
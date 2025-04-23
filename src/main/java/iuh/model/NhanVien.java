package iuh.model;

import java.time.LocalDate;
import java.time.Period;

public class NhanVien {
    private String maSoNV;
    private String soCCCD;
    private String hoTenNV;
    private LocalDate ngaySinh;
    private int gioiTinh;
    private String soDT;
    private String diaChi;
    private int chucVu;
    private String matKhau;
    private boolean daNghiViec;

    // Constructor rỗng
    public NhanVien() {
    }

    // Constructor đầy đủ tham số
    public NhanVien(String maSoNV, String soCCCD, String ho_csv, LocalDate ngaySinh, int gioiTinh, String soDT, String diaChi, int chucVu, String matKhau, boolean daNghiViec) {
        this.maSoNV = maSoNV;
        this.soCCCD = soCCCD;
        this.hoTenNV = hoTenNV;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.soDT = soDT;
        this.diaChi = diaChi;
        this.chucVu = chucVu;
        this.matKhau = matKhau;
        this.daNghiViec = daNghiViec;
    }

    // Getter và Setter
    public String getMaSoNV() {
        return maSoNV;
    }

    public void setMaSoNV(String maSoNV) {
        this.maSoNV = maSoNV;
    }

    public String getSoCCCD() {
        return soCCCD;
    }

    public void setSoCCCD(String soCCCD) {
        this.soCCCD = soCCCD;
    }

    public String getHoTenNV() {
        return hoTenNV;
    }

    public void setHoTenNV(String hoTenNV) {
        this.hoTenNV = hoTenNV;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi; // Địa chỉ có thể null
    }

    public int getChucVu() {
        return chucVu;
    }

    public void setChucVu(int chucVu) {
        this.chucVu = chucVu;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public boolean isDaNghiViec() {
        return daNghiViec;
    }

    public void setDaNghiViec(boolean daNghiViec) {
        this.daNghiViec = daNghiViec;
    }

    // Phương thức toString
    @Override
    public String toString() {
        return "NhanVien{" +
                "maSoNV='" + maSoNV + '\'' +
                ", soCCCD='" + soCCCD + '\'' +
                ", hoTenNV='" + hoTenNV + '\'' +
                ", ngaySinh=" + ngaySinh +
                ", gioiTinh=" + (gioiTinh == 0 ? "Nam" : "Nữ") +
                ", soDT='" + soDT + '\'' +
                ", diaChi='" + (diaChi != null ? diaChi : "") + '\'' +
                ", chucVu=" + (chucVu == 0 ? "Quản lý" : "Nhân viên bán vé") +
                ", matKhau='" + matKhau + '\'' +
                ", daNghiViec=" + daNghiViec +
                '}';
    }
}

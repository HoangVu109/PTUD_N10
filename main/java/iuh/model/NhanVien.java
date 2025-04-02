package iuh.model;

import java.util.Date;

public class NhanVien {
    private String maSoNV;
    private String hoTenNV;
    private int chucVu;
    private int gioiTinh;
    private String soCCCD;
    private Date ngaySinh;
    private String diaChi;
    private String soDT;
    private String matKhau;
    private boolean daNghiViec;

    // Constructors không có gì
    public NhanVien() {
    }

    //Constructors day du
    public NhanVien(String maSoNV, String hoTenNV, int chucVu, int gioiTinh, String soCCCD, Date ngaySinh, String diaChi, String soDT, String matKhau,int tinhTrang) {
        this.maSoNV = maSoNV;
        this.hoTenNV = hoTenNV;
        this.chucVu = chucVu;
        this.gioiTinh = gioiTinh;
        this.soCCCD = soCCCD;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.soDT = soDT;
        this.matKhau = matKhau;
        //
    }

    public String getMaSoNV() {
        return maSoNV;
    }

    public void setMaSoNV(String maSoNV) {
        this.maSoNV = maSoNV;
    }

    public String getHoTenNV() {
        return hoTenNV;
    }

    public void setHoTenNV(String hoTenNV) {
        this.hoTenNV = hoTenNV;
    }

    public int getChucVu() {
        return chucVu;
    }

    public void setChucVu(int chucVu) {
        this.chucVu = chucVu;
    }

    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSoCCCD() {
        return soCCCD;
    }

    public void setSoCCCD(String soCCCD) {
        this.soCCCD = soCCCD;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
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
}
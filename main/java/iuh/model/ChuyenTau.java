package iuh.model;

import java.sql.Timestamp;

public class ChuyenTau {
    private String maChuyenTau;
    private String maTau;
    private Timestamp gioKhoiHanh;
    private int soLuongToa;
    private String tuyenTau;
    private int soLuongHanhKhach;

    // Getters and Setters
    public String getMaChuyenTau() {
        return maChuyenTau;
    }

    public void setMaChuyenTau(String maChuyenTau) {
        this.maChuyenTau = maChuyenTau;
    }

    public String getMaTau() {
        return maTau;
    }

    public void setMaTau(String maTau) {
        this.maTau = maTau;
    }

    public Timestamp getGioKhoiHanh() {
        return gioKhoiHanh;
    }

    public void setGioKhoiHanh(Timestamp gioKhoiHanh) {
        this.gioKhoiHanh = gioKhoiHanh;
    }

    public int getSoLuongToa() {
        return soLuongToa;
    }

    public void setSoLuongToa(int soLuongToa) {
        this.soLuongToa = soLuongToa;
    }

    public String getTuyenTau() {
        return tuyenTau;
    }

    public void setTuyenTau(String tuyenTau) {
        this.tuyenTau = tuyenTau;
    }

    public int getSoLuongHanhKhach() {
        return soLuongHanhKhach;
    }

    public void setSoLuongHanhKhach(int soLuongHanhKhach) {
        this.soLuongHanhKhach = soLuongHanhKhach;
    }
    public ChuyenTau() {
    }

    public ChuyenTau(String maChuyenTau, String maTau, int soLuongToa, int soLuongHanhKhach, String tuyenTau, Timestamp gioKhoiHanh) {
        this.maChuyenTau = maChuyenTau;
        this.maTau = maTau;
        this.soLuongToa = soLuongToa;
        this.soLuongHanhKhach = soLuongHanhKhach;
        this.tuyenTau = tuyenTau;
        this.gioKhoiHanh = gioKhoiHanh;
    }

    @Override
    public String toString() {
        return "ChuyenTau{" +
                "maChuyenTau='" + maChuyenTau + '\'' +
                ", maTau='" + maTau + '\'' +
                ", gioKhoiHanh=" + gioKhoiHanh +
                ", soLuongToa=" + soLuongToa +
                ", tuyenTau='" + tuyenTau + '\'' +
                ", soLuongHanhKhach=" + soLuongHanhKhach +
                '}';
    }
}
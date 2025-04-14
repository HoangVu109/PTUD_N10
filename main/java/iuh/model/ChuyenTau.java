package iuh.model;

import java.sql.Timestamp;

public class ChuyenTau {
    private String maChuyenTau;
    private String maTau;
    private Timestamp gioKhoiHanh;
    private String tuyenTau;
    private boolean daBiHuy ;
    private int soLuongHKToiDa;
    private int soluongHK;


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

    public String getTuyenTau() {
        return tuyenTau;
    }

    public void setTuyenTau(String tuyenTau) {
        this.tuyenTau = tuyenTau;
    }

    public ChuyenTau() {
    }

    public int getSoluongHK() {
        return soluongHK;
    }

    public void setSoluongHK(int soluonngHK) {
        this.soluongHK = soluonngHK;
    }

    public int getSoLuongHKToiDa() {
        return soLuongHKToiDa;
    }

    public void setSoLuongHKToiDa(int soLuongHKToiDa) {
        this.soLuongHKToiDa = soLuongHKToiDa;
    }

    public boolean isDaBiHuy() {
        return daBiHuy;
    }

    public void setDaBiHuy(boolean daBiHuy) {
        this.daBiHuy = daBiHuy;
    }

    @Override
    public String toString() {
        return "ChuyenTau{" +
                "maChuyenTau='" + maChuyenTau + '\'' +
                ", maTau='" + maTau + '\'' +
                ", gioKhoiHanh=" + gioKhoiHanh +
                ", tuyenTau='" + tuyenTau + '\'' +
                ", daBiHuy=" + daBiHuy +
                ", soLuongHKToiDa=" + soLuongHKToiDa +
                ", soluongHK=" + soluongHK +
                '}';
    }
}
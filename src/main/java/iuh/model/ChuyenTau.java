package iuh.model;

import java.time.LocalDateTime;

public class ChuyenTau {

    private String maChuyenTau;
    private Tau tau;
    private LocalDateTime gioKhoiHanh;
    private boolean daBiHuy;

    // Constructor rỗng
    public ChuyenTau() {
    }

    // Constructor đầy đủ tham số
    public ChuyenTau(String maChuyenTau, Tau tau, LocalDateTime gioKhoiHanh, boolean daBiHuy) {
        this.maChuyenTau = maChuyenTau;
        this.tau = tau;
        this.gioKhoiHanh = gioKhoiHanh;
        this.daBiHuy = daBiHuy;
    }

    // Getter và Setter
    public String getMaChuyenTau() {
        return maChuyenTau;
    }

    public void setMaChuyenTau(String maChuyenTau) {
        this.maChuyenTau = maChuyenTau;
    }

    public Tau getTau() {
        return tau;
    }

    public void setTau(Tau tau) {
        this.tau = tau;
    }

    public LocalDateTime getGioKhoiHanh() {
        return gioKhoiHanh;
    }

    public void setGioKhoiHanh(LocalDateTime gioKhoiHanh) {
        this.gioKhoiHanh = gioKhoiHanh;
    }

    public boolean isDaBiHuy() {
        return daBiHuy;
    }

    public void setDaBiHuy(boolean daBiHuy) {
        this.daBiHuy = daBiHuy;
    }

    // Phương thức toString
    @Override
    public String toString() {
        return "ChuyenTau{" +
                "maChuyenTau='" + maChuyenTau + '\'' +
                ", tau=" + (tau != null ? tau.getMaTau() : "null") +
                ", gioKhoiHanh=" + gioKhoiHanh +
                ", daBiHuy=" + daBiHuy +
                '}';
    }
}
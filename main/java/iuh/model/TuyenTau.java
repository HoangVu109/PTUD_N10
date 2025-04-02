package iuh.model;

public class TuyenTau {
    private String maTuyenTau;
    private String gaKhoiHanh;
    private String gaKetThuc;
    private boolean daBiXoa;

    // Getters and Setters
    public String getMaTuyenTau() {
        return maTuyenTau;
    }

    public void setMaTuyenTau(String maTuyenTau) {
        this.maTuyenTau = maTuyenTau;
    }

    public String getGaKhoiHanh() {
        return gaKhoiHanh;
    }

    public void setGaKhoiHanh(String gaKhoiHanh) {
        this.gaKhoiHanh = gaKhoiHanh;
    }

    public String getGaKetThuc() {
        return gaKetThuc;
    }

    public void setGaKetThuc(String gaKetThuc) {
        this.gaKetThuc = gaKetThuc;
    }

    public boolean isDaBiXoa() {
        return daBiXoa;
    }

    public void setDaBiXoa(boolean daBiXoa) {
        this.daBiXoa = daBiXoa;
    }

    @Override
    public String toString() {
        return "TuyenTau{" +
                "maTuyenTau='" + maTuyenTau + '\'' +
                ", gaKhoiHanh='" + gaKhoiHanh + '\'' +
                ", gaKetThuc='" + gaKetThuc + '\'' +
                ", daBiXoa=" + daBiXoa +
                '}';
    }
}

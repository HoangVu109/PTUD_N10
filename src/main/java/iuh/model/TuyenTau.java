package iuh.model;

public class TuyenTau {

    private String maTuyenTau;
    private String gaKhoiHanh;
    private String gaKetThuc;
    private boolean daBiXoa;

    // Constructor rỗng
    public TuyenTau() {
    }

    // Constructor đầy đủ tham số
    public TuyenTau(String maTuyenTau, String gaKhoiHanh, String gaKetThuc, boolean daBiXoa) {
        this.maTuyenTau = maTuyenTau;
        this.gaKhoiHanh = gaKhoiHanh;
        this.gaKetThuc = gaKetThuc;
        this.daBiXoa = daBiXoa;
    }

    // Getter và Setter
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

    // Phương thức toString
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
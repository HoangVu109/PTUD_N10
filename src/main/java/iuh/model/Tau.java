package iuh.model;

public class Tau {

    private String maTau;
    private TuyenTau tuyenTau;
    private boolean daBiXoa;

    // Constructor rỗng
    public Tau() {
    }

    // Constructor đầy đủ tham số
    public Tau(String maTau, TuyenTau tuyenTau, boolean daBiXoa) {
        this.maTau = maTau;
        this.tuyenTau = tuyenTau;
        this.daBiXoa = daBiXoa;
    }

    // Getter và Setter
    public String getMaTau() {
        return maTau;
    }

    public void setMaTau(String maTau) {
        this.maTau = maTau;
    }

    public TuyenTau getTuyenTau() {
        return tuyenTau;
    }

    public void setTuyenTau(TuyenTau tuyenTau) {
        this.tuyenTau = tuyenTau;
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
        return "Tau{" +
                "maTau='" + maTau + '\'' +
                ", tuyenTau=" + (tuyenTau != null ? tuyenTau.getMaTuyenTau() : "null") +
                ", daBiXoa=" + daBiXoa +
                '}';
    }
}
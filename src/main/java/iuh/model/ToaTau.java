package iuh.model;

public class ToaTau {

    private ChuyenTau chuyenTau;
    private int soToa;
    private LoaiToaTau loaiToaTau;

    // Constructor rỗng
    public ToaTau() {
    }

    // Constructor đầy đủ tham số
    public ToaTau(ChuyenTau chuyenTau, int soToa, LoaiToaTau loaiToaTau) {
        this.chuyenTau = chuyenTau;
        this.soToa = soToa;
        this.loaiToaTau = loaiToaTau;
    }

    // Getter và Setter
    public ChuyenTau getChuyenTau() {
        return chuyenTau;
    }

    public void setChuyenTau(ChuyenTau chuyenTau) {
        this.chuyenTau = chuyenTau;
    }

    public int getSoToa() {
        return soToa;
    }

    public void setSoToa(int soToa) {
        this.soToa = soToa;
    }

    public LoaiToaTau getLoaiToaTau() {
        return loaiToaTau;
    }

    public void setLoaiToaTau(LoaiToaTau loaiToaTau) {
        this.loaiToaTau = loaiToaTau;
    }

    // Phương thức toString
    @Override
    public String toString() {
        return "ToaTau{" +
                "chuyenTau=" + (chuyenTau != null ? chuyenTau.getMaChuyenTau() : "null") +
                ", soToa=" + soToa +
                ", loaiToaTau=" + (loaiToaTau != null ? loaiToaTau.getMaLoaiToaTau() : "null") +
                '}';
    }
}

package iuh.model;

public class ViTri {

    private ToaTau toaTau;
    private int soViTri;
    private LoaiViTri loaiViTri;
    private boolean daDatCho;

    // Constructor rỗng
    public ViTri() {
    }

    // Constructor đầy đủ tham số
    public ViTri(ToaTau toaTau, int soViTri, LoaiViTri loaiViTri, boolean daDatCho) {
        this.toaTau = toaTau;
        this.soViTri = soViTri;
        this.loaiViTri = loaiViTri;
        this.daDatCho = daDatCho;
    }

    // Getter và Setter
    public ToaTau getToaTau() {
        return toaTau;
    }

    public void setToaTau(ToaTau toaTau) {
        this.toaTau = toaTau;
    }

    public int getSoViTri() {
        return soViTri;
    }

    public void setSoViTri(int soViTri) {
        this.soViTri = soViTri;
    }

    public LoaiViTri getLoaiViTri() {
        return loaiViTri;
    }

    public void setLoaiViTri(LoaiViTri loaiViTri) {
        this.loaiViTri = loaiViTri;
    }

    public boolean isDaDatCho() {
        return daDatCho;
    }

    public void setDaDatCho(boolean daDatCho) {
        this.daDatCho = daDatCho;
    }

    // Phương thức toString
    @Override
    public String toString() {
        return "ViTri{" +
                "toaTau=" + (toaTau != null ? "maChuyenTau=" + toaTau.getChuyenTau().getMaChuyenTau() + ", soToa=" + toaTau.getSoToa() : "null") +
                ", soViTri=" + soViTri +
                ", loaiViTri=" + (loaiViTri != null ? loaiViTri.getMaLoaiVT() : "null") +
                ", daDatCho=" + daDatCho +
                '}';
    }
}
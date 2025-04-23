package iuh.model;

public class ThongTinChuyenTau {

    private ChuyenTau chuyenTau;
    private LoaiViTri loaiViTri;
    private double giaVe;

    // Constructor rỗng
    public ThongTinChuyenTau() {
    }

    // Constructor đầy đủ tham số
    public ThongTinChuyenTau(ChuyenTau chuyenTau, LoaiViTri loaiViTri, double giaVe) {
        this.chuyenTau = chuyenTau;
        this.loaiViTri = loaiViTri;
        this.giaVe = giaVe;
    }

    // Getter và Setter
    public ChuyenTau getChuyenTau() {
        return chuyenTau;
    }

    public void setChuyenTau(ChuyenTau chuyenTau) {
        this.chuyenTau = chuyenTau;
    }

    public LoaiViTri getLoaiViTri() {
        return loaiViTri;
    }

    public void setLoaiViTri(LoaiViTri loaiViTri) {
        this.loaiViTri = loaiViTri;
    }

    public double getGiaVe() {
        return giaVe;
    }

    public void setGiaVe(double giaVe) {
        this.giaVe = giaVe;
    }

    // Phương thức toString
    @Override
    public String toString() {
        return "ThongTinChuyenTau{" +
                "chuyenTau=" + (chuyenTau != null ? chuyenTau.getMaChuyenTau() : "null") +
                ", loaiViTri=" + (loaiViTri != null ? loaiViTri.getMaLoaiVT() : "null") +
                ", giaVe=" + giaVe +
                '}';
    }
}
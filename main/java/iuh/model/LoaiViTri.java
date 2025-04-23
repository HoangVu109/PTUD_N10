package iuh.model;

public class LoaiViTri {

    private String maLoaiVT;
    private String tenLoaiVT;
    private String moTa;

    // Constructor rỗng
    public LoaiViTri() {
    }

    // Constructor đầy đủ tham số
    public LoaiViTri(String maLoaiVT, String tenLoaiVT, String moTa) {
        this.maLoaiVT = maLoaiVT;
        this.tenLoaiVT = tenLoaiVT;
        this.moTa = moTa;
    }

    // Getter và Setter
    public String getMaLoaiVT() {
        return maLoaiVT;
    }

    public void setMaLoaiVT(String maLoaiVT) {
        this.maLoaiVT = maLoaiVT;
    }

    public String getTenLoaiVT() {
        return tenLoaiVT;
    }

    public void setTenLoaiVT(String tenLoaiVT) {
        this.tenLoaiVT = tenLoaiVT;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    // Phương thức toString
    @Override
    public String toString() {
        return "LoaiViTri{" +
                "maLoaiVT='" + maLoaiVT + '\'' +
                ", tenLoaiVT='" + tenLoaiVT + '\'' +
                ", moTa='" + (moTa != null ? moTa : "") + '\'' +
                '}';
    }
}

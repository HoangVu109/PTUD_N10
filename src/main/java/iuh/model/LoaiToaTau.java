package iuh.model;

public class LoaiToaTau {

    private String maLoaiToaTau;
    private String tenLoaiToaTau;
    private String moTa;

    // Constructor rỗng
    public LoaiToaTau() {
    }

    // Constructor đầy đủ tham số
    public LoaiToaTau(String maLoaiToaTau, String tenLoaiToaTau, String moTa) {
        this.maLoaiToaTau = maLoaiToaTau;
        this.tenLoaiToaTau = tenLoaiToaTau;
        this.moTa = moTa;
    }

    // Getter và Setter
    public String getMaLoaiToaTau() {
        return maLoaiToaTau;
    }

    public void setMaLoaiToaTau(String maLoaiToaTau) {
        this.maLoaiToaTau = maLoaiToaTau;
    }

    public String getTenLoaiToaTau() {
        return tenLoaiToaTau;
    }

    public void setTenLoaiToaTau(String tenLoaiToaTau) {
        this.tenLoaiToaTau = tenLoaiToaTau;
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
        return "LoaiToaTau{" +
                "maLoaiToaTau='" + maLoaiToaTau + '\'' +
                ", tenLoaiToaTau='" + tenLoaiToaTau + '\'' +
                ", moTa='" + (moTa != null ? moTa : "") + '\'' +
                '}';
    }
}
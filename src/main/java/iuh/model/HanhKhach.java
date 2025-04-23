package iuh.model;

public class HanhKhach {

    private String soCCCD;
    private String hoTenHK;
    private String soDT;
    private String diaChi;

    // Constructor rỗng
    public HanhKhach() {
    }

    // Constructor đầy đủ tham số
    public HanhKhach(String soCCCD, String hoTenHK, String soDT, String diaChi) {
        this.soCCCD = soCCCD;
        this.hoTenHK = hoTenHK;
        this.soDT = soDT;
        this.diaChi = diaChi;
    }

    // Getter và Setter
    public String getSoCCCD() {
        return soCCCD;
    }

    public void setSoCCCD(String soCCCD) {
        this.soCCCD = soCCCD;
    }

    public String getHoTenHK() {
        return hoTenHK;
    }

    public void setHoTenHK(String hoTenHK) {
        this.hoTenHK = hoTenHK;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi; // Địa chỉ có thể null
    }

    // Phương thức toString
    @Override
    public String toString() {
        return "HanhKhach{" +
                "soCCCD='" + soCCCD + '\'' +
                ", hoTenHK='" + hoTenHK + '\'' +
                ", soDT='" + soDT + '\'' +
                ", diaChi='" + (diaChi != null ? diaChi : "") + '\'' +
                '}';
    }
}
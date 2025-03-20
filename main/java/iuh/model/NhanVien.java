package iuh.model;

public class NhanVien {
    private String maNV;
    private String hoTen;
    private String chucVu;
    private String gioiTinh;
    private String cccd;
    private String ngaySinh;
    private String diaChi;

    // Constructors không có gì
    public NhanVien() {
    }
    //Constructors day du
    public NhanVien(String maNV, String hoTen, String chucVu, String gioiTinh, String cccd, String ngaySinh, String diaChi) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.chucVu = chucVu;
        this.gioiTinh = gioiTinh;
        this.cccd = cccd;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
    }
    //

    // Getters
    public String getMaNV() { return maNV; }
    public String getHoTen() { return hoTen; }
    public String getChucVu() { return chucVu; }
    public String getGioiTinh() { return gioiTinh; }
    public String getCccd() { return cccd; }
    public String getNgaySinh() { return ngaySinh; }
    public String getDiaChi() { return diaChi; }

    // Setters
    public void setMaNV(String maNV) { this.maNV = maNV; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }
    public void setChucVu(String chucVu) { this.chucVu = chucVu; }
    public void setGioiTinh(String gioiTinh) { this.gioiTinh = gioiTinh; }
    public void setCccd(String cccd) { this.cccd = cccd; }
    public void setNgaySinh(String ngaySinh) { this.ngaySinh = ngaySinh; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }
    }
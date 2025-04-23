package iuh.model;

public class VeTau {

    private String maVe;
    private HanhKhach hanhKhach;
    private ViTri viTri;
    private double giaVe;
    private boolean daBiHuy;
    private ChuongTrinhUuDai chuongTrinhUuDai;
    private HoaDon hoaDon;

    // Constructor rỗng
    public VeTau() {
    }

    // Constructor đầy đủ tham số
    public VeTau(String maVe, HanhKhach hanhKhach, ViTri viTri, boolean daBiHuy,
                 ChuongTrinhUuDai chuongTrinhUuDai, HoaDon hoaDon, ThongTinChuyenTau thongTinChuyenTau) {
        this.maVe = maVe;
        this.hanhKhach = hanhKhach;
        this.viTri = viTri;
        this.daBiHuy = daBiHuy;
        this.chuongTrinhUuDai = chuongTrinhUuDai;
        this.hoaDon = hoaDon;
        calGiaVe(thongTinChuyenTau);
    }

    // Getter và Setter
    public String getMaVe() {
        return maVe;
    }

    public void setMaVe(String maVe) {
        this.maVe = maVe;
    }

    public HanhKhach getHanhKhach() {
        return hanhKhach;
    }

    public void setHanhKhach(HanhKhach hanhKhach) {
        this.hanhKhach = hanhKhach;
    }

    public ViTri getViTri() {
        return viTri;
    }

    public void setViTri(ViTri viTri) {
        this.viTri = viTri;
    }

    public double getGiaVe() {
        return giaVe;
    }

    public void setGiaVe(double giaVe) {
        this.giaVe = giaVe;
    }

    public boolean isDaBiHuy() {
        return daBiHuy;
    }

    public void setDaBiHuy(boolean daBiHuy) {
        this.daBiHuy = daBiHuy;
    }

    public ChuongTrinhUuDai getChuongTrinhUuDai() {
        return chuongTrinhUuDai;
    }

    public void setChuongTrinhUuDai(ChuongTrinhUuDai chuongTrinhUuDai) {
        this.chuongTrinhUuDai = chuongTrinhUuDai;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public void calGiaVe(ThongTinChuyenTau thongTinChuyenTau) {
        double giaVeGoc = thongTinChuyenTau.getGiaVe();
        double chietKhau = chuongTrinhUuDai.getChietKhau();
        setGiaVe(giaVeGoc - giaVeGoc*chietKhau/100);
    }

    // Phương thức toString
    @Override
    public String toString() {
        return "VeTau{" +
                "maVe='" + maVe + '\'' +
                ", hanhKhach=" + (hanhKhach != null ? hanhKhach.getSoCCCD() : "null") +
                ", viTri=" + (viTri != null ? "maChuyenTau=" + (viTri.getToaTau() != null && viTri.getToaTau().getChuyenTau() != null ? viTri.getToaTau().getChuyenTau().getMaChuyenTau() : "null") +
                ", soToa=" + (viTri.getToaTau() != null ? viTri.getToaTau().getSoToa() : "null") +
                ", soViTri=" + viTri.getSoViTri() : "null") +
                ", giaVe=" + giaVe +
                ", daBiHuy=" + daBiHuy +
                ", chuongTrinhUuDai=" + (chuongTrinhUuDai != null ? chuongTrinhUuDai.getMaChuongTrinh() : "null") +
                ", hoaDon=" + (hoaDon != null ? hoaDon.getMaHoaDon() : "null") +
                '}';
    }
}
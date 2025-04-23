package iuh.model;

public class ChuongTrinhUuDai {

    private String maChuongTrinh;
    private String tenChuongTrinh;
    private int chietKhau;

    // Constructor rỗng
    public ChuongTrinhUuDai() {
    }

    // Constructor đầy đủ tham số
    public ChuongTrinhUuDai(String maChuongTrinh, String tenChuongTrinh, int chietKhau) {
        this.maChuongTrinh = maChuongTrinh;
        this.tenChuongTrinh = tenChuongTrinh;
        this.chietKhau = chietKhau;
    }

    // Getter và Setter
    public String getMaChuongTrinh() {
        return maChuongTrinh;
    }

    public void setMaChuongTrinh(String maChuongTrinh) {
        this.maChuongTrinh = maChuongTrinh;
    }

    public String getTenChuongTrinh() {
        return tenChuongTrinh;
    }

    public void setTenChuongTrinh(String tenChuongTrinh) {
        this.tenChuongTrinh = tenChuongTrinh;
    }

    public int getChietKhau() {
        return chietKhau;
    }

    public void setChietKhau(int chietKhau) {
        this.chietKhau = chietKhau;
    }

    // Phương thức toString
    @Override
    public String toString() {
        return "ChuongTrinhUuDai{" +
                "maChuongTrinh='" + maChuongTrinh + '\'' +
                ", tenChuongTrinh='" + tenChuongTrinh + '\'' +
                ", chietKhau=" + chietKhau +
                '}';
    }
}
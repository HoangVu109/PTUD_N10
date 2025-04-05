package iuh.model;

public class Tau {
    private String maTau;
    private String maTuyenTau;
    private boolean daBiXoa;

    // Getters and Setters
    public String getMaTau() {
        return maTau;
    }

    public void setMaTau(String maTau) {
        this.maTau = maTau;
    }

    public String getMaTuyenTau() {
        return maTuyenTau;
    }

    public void setMaTuyenTau(String maTuyenTau) {
        this.maTuyenTau = maTuyenTau;
    }

    public boolean isDaBiXoa() {
        return daBiXoa;
    }

    public void setDaBiXoa(boolean daBiXoa) {
        this.daBiXoa = daBiXoa;
    }
   public void getDanhSachTau() {
        System.out.println("Mã tàu: " + maTau);
        System.out.println("Mã tuyến tàu: " + maTuyenTau);
        System.out.println("Đã bị xóa: " + (daBiXoa ? "Có" : "Không"));
    }

    @Override
    public String toString() {
        return "Tau{" +
                "maTau='" + maTau + '\'' +
                ", maTuyenTau='" + maTuyenTau + '\'' +
                ", daBiXoa=" + daBiXoa +
                '}';
    }
}
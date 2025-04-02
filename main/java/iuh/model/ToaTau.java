package iuh.model;

public class ToaTau {
    private String maToaTau;
    private String maTau;
    private boolean daBiXoa;
    // Getters and Setters
    public String getMaToaTau() {
        return maToaTau;
    }

    public void setMaToaTau(String maToaTau) {
        this.maToaTau = maToaTau;
    }

    public String getMaTau() {
        return maTau;
    }

    public void setMaTau(String maTau) {
        this.maTau = maTau;
    }

    public boolean isDaBiXoa() {
        return daBiXoa;
    }

    public void setDaBiXoa(boolean daBiXoa) {
        this.daBiXoa = daBiXoa;
    }

    @Override
    public String toString() {
        return "ToaTau{" +
                "maToaTau='" + maToaTau + '\'' +
                ", maTau='" + maTau + '\'' +
                ", daBiXoa=" + daBiXoa +
                '}';
    }
}
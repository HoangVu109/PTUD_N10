package iuh.model;

import java.time.LocalDateTime;

public class CaLamViec {

    private String maCa;
    private LocalDateTime gioBatDau;
    private LocalDateTime gioKetThuc;
    private NhanVien nhanVien;

    // Constructor rỗng
    public CaLamViec() {
    }

    // Constructor đầy đủ tham số
    public CaLamViec(String maCa, LocalDateTime gioBatDau, LocalDateTime gioKetThuc, NhanVien nhanVien) {
        this.maCa = maCa;
        this.gioBatDau = gioBatDau;
        this.gioKetThuc = gioKetThuc;
        this.nhanVien = nhanVien;
    }

    // Getter và Setter
    public String getMaCa() {
        return maCa;
    }

    public void setMaCa(String maCa) {
        if (maCa == null || maCa.length() != 8) {
            throw new IllegalArgumentException("Mã ca phải có đúng 8 ký tự và không được null");
        }
        this.maCa = maCa;
    }

    public LocalDateTime getGioBatDau() {
        return gioBatDau;
    }

    public void setGioBatDau(LocalDateTime gioBatDau) {
        this.gioBatDau = gioBatDau;
    }

    public LocalDateTime getGioKetThuc() {
        return gioKetThuc;
    }

    public void setGioKetThuc(LocalDateTime gioKetThuc) {

        this.gioKetThuc = gioKetThuc;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    // Phương thức toString
    @Override
    public String toString() {
        return "CaLamViec{" +
                "maCa='" + maCa + '\'' +
                ", gioBatDau=" + gioBatDau +
                ", gioKetThuc=" + gioKetThuc +
                ", maNhanVien='" + nhanVien.getMaSoNV() + '\'' +
                '}';
    }
}

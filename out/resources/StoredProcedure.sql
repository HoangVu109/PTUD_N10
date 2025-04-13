USE GaSaiGonDB
GO

CREATE PROCEDURE sp_ThemNhanVien
    @maSoNV VARCHAR(6),
    @soCCCD VARCHAR(20),
    @hoTenNV NVARCHAR(100),
    @ngaySinh DATE,
    @gioiTinh INT,
    @soDT VARCHAR(15),
    @diaChi NVARCHAR(255),
    @chucVu INT,
    @matKhau VARCHAR(255),
    @daNghiViec BIT
AS
BEGIN
    INSERT INTO NhanVien (maSoNV, soCCCD, hoTenNV, ngaySinh, gioiTinh, soDT, diaChi, chucVu, matKhau, daNghiViec)
    VALUES (@maSoNV, @soCCCD, @hoTenNV, @ngaySinh, @gioiTinh, @soDT, @diaChi, @chucVu, HASHBYTES('SHA2_256', @matKhau), @daNghiViec)
END
GO
CREATE PROCEDURE sp_ThemChuyenTau
    @maChuyenTau VARCHAR(10),
    @maTau VARCHAR(10),
    @gioKhoiHanh DATETIME,
    @daBiHuy BIT,
    @soLuongHKToiDa INT,
    @soLuongHK INT
AS
BEGIN
    -- Kiểm tra mã chuyến tàu có trùng không
    IF EXISTS (SELECT 1 FROM ChuyenTau WHERE maChuyenTau = @maChuyenTau)
    BEGIN
        RAISERROR ('Mã chuyến tàu đã tồn tại!', 16, 1);
        RETURN;
    END

    -- Kiểm tra mã tàu có tồn tại không
    IF NOT EXISTS (SELECT 1 FROM Tau WHERE maTau = @maTau AND daBiXoa = 0)
    BEGIN
        RAISERROR ('Mã tàu không tồn tại hoặc đã bị xóa!', 16, 1);
        RETURN;
    END

    -- Kiểm tra giờ khởi hành phải ở tương lai
    IF @gioKhoiHanh <= GETDATE()
    BEGIN
        RAISERROR ('Giờ khởi hành phải ở tương lai!', 16, 1);
        RETURN;
    END

    -- Kiểm tra số lượng hành khách
    IF @soLuongHK < 0 OR @soLuongHKToiDa <= 0
    BEGIN
        RAISERROR ('Số lượng hành khách và số lượng tối đa phải lớn hơn 0!', 16, 1);
        RETURN;
    END

    IF @soLuongHK > @soLuongHKToiDa
    BEGIN
        RAISERROR ('Số lượng hành khách không được vượt quá số lượng tối đa!', 16, 1);
        RETURN;
    END

    -- Thêm chuyến tàu
    INSERT INTO ChuyenTau (maChuyenTau, maTau, gioKhoiHanh, daBiHuy, soLuongHKToiDa, soLuongHK)
    VALUES (@maChuyenTau, @maTau, @gioKhoiHanh, @daBiHuy, @soLuongHKToiDa, @soLuongHK)
END
GO

CREATE PROCEDURE sp_ThemVeTau
    @maVe VARCHAR(10),
    @maHanhKhach VARCHAR(12),
    @maChuyenTau VARCHAR(10),
    @maViTri VARCHAR(10),
    @giaVe FLOAT,
    @daBiHuy BIT
AS
BEGIN
    INSERT INTO VeTau (maVe, maHanhKhach, maChuyenTau, maViTri, giaVe, daBiHuy)
    VALUES (@maVe, @maHanhKhach, @maChuyenTau, @maViTri, @giaVe, @daBiHuy)
END
GO

CREATE PROCEDURE sp_BanVe
    @maHoaDon VARCHAR(20),
    @maNhanVien VARCHAR(6),
    @ngayXuat DATE,
    @hoTenNguoiMua VARCHAR(100),
    @thanhTien FLOAT,
    @soCCCDNguoiMua VARCHAR(12),
    @soDTNguoiMua VARCHAR(10),
    @maVe VARCHAR(10)
AS
BEGIN
    BEGIN TRANSACTION

    INSERT INTO HoaDon (maHoaDon, maNhanVien, ngayXuat, hoTenNguoiMua, thanhTien, soCCCDNguoiMua, soDTNguoiMua)
    VALUES (@maHoaDon, @maNhanVien, @ngayXuat, @hoTenNguoiMua, @thanhTien, @soCCCDNguoiMua, @soDTNguoiMua)

    INSERT INTO ChiTietHoaDon (maHoaDon, maVeTau)
    VALUES (@maHoaDon, @maVe)

    UPDATE ViTri SET daDatCho = 1 WHERE maVT = (SELECT maViTri FROM VeTau WHERE maVe = @maVe)

    COMMIT TRANSACTION
END
GO

CREATE PROCEDURE sp_HuyVe
    @maVe VARCHAR(10)
AS
BEGIN
    BEGIN TRANSACTION

    UPDATE VeTau SET daBiHuy = 1 WHERE maVe = @maVe
    UPDATE ViTri SET daDatCho = 0 WHERE maVT = (SELECT maViTri FROM VeTau WHERE maVe = @maVe)

    COMMIT TRANSACTION
END
GO

CREATE PROCEDURE sp_CapNhatTrangThaiNhanVien
    @maSoNV VARCHAR(6),
    @daNghiViec BIT
AS
BEGIN
    UPDATE NhanVien
    SET daNghiViec = @daNghiViec
    WHERE maSoNV = @maSoNV
END
GO


CREATE PROCEDURE sp_TimLichTrinhChuyenTau
    @gaKhoiHanh VARCHAR(50),
    @gaKetThuc VARCHAR(50),
    @ngayDi DATE
AS
BEGIN
    SELECT c.maChuyenTau, t.maTau, t.maTuyenTau, c.gioKhoiHanh
    FROM ChuyenTau c
    JOIN Tau t ON c.maTau = t.maTau
    JOIN TuyenTau tt ON t.maTuyenTau = tt.maTuyenTau
    WHERE tt.gaKhoiHanh = @gaKhoiHanh
      AND tt.gaKetThuc = @gaKetThuc
      AND CAST(c.gioKhoiHanh AS DATE) = @ngayDi
      AND c.daBiHuy = 0
END
GO

CREATE PROCEDURE sp_ThemChuongTrinhUuDai
    @maChuongTrinh VARCHAR(20),
    @tenChuongTrinh NVARCHAR(100)
AS
BEGIN
    INSERT INTO ChuongTrinhUuDai (maChuongTrinh, tenChuongTrinh)
    VALUES (@maChuongTrinh, @tenChuongTrinh)
END
GO


--CÁC SP HOÁ ĐƠN
CREATE PROCEDURE sp_TimHoaDonTheoMa
    @maHoaDon VARCHAR(20)
AS
BEGIN
    SELECT h.maHoaDon, h.maNhanVien, h.ngayXuat, h.hoTenNguoiMua, h.thanhTien, 
           h.soCCCDNguoiMua, h.soDTNguoiMua, cthd.maVeTau
    FROM HoaDon h
    LEFT JOIN ChiTietHoaDon cthd ON h.maHoaDon = cthd.maHoaDon
    WHERE h.maHoaDon = @maHoaDon
END
GO

CREATE PROCEDURE sp_TimHoaDonTheoCCCD
    @soCCCDNguoiMua VARCHAR(12)
AS
BEGIN
    SELECT h.maHoaDon, h.maNhanVien, h.ngayXuat, h.hoTenNguoiMua, h.thanhTien
    FROM HoaDon h
    WHERE h.soCCCDNguoiMua = @soCCCDNguoiMua
    ORDER BY h.ngayXuat DESC
END
GO

CREATE PROCEDURE sp_TimHoaDonTheoNgay
    @ngayBatDau DATE,
    @ngayKetThuc DATE
AS
BEGIN
    SELECT h.maHoaDon, h.maNhanVien, h.ngayXuat, h.hoTenNguoiMua, h.thanhTien
    FROM HoaDon h
    WHERE h.ngayXuat BETWEEN @ngayBatDau AND @ngayKetThuc
    ORDER BY h.ngayXuat DESC
END
GO


-- CÁC SP Hành khách
CREATE PROCEDURE sp_ThemHanhKhach
    @soCCCD VARCHAR(12),
    @hoTenHK NVARCHAR(50),
    @soDT VARCHAR(11),
    @diaChi NVARCHAR(100) = NULL
AS
BEGIN
    INSERT INTO HanhKhach (soCCCD, hoTenHK, soDT, diaChi)
    VALUES (@soCCCD, @hoTenHK, @soDT, @diaChi)
END
GO

CREATE PROCEDURE sp_CapNhatHanhKhach
    @soCCCD VARCHAR(12),
    @hoTenHK NVARCHAR(50) = NULL,
    @soDT VARCHAR(11) = NULL,
    @diaChi NVARCHAR(100) = NULL
AS
BEGIN
    UPDATE HanhKhach
    SET hoTenHK = COALESCE(@hoTenHK, hoTenHK),
        soDT = COALESCE(@soDT, soDT),
        diaChi = COALESCE(@diaChi, diaChi)
    WHERE soCCCD = @soCCCD
END
GO

CREATE PROCEDURE sp_XoaHanhKhach
    @soCCCD VARCHAR(12)
AS
BEGIN
    DELETE FROM HanhKhach
    WHERE soCCCD = @soCCCD
END
GO

CREATE PROCEDURE sp_TimHanhKhachTheoCCCD
    @soCCCD VARCHAR(12)
AS
BEGIN
    SELECT * FROM HanhKhach WHERE soCCCD = @soCCCD
END
GO

CREATE PROCEDURE sp_TimHanhKhachTheoSDT
    @soDT VARCHAR(11)
AS
BEGIN
    SELECT * FROM HanhKhach WHERE soDT = @soDT
END
GO

CREATE PROCEDURE sp_LayDanhSachHanhKhach
AS
BEGIN
    SELECT * FROM HanhKhach ORDER BY hoTenHK
END
GO

CREATE PROCEDURE sp_KiemTraDangNhap
    @maSoNV VARCHAR(6),
    @matKhau VARCHAR(255),
    @ketQua INT OUTPUT -- Tham số OUTPUT để trả về giá trị
AS
BEGIN
    DECLARE @MatKhauDaLuu VARCHAR(255), @daNghiViec BIT, @chucVu INT;
    
    -- Lấy thông tin nhân viên
    SELECT @MatKhauDaLuu = matKhau, @daNghiViec = daNghiViec , @chucVu = chucVu
    FROM NhanVien 
    WHERE maSoNV = @maSoNV;

    -- Mặc định là thất bại (FALSE)
    SET @ketQua = -1;

    -- Kiểm tra tài khoản có tồn tại không
    IF @MatKhauDaLuu IS NULL
        RETURN; -- Không tìm thấy tài khoản

    -- Kiểm tra xem nhân viên có nghỉ việc không
    IF @daNghiViec = 1
        RETURN; -- Nhân viên đã nghỉ việc

    -- Kiểm tra mật khẩu
    IF @MatKhauDaLuu = HASHBYTES('SHA2_256', @matKhau)
        SET @ketQua = @chucVu;  -- Đăng nhập thành công, trả về chức vụ
END;
GO

CREATE PROCEDURE sp_LayDanhSachNhanVienDangLamViec
AS
BEGIN
    SELECT * FROM NhanVien WHERE daNghiViec = 0;
END;
GO


CREATE PROCEDURE sp_LayDanhSachNhanVien
AS
BEGIN
    SELECT * FROM NhanVien;
END;
GO

CREATE PROCEDURE sp_LayDanhSachNhanVienCu
AS
BEGIN
    SELECT * FROM NhanVien WHERE daNghiViec = 1;
END;
GO
-- spDanhSachChuyenTau 04/04
CREATE PROCEDURE sp_LayDanhSachChuyenTau
AS
BEGIN
    SELECT 
        ct.maChuyenTau,
        ct.maTau,
        ct.gioKhoiHanh,
        ct.daBiHuy,
        ct.soLuongHKToiDa,
        ct.soLuongHK,
        tt.gaKhoiHanh,
        tt.gaKetThuc
    FROM ChuyenTau ct
    JOIN Tau t ON ct.maTau = t.maTau
    JOIN TuyenTau tt ON t.maTuyenTau = tt.maTuyenTau
    WHERE ct.daBiHuy = 0
    ORDER BY ct.gioKhoiHanh DESC;
END
GO
CREATE PROCEDURE sp_CapNhatChuyenTau
    @maChuyenTau VARCHAR(10),
    @maTau VARCHAR(10),
    @gioKhoiHanh DATETIME,
    @daBiHuy BIT,
    @soLuongHKToiDa INT,
    @soLuongHK INT
AS
BEGIN
    UPDATE ChuyenTau
    SET maTau = @maTau,
        gioKhoiHanh = @gioKhoiHanh,
        daBiHuy = @daBiHuy,
        soLuongHKToiDa = @soLuongHKToiDa,
        soLuongHK = @soLuongHK
    WHERE maChuyenTau = @maChuyenTau
END
GO
CREATE PROCEDURE sp_LayDanhSachTuyenTau
AS
BEGIN
    SELECT * FROM TuyenTau WHERE daBiXoa = 0;
END
GO
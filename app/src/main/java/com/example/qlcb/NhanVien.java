package com.example.qlcb;

public class NhanVien extends CanBo {
    private String congViec;

    public NhanVien(String hoTen, String ngaySinh, String gioiTinh, String diaChi, String congViec) {
        super(hoTen, ngaySinh, gioiTinh, diaChi);
        this.congViec = congViec;
    }

    @Override
    public String toString() {
        return super.toString() + " | Công việc: " + congViec;
    }
}

package com.example.qlcb;

public class CanBo {
    private String hoTen;
    private String ngaySinh;
    private String gioiTinh;
    private String diaChi;

    public CanBo(String hoTen, String ngaySinh, String gioiTinh, String diaChi) {
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
    }

    public String getHoTen() { return hoTen; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }

    @Override
    public String toString() {
        return "Họ tên: " + hoTen +
                " | Ngày sinh: " + ngaySinh +
                " | Giới tính: " + gioiTinh +
                " | Địa chỉ: " + diaChi;
    }
}

package com.example.qlcb;

public class CongNhan extends CanBo {
    private String bac;

    public CongNhan(String hoTen, String ngaySinh, String gioiTinh, String diaChi, String bac) {
        super(hoTen, ngaySinh, gioiTinh, diaChi);
        this.bac = bac;
    }

    @Override
    public String toString() {
        return super.toString() + " | Bậc: " + bac;
    }
}

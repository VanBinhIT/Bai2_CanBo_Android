package com.example.qlcb;

public class KySu extends CanBo {
    private String nganhDaoTao;

    public KySu(String hoTen, String ngaySinh, String gioiTinh, String diaChi, String nganhDaoTao) {
        super(hoTen, ngaySinh, gioiTinh, diaChi);
        this.nganhDaoTao = nganhDaoTao;
    }

    @Override
    public String toString() {
        return super.toString() + " | Ngành đào tạo: " + nganhDaoTao;
    }
}

package com.example.qlcb

open class CanBo(
    var hoTen: String,
    var ngaySinh: String,
    var gioiTinh: String,
    var diaChi: String
) {
    open fun hienThiThongTin(): String =
        "Họ tên: $hoTen | Ngày sinh: $ngaySinh | Giới tính: $gioiTinh | Địa chỉ: $diaChi"
}

class CongNhan(
    hoTen: String,
    ngaySinh: String,
    gioiTinh: String,
    diaChi: String,
    var bac: String
) : CanBo(hoTen, ngaySinh, gioiTinh, diaChi) {
    override fun hienThiThongTin(): String =
        "[Công nhân] ${super.hienThiThongTin()} | Bậc: $bac"
}

class KySu(
    hoTen: String,
    ngaySinh: String,
    gioiTinh: String,
    diaChi: String,
    var nganhDaoTao: String
) : CanBo(hoTen, ngaySinh, gioiTinh, diaChi) {
    override fun hienThiThongTin(): String =
        "[Kỹ sư] ${super.hienThiThongTin()} | Ngành đào tạo: $nganhDaoTao"
}

class NhanVien(
    hoTen: String,
    ngaySinh: String,
    gioiTinh: String,
    diaChi: String,
    var congViec: String
) : CanBo(hoTen, ngaySinh, gioiTinh, diaChi) {
    override fun hienThiThongTin(): String =
        "[Nhân viên] ${super.hienThiThongTin()} | Công việc: $congViec"
}

class QLCB {
    private val dsCanBo = mutableListOf<CanBo>()

    fun them(canBo: CanBo) {
        dsCanBo.add(canBo)
    }

    fun timKiemTheoHoTen(tuKhoa: String): List<CanBo> {
        val key = tuKhoa.trim().lowercase()
        return dsCanBo.filter { it.hoTen.lowercase().contains(key) }
    }

    fun hienThiTatCa(): String =
        if (dsCanBo.isEmpty()) "Danh sách trống"
        else dsCanBo.joinToString(separator = "\n") { it.hienThiThongTin() }
}

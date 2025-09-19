package com.example.qlcb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { QLCBScreen() }
    }
}

@Composable
fun QLCBScreen() {
    val qlcb103 = remember { QLCB() }
    var ketQua103 by remember { mutableStateOf("—") }
    var tuKhoa103 by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        val dsCanBo103 = listOf(
            CongNhan("Nguyễn Văn Dung", "01/01/1990", "Nam", "Hà Nội", "4/7"),
            KySu("Trần Văn Bình", "05/03/1992", "Nữ", "Đà Nẵng", "Công nghệ thông tin"),
            NhanVien("Lê Minh Linh", "20/07/1995", "Nam", "TP.HCM", "Lễ tân")
        )
        dsCanBo103.forEach { qlcb103.them(it) }
        ketQua103 = qlcb103.hienThiTatCa()
    }

    MaterialTheme {
        Surface(Modifier.fillMaxSize()) {
            Column(
                Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text("Quản lý cán bộ (MSV_103)", style = MaterialTheme.typography.titleLarge)

                // ===== Form nhập mới =====
                var hoTen by remember { mutableStateOf("") }
                var ngaySinh by remember { mutableStateOf("") }
                var gioiTinh by remember { mutableStateOf("") }
                var diaChi by remember { mutableStateOf("") }
                var loai by remember { mutableStateOf("Công nhân") } // Công nhân | Kỹ sư | Nhân viên
                var truongRieng by remember { mutableStateOf("") }
                var thongBao by remember { mutableStateOf<String?>(null) }

                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Nhập thông tin mới", style = MaterialTheme.typography.titleMedium)

                    OutlinedTextField(
                        value = hoTen, onValueChange = { hoTen = it },
                        label = { Text("Họ tên") }, singleLine = true, modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = ngaySinh, onValueChange = { ngaySinh = it },
                        label = { Text("Ngày sinh (dd/MM/yyyy)") }, singleLine = true, modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = gioiTinh, onValueChange = { gioiTinh = it },
                        label = { Text("Giới tính") }, singleLine = true, modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = diaChi, onValueChange = { diaChi = it },
                        label = { Text("Địa chỉ") }, singleLine = true, modifier = Modifier.fillMaxWidth()
                    )

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                        FilterChipX(
                            text = "Công nhân",
                            selected = loai == "Công nhân",
                            onClick = { loai = "Công nhân"; truongRieng = "" },
                            modifier = Modifier.weight(1f)
                        )
                        FilterChipX(
                            text = "Kỹ sư",
                            selected = loai == "Kỹ sư",
                            onClick = { loai = "Kỹ sư"; truongRieng = "" },
                            modifier = Modifier.weight(1f)
                        )
                        FilterChipX(
                            text = "Nhân viên",
                            selected = loai == "Nhân viên",
                            onClick = { loai = "Nhân viên"; truongRieng = "" },
                            modifier = Modifier.weight(1f)
                        )
                    }

                    val labelRieng = when (loai) {
                        "Công nhân" -> "Bậc (vd: 3/7, 4/7)"
                        "Kỹ sư" -> "Ngành đào tạo"
                        else -> "Công việc"
                    }
                    OutlinedTextField(
                        value = truongRieng, onValueChange = { truongRieng = it },
                        label = { Text(labelRieng) }, singleLine = true, modifier = Modifier.fillMaxWidth()
                    )

                    if (thongBao != null) {
                        Text(thongBao!!, color = MaterialTheme.colorScheme.error)
                    }

                    Button(onClick = {
                        if (hoTen.isBlank() || ngaySinh.isBlank() || gioiTinh.isBlank() || diaChi.isBlank() || truongRieng.isBlank()) {
                            thongBao = "Vui lòng nhập đầy đủ thông tin."
                        } else {
                            val moi = when (loai) {
                                "Công nhân" -> CongNhan(hoTen, ngaySinh, gioiTinh, diaChi, truongRieng)
                                "Kỹ sư" -> KySu(hoTen, ngaySinh, gioiTinh, diaChi, truongRieng)
                                else -> NhanVien(hoTen, ngaySinh, gioiTinh, diaChi, truongRieng)
                            }
                            qlcb103.them(moi)
                            ketQua103 = qlcb103.hienThiTatCa()
                            thongBao = null
                            hoTen = ""; ngaySinh = ""; gioiTinh = ""; diaChi = ""; truongRieng = ""
                        }
                    }) { Text("Thêm cán bộ") }
                }

                Divider()

                OutlinedTextField(
                    value = tuKhoa103,
                    onValueChange = { tuKhoa103 = it },
                    label = { Text("Nhập họ tên cần tìm") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(onClick = {
                        val kq = qlcb103.timKiemTheoHoTen(tuKhoa103)
                        ketQua103 = if (kq.isEmpty()) "Không tìm thấy \"$tuKhoa103\""
                        else kq.joinToString("\n") { it.hienThiThongTin() }
                    }) { Text("Tìm kiếm") }

                    Button(onClick = { ketQua103 = qlcb103.hienThiTatCa() }) {
                        Text("Hiển thị tất cả")
                    }
                }

                Text("Kết quả:", style = MaterialTheme.typography.titleMedium)
                Text(ketQua103)
            }
        }
    }
}

@Composable
private fun FilterChipX(text: String, selected: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier) {
    val bg = if (selected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant
    val fg = if (selected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurfaceVariant
    Surface(onClick = onClick, modifier = modifier, tonalElevation = if (selected) 4.dp else 0.dp, color = bg, shape = MaterialTheme.shapes.small) {
        Box(Modifier.padding(vertical = 8.dp, horizontal = 12.dp)) {
            Text(text, color = fg)
        }
    }
}

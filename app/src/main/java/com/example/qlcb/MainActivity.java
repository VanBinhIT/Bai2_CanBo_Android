package com.example.qlcb;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import java.util.*;

public class MainActivity extends AppCompatActivity {
    private QLCB ql103;
    private EditText edtHoTen103, edtNgaySinh103, edtGioiTinh103, edtDiaChi103, edtThongTinRieng103, edtTimTen103;
    private Spinner spLoai103;
    private Button btnThem103, btnTim103, btnAll103;
    private TextView tvTieuDe103;
    private ListView lvCB103;
    private ArrayAdapter<String> adapter103;
    private final List<String> dsStr103 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ql103 = new QLCB();

        edtHoTen103 = findViewById(R.id.edtHoTen103);
        edtNgaySinh103 = findViewById(R.id.edtNgaySinh103);
        edtGioiTinh103 = findViewById(R.id.edtGioiTinh103);
        edtDiaChi103 = findViewById(R.id.edtDiaChi103);
        edtThongTinRieng103 = findViewById(R.id.edtThongTinRieng103);
        edtTimTen103 = findViewById(R.id.edtTimTen103);

        spLoai103 = findViewById(R.id.spLoai103);
        btnThem103 = findViewById(R.id.btnThem103);
        btnTim103 = findViewById(R.id.btnTim103);
        btnAll103 = findViewById(R.id.btnAll103);
        tvTieuDe103 = findViewById(R.id.tvTieuDe103);
        lvCB103 = findViewById(R.id.lvCB103);

        adapter103 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dsStr103);
        lvCB103.setAdapter(adapter103);

        btnThem103.setOnClickListener(v -> {
            try {
                String ten = edtHoTen103.getText().toString().trim();
                String ns = edtNgaySinh103.getText().toString().trim();
                String gt = edtGioiTinh103.getText().toString().trim();
                String dc = edtDiaChi103.getText().toString().trim();
                String thongTin = edtThongTinRieng103.getText().toString().trim();
                String loai = spLoai103.getSelectedItem().toString();

                CanBo cb;
                if (loai.equals("Công nhân"))
                    cb = new CongNhan(ten, ns, gt, dc, thongTin);
                else if (loai.equals("Kỹ sư"))
                    cb = new KySu(ten, ns, gt, dc, thongTin);
                else
                    cb = new NhanVien(ten, ns, gt, dc, thongTin);

                ql103.them(cb);
                capNhatDanhSach103(ql103.tatCa());
                clearForm103();
            } catch (Exception e) {
                Toast.makeText(this, "Lỗi nhập liệu", Toast.LENGTH_SHORT).show();
            }
        });

        btnTim103.setOnClickListener(v -> {
            String ten = edtTimTen103.getText().toString().trim();
            tvTieuDe103.setText("Kết quả tìm kiếm: " + ten);
            capNhatDanhSach103(ql103.timTheoTen(ten));
        });

        btnAll103.setOnClickListener(v -> {
            tvTieuDe103.setText("Danh sách tất cả cán bộ");
            capNhatDanhSach103(ql103.tatCa());
        });
    }

    private void capNhatDanhSach103(List<CanBo> ds) {
        dsStr103.clear();
        for (CanBo cb : ds) dsStr103.add(cb.toString());
        adapter103.notifyDataSetChanged();
    }

    private void clearForm103() {
        edtHoTen103.setText("");
        edtNgaySinh103.setText("");
        edtGioiTinh103.setText("");
        edtDiaChi103.setText("");
        edtThongTinRieng103.setText("");
    }
}

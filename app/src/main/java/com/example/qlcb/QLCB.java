package com.example.qlcb;

import java.util.ArrayList;
import java.util.List;

public class QLCB {
    private final List<CanBo> ds = new ArrayList<>();

    public void them(CanBo cb) { ds.add(cb); }

    public List<CanBo> tatCa() { return new ArrayList<>(ds); }

    public List<CanBo> timTheoTen(String ten) {
        List<CanBo> kq = new ArrayList<>();
        for (CanBo cb : ds) {
            if (cb.getHoTen().toLowerCase().contains(ten.toLowerCase())) {
                kq.add(cb);
            }
        }
        return kq;
    }
}

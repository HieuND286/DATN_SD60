package com.example.demo.dto.request;


import com.example.demo.entity.ChiTietSanPham;
import com.example.demo.entity.GioHang;
import com.example.demo.entity.GioHangChiTiet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class GioHangChiTietRequest {
    private String id;
    private String gioHang;
    private String chiTietSanPham;
    private int soLuong;
    private BigDecimal thanhTien;
    private int trangThai;
    public GioHangChiTiet map(GioHangChiTiet ghct){
        ghct.setId(this.id);
        ghct.setGioHang(GioHang.builder().id(this.gioHang).build());
        ghct.setChiTietSanPham(ChiTietSanPham.builder().id(this.chiTietSanPham).build());
        ghct.setSoLuong(this.soLuong);
        ghct.setThanhTien(this.thanhTien);
        ghct.setTrangThai(this.trangThai);
        return ghct;
    }
}

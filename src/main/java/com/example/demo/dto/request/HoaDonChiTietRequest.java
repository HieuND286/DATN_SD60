package com.example.demo.dto.request;


import com.example.demo.entity.ChiTietSanPham;
import com.example.demo.entity.HoaDon;
import com.example.demo.entity.HoaDonChiTiet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class HoaDonChiTietRequest {
    private String id;
    private String hoaDon;
    private String chiTietSanPham;
    private int soLuong;
    private BigDecimal giaSauGiam;
    private BigDecimal giaGiam;
    private String nguoiTao;
    private String nguoiSua;
    private LocalDateTime ngayTao;
    private LocalDateTime ngaySua;
    private int trangThai;
    public HoaDonChiTiet map(HoaDonChiTiet hdct){
        hdct.setHoaDon(HoaDon.builder().id(this.hoaDon).build());
        hdct.setChiTietSanPham(ChiTietSanPham.builder().id(this.chiTietSanPham).build());
        hdct.setId(this.id);
        hdct.setSoLuong(this.soLuong);
        hdct.setGiaGiam(this.giaGiam);
        hdct.setGiaSauGiam(this.giaSauGiam);
        hdct.setNguoiTao(this.nguoiTao);
        hdct.setNguoiSua(this.nguoiSua);
        hdct.setNgayTao(this.ngayTao);
        hdct.setNgaySua(this.ngaySua);
        hdct.setTrangThai(this.trangThai);
        return hdct;
    }
}

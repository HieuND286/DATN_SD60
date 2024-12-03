package com.example.demo.dto.request;

import com.example.demo.entity.ChiTietSanPham;
import com.example.demo.entity.TraHang;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TraHangRequest {
    private String id;
    private String idHD;
    private BigDecimal tienMoi;
    private BigDecimal tienGocMoi;
    private BigDecimal tienGiam;
    private String idHDCT;
    private String idCTSP;
    private int soLuong;
    private LocalDateTime ngayTao;
    private LocalDateTime ngaySua;
    private String nguoiTao;
    private String nguoiSua;
    private String ghiChu;
    private int trangThai;

    public TraHang map(TraHang traHang){
        traHang.setId(this.id);
        traHang.setChiTietSanPham(ChiTietSanPham.builder().id(this.idCTSP).build());
        traHang.setSoLuong(this.soLuong);
        traHang.setNgayTao(this.ngayTao);
        traHang.setNgaySua(this.ngaySua);
        traHang.setNguoiTao(this.nguoiTao);
        traHang.setNguoiSua(this.nguoiSua);
        traHang.setGhiChu(this.ghiChu);
        traHang.setTrangThai(this.trangThai);
        return traHang;
    }
}

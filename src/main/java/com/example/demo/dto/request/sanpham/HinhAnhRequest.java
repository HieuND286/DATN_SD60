package com.example.demo.dto.request.sanpham;


import com.example.demo.entity.ChiTietSanPham;
import com.example.demo.entity.HinhAnh;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class HinhAnhRequest {

    private String ma;
    private String chiTietSanPham;
    private String ten;
    private String url;
    private LocalDateTime ngayTao;
    private LocalDateTime ngaySua;
    private String nguoiTao;
    private String nguoiSua;
    private int trangThai;

    public HinhAnh map(HinhAnh ha){
        ha.setMa(this.ma);
        ha.setChiTietSanPham(ChiTietSanPham.builder().id(this.chiTietSanPham).build());
        ha.setTen(this.ten);
        ha.setUrl(this.url);
        ha.setNgayTao(this.ngayTao);
        ha.setNgaySua(this.ngaySua);
        ha.setNguoiTao(this.nguoiTao);
        ha.setNguoiSua(this.nguoiSua);
        return ha;
    }
}

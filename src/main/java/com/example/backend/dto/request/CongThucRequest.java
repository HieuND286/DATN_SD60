package com.example.backend.dto.request;


import com.example.backend.entity.CongThuc;
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

public class CongThucRequest {
    private String id;

    private BigDecimal giaTriDoi;
    private BigDecimal tiSo;
    private LocalDateTime ngayTao;
    private LocalDateTime ngaySua;
    private String nguoiTao;
    private String nguoiSua;
    private int trangThai;
    public CongThuc map(CongThuc congThuc){
        congThuc.setId(this.id);
        congThuc.setGiaTriDoi(this.giaTriDoi);
        congThuc.setTiSo(this.tiSo);
        congThuc.setNgayTao(this.ngayTao);
        congThuc.setNgaySua(this.ngaySua);
        congThuc.setNguoiTao(this.nguoiTao);
        congThuc.setNguoiSua(this.nguoiSua);
        congThuc.setTrangThai(this.trangThai);
        return congThuc;
    }
}

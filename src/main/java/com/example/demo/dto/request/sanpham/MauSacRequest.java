package com.example.demo.dto.request.sanpham;


import com.example.demo.entity.MauSac;
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
public class MauSacRequest {

    private String ma;

    private String ten;

    private LocalDateTime ngayTao;

    private LocalDateTime ngaySua;

    private String nguoiTao;

    private String nguoiSua;

    private int trangThai;

    public MauSac mapMS(MauSac ms){
        ms.setMa(this.ma);
        ms.setTen(this.ten);
        ms.setNgayTao(this.ngayTao);
        ms.setNgaySua(this.ngaySua);
        ms.setNguoiTao(this.nguoiTao);
        ms.setNguoiSua(this.nguoiSua);
        ms.setTrangThai(this.trangThai);
        return ms;
    }
}

package com.example.demo.dto.request.sanpham;


import com.example.demo.entity.DeGiay;
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

public class DeGiayRequest {
    private String ma;

    private String ten;

    private LocalDateTime ngayTao;

    private LocalDateTime ngaySua;

    private String nguoiTao;

    private String nguoiSua;

    private int trangThai;

    public DeGiay mapDG(DeGiay dg){
        dg.setMa(this.ma);
        dg.setTen(this.ten);
        dg.setNgayTao(this.ngayTao);
        dg.setNgaySua(this.ngaySua);
        dg.setNguoiTao(this.nguoiTao);
        dg.setNguoiSua(this.nguoiSua);
        dg.setTrangThai(this.trangThai);
        return dg;
    }
}

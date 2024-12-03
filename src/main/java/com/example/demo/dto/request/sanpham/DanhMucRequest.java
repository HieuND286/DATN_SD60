package com.example.demo.dto.request.sanpham;


import com.example.demo.entity.DanhMuc;
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

public class DanhMucRequest {

    private String ma;

    private String ten;

    private LocalDateTime ngayTao;

    private LocalDateTime ngaySua;

    private String nguoiTao;

    private String nguoiSua;

    private int trangThai;

    public DanhMuc mapDM(DanhMuc dm){
        dm.setMa(this.ma);
        dm.setTen(this.ten);
        dm.setNgayTao(this.ngayTao);
        dm.setNgaySua(this.ngaySua);
        dm.setNguoiTao(this.nguoiTao);
        dm.setNguoiSua(this.nguoiSua);
        dm.setTrangThai(this.trangThai);
        return dm;
    }
}

package com.example.demo.dto.request.sanpham;


import com.example.demo.entity.Hang;
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

public class HangRequest {

    private String ma;

    private String ten;

    private LocalDateTime ngayTao;

    private LocalDateTime ngaySua;

    private String nguoiTao;

    private String nguoiSua;

    private int trangThai;

    public Hang mapH(Hang h){
        h.setMa(this.ma);
        h.setTen(this.ten);
        h.setNgayTao(this.ngayTao);
        h.setNgaySua(this.ngaySua);
        h.setNguoiTao(this.nguoiTao);
        h.setNguoiSua(this.nguoiSua);
        h.setTrangThai(this.trangThai);
        return h;
    }
}

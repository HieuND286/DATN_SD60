package com.example.demo.dto.request.sanpham;

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

public class AddAnhRequest {
    private String ma;
    private String ten;
    private String url;
    private LocalDateTime ngayTao;
    private LocalDateTime ngaySua;
    private String nguoiTao;
    private String nguoiSua;
    private int trangThai;

    public HinhAnh map(HinhAnh ha){
        ha.setMa(this.ma);
        ha.setTen(this.ten);
        ha.setUrl(this.url);
        ha.setNgayTao(this.ngayTao);
        ha.setNgaySua(this.ngaySua);
        ha.setNguoiTao(this.nguoiTao);
        ha.setNguoiSua(this.nguoiSua);
        return ha;
    }
}

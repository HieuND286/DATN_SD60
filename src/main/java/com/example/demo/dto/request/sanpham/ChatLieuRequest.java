package com.example.demo.dto.request.sanpham;


import com.example.demo.entity.ChatLieu;
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

public class ChatLieuRequest {

    private String ma;

    private String ten;

    private LocalDateTime ngayTao;

    private LocalDateTime ngaySua;

    private String nguoiTao;

    private String nguoiSua;

    private int trangThai;

    public ChatLieu mapCL(ChatLieu cl){
        cl.setMa(this.ma);
        cl.setTen(this.ten);
        cl.setNgayTao(this.ngayTao);
        cl.setNgaySua(this.ngaySua);
        cl.setNguoiTao(this.nguoiTao);
        cl.setNguoiSua(this.nguoiSua);
        cl.setTrangThai(this.trangThai);
        return cl;
    }
}

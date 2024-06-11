package com.example.demo.dto.request;


import com.example.demo.entity.HoaDon;
import com.example.demo.entity.LichSuHoaDon;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString

public class LichSuHoaDonRequest {
    String idHD;
     String moTaHoatDong;
     String nguoiTao;
     String nguoiSua;
     LocalDateTime ngayTao;
    LocalDateTime ngaySua;
     int trangThai;
     public LichSuHoaDon map(LichSuHoaDon lichSuHoaDon){
         lichSuHoaDon.setMoTaHoatDong(this.moTaHoatDong);
         lichSuHoaDon.setNguoiTao(this.nguoiTao);
         lichSuHoaDon.setNguoiSua(this.nguoiSua);
         lichSuHoaDon.setNgayTao(this.ngayTao);
         lichSuHoaDon.setNgaySua(this.ngaySua);
         lichSuHoaDon.setTrangThai(this.trangThai);
         lichSuHoaDon.setHoaDon(HoaDon.builder().id(this.idHD).build());
         return  lichSuHoaDon;
     }
}

package com.example.demo.dto.request.sanphamupdate;

import com.example.demo.entity.ChatLieu;
import com.example.demo.entity.ChiTietSanPham;
import com.example.demo.entity.DanhMuc;
import com.example.demo.entity.DeGiay;
import com.example.demo.entity.Hang;
import com.example.demo.entity.KichThuoc;
import com.example.demo.entity.MauSac;
import com.example.demo.entity.SanPham;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateCTSPRequest {

    public String sanPham;
    public int trangThai;
    public int soLuong;
    public String moTa;
    public BigDecimal giaBan;
    public String danhMuc;
    public String deGiay;
    public String hang;
    public String kichThuoc;
    public String mauSac;
    public String chatLieu;

    public ChiTietSanPham map(ChiTietSanPham ct){
        ct.setTrangThai(this.trangThai);
        ct.setSoLuong(this.soLuong);
        ct.setMoTa(this.moTa);
        ct.setGiaBan(this.giaBan);
        ct.setSanPham(SanPham.builder().id(this.sanPham).build());
        ct.setDanhMuc(DanhMuc.builder().id(this.danhMuc).build());
        ct.setDeGiay(DeGiay.builder().id(this.deGiay).build());
        ct.setHang(Hang.builder().id(this.hang).build());
        ct.setKichThuoc(KichThuoc.builder().id(this.kichThuoc).build());
        ct.setMauSac(MauSac.builder().id(this.mauSac).build());
        ct.setChatLieu(ChatLieu.builder().id(this.chatLieu).build());
        return ct;
    }
}

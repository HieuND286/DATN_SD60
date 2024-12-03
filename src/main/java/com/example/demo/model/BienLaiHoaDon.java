package com.example.demo.model;

import com.example.demo.dto.request.HoaDonChiTietSendMailRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BienLaiHoaDon {
    private String sdt;

    private String diaChi;

    private String ten;

    private String ma;

    private String giamgia;

    private String tongTien;

    private String ghiChu;

    private String phiShip;

    private String anh;

    private String tongThanhToan;

    private LocalDateTime date;

    private Integer soLuong;

    private List<HoaDonChiTietSendMailRequest> items;
}

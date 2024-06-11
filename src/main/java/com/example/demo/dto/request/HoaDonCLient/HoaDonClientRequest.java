package com.example.demo.dto.request.HoaDonCLient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class HoaDonClientRequest {
    private BigDecimal tongTien;

    private BigDecimal tienShip;

    private BigDecimal tienSauGiam;
    private BigDecimal giaGiamGia;

    private String idUser;

    private  String email;

    private String tenNguoiNhan;

    private String diaChi;

    private Date ngayDuKienNhan;

    private String sdt;
    private String idVoucher;

    private Integer idPayMethod;

    private  String maGiaoDich;

    private List<KHHoaDonChiTietRequest> listHDCT;


}

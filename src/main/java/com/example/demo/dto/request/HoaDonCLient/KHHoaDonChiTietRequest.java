package com.example.demo.dto.request.HoaDonCLient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class KHHoaDonChiTietRequest {
    private String idCTSP;

    private BigDecimal donGia;

    private Integer soLuong;

   private String idGioHang;
}

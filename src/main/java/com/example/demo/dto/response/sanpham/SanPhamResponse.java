package com.example.demo.dto.response.sanpham;

import org.springframework.beans.factory.annotation.Value;
import java.sql.Date;

public interface SanPhamResponse {
    @Value("#{target.idSP}")
    String getIdSP();

    @Value("#{target.ma}")
    String getMa();

    @Value("#{target.ten}")
    String getTen();

    @Value("#{target.soLuong}")
    int getSoLuong();

    @Value("#{target.trangThai}")
    int getTrangThai();
}

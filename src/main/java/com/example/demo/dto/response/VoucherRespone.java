package com.example.demo.dto.response;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;

public interface VoucherRespone {
    public String getId();
    public String getMa();
    public String getTen();
    public String getLoaiVoucher();
    public LocalDateTime getNgayBatDau();
    public LocalDateTime getNgayKetThuc();
    public String getMucDo();
    public BigDecimal getGiamToiDa();
    public BigDecimal getDieuKien();
    public String getSoLuong();
    public String getNguoiTao();
    public String getNguoiSua();
    public Date getNgayTao();
    public Date getNgaySua();
    public String getTrangThai();
}

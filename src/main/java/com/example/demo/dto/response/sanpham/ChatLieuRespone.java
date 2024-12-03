package com.example.demo.dto.response.sanpham;

import java.time.LocalDateTime;

public interface ChatLieuRespone {
    public String getId();

    public String getMa();

    public String getTen();

    public LocalDateTime getNgayTao();

    public LocalDateTime getNgaySua();

    public String getNguoiTao();

    public String getNguoiSua();

    public int getTrangThai();
}

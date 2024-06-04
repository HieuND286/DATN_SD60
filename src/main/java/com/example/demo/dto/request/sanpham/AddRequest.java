package com.example.demo.dto.request.sanpham;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class AddRequest {
    private ChiTietSanPhamRequest chiTiet;
    private HinhAnhRequest hinh;
}

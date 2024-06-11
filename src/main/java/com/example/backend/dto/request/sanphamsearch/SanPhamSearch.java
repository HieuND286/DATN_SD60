package com.example.backend.dto.request.sanphamsearch;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SanPhamSearch {
    String ten;
    String trangThai;
    int soLuong;
}

package com.example.demo.dto.request.sanphamsearch;

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
public class TimSanPhamTheoMang {
    String[] arraySanPham;
    String[] arrayMauSac;
    String[] arrayKichThuoc;
    int giaBatDau;
    int giaKetThuc;
}

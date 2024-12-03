package com.example.demo.dto.request;

import com.example.demo.entity.NguoiDung;
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
public class DoiMatKhauRequest {
    private String matKhauCu;
    private String matKhau;



    public NguoiDung map(NguoiDung nguoiDung) {
        nguoiDung.setMatKhau(this.matKhau);
        return nguoiDung;
    }
}

package com.example.demo.dto.request.loginReqest;

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
public class SignUpRequest {
    private String ten;
    private String email;
    private String matKhau;



    public NguoiDung map(NguoiDung nguoiDung) {
        nguoiDung.setTen(this.ten);
        nguoiDung.setEmail(this.email);
        nguoiDung.setMatKhau(this.matKhau);

        return nguoiDung;
    }
}

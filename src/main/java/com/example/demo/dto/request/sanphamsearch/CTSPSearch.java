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
public class CTSPSearch {
    String tenCT;
    String idKT;
    String idMS;
    String idDM;
    String idDC;
    String idCL;
    String idH;
    int trangThaiCT;
    int soLuongCT;
    int giaBanCT;
}

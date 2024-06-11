package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@ToString
@Table(name = "khuyen_mai")
public class KhuyenMai {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String ma;
    private String ten;
    private BigDecimal gia_tri_khuyen_mai;
    private LocalDateTime ngay_bat_dau;
    private LocalDateTime ngay_ket_thuc;
    private String loai;
    private String nguoiTao;
    private String nguoiSua;
    private Date ngayTao;
    private Date ngaySua;
    private Integer trangThai;



    public String formatCurrency(){
        Locale loc = new Locale("vi","VN");
        NumberFormat nf = NumberFormat.getCurrencyInstance(loc);
        return  nf.format(this.gia_tri_khuyen_mai);

    }

    public String formatDate(LocalDateTime ldt){
        return ldt.getHour()+":"+ldt.getMinute()+ " - "+ldt.getDayOfMonth()+"/"+ldt.getMonthValue()+"/"+ldt.getYear();
    }
}

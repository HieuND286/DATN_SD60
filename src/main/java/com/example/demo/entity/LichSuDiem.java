package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "lich_su_diem")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LichSuDiem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
    @JoinColumn(name = "tai_khoan_id")
    private NguoiDung nguoiDung;
    @ManyToOne
    @JoinColumn(name = "hoa_don_id")
    private HoaDon hoaDon;
    @ManyToOne
    @JoinColumn(name = "cong_thuc_id")
    private CongThuc congThuc;
    private int diem;
    private LocalDateTime ngayTao;
    private LocalDateTime ngaySua;
    private String nguoiTao;
    private String nguoiSua;
    private int trangThai;

}

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

@Entity
@Table(name = "chitietsp_khuyenmai")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class KhuyenMaiSanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
    @JoinColumn(name = "khuyen_mai_id")
    private KhuyenMai khuyenMai;
    @ManyToOne
    @JoinColumn(name = "chi_tiet_sp_id")
    private ChiTietSanPham chiTietSanPham;
    private String ma;
    private int trangThai;

}

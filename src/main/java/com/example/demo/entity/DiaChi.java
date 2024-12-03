
package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "dia_chi")
@Entity
@Builder
public class DiaChi {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String tenNguoiNhan;
    private String soDienThoai;
    private String diaChi;
    private String idXa;
    private Integer idHuyen;
    private Integer idThanhPho;
    private int idQuocGia;
    private String tenXa;
    private String tenHuyen;
    private String tenThanhPho;
    private String quocGia;
    private int trangThai;
    @ManyToOne
    @JoinColumn(name = "nguoi_dung_id",referencedColumnName = "id")
    private NguoiDung nguoiDung;
}

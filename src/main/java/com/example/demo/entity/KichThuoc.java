package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Table(name = "kich_thuoc")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class KichThuoc {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String ma;

    private String ten;

    private LocalDateTime ngayTao;

    private LocalDateTime ngaySua;

    private String nguoiTao;

    private String nguoiSua;

    private int trangThai;
}

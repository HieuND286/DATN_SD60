package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "mau_sac")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class MauSac {
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


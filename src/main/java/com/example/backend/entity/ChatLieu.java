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
@Table(name = "chat_lieu")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ChatLieu {

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

    public ChatLieu mapCL(ChatLieu cl){
        cl.setMa(this.ma);
        cl.setTen(this.ten);
        cl.setNgayTao(this.ngayTao);
        cl.setNgaySua(this.ngaySua);
        cl.setNguoiTao(this.nguoiTao);
        cl.setNguoiSua(this.nguoiSua);
        cl.setTrangThai(this.trangThai);
        return cl;
    }
}

package com.example.backend.dto.request.hoadonsearch;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class HoaDonSearch {
    String tenHD;
    int loaiHD;
    Date ngayBDHD;
    Date ngayKTHD;
}

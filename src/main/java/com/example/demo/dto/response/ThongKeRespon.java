package com.example.demo.dto.response;


import org.springframework.beans.factory.annotation.Value;

public interface ThongKeRespon {
    @Value("#{target.tongTienThongKe}")
    Float getTongTienThongKe();
    @Value("#{target.tongHoaDonThongKe}")
    int getTongHoaDonThongKe();
}

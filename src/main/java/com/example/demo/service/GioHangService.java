package com.example.demo.service;

import com.example.demo.dto.request.GioHangRequest;
import com.example.demo.dto.response.GioHangRespone;
import com.example.demo.entity.GioHang;
import com.example.demo.repository.GioHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class GioHangService {
    @Autowired
    GioHangRepository gioHangRepository;

    public GioHang addGioHang(GioHangRequest request){
        GioHang gh=request.map(new GioHang());
        gh.setNgayTao(LocalDateTime.now());
        return gioHangRepository.save(gh);
    }
    public GioHangRespone detailGioHang(String idKH){
        return gioHangRepository.detailGioHang(idKH);
    }
    public GioHangRespone detailGHByID(String id){
        return gioHangRepository.detailGHByID(id);
    }
}

package com.example.demo.service;

import com.example.demo.dto.request.LichSuHoaDonRequest;
import com.example.demo.dto.response.AdminHoaDonTimeLineRes;
import com.example.demo.dto.response.AdminHoaDonTimeLineRespon;
import com.example.demo.entity.LichSuHoaDon;
import com.example.demo.repository.LichSuHoaDonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LichSuHoaDonService {
    @Autowired
    LichSuHoaDonRepository lichSuHoaDonRepository;
    public LichSuHoaDon addLichSuHoaDon(LichSuHoaDonRequest lichSuHoaDonRequest){
        LichSuHoaDon lichSuHoaDon = lichSuHoaDonRequest.map(new LichSuHoaDon());
        return lichSuHoaDonRepository.save(lichSuHoaDon);

    }
    public LichSuHoaDon save(LichSuHoaDon lichSuHoaDon){
        return lichSuHoaDonRepository.save(lichSuHoaDon);
    }
    public List<AdminHoaDonTimeLineRespon> getLichHoaDon(String idHD) {
        return lichSuHoaDonRepository.detailLichSuHoaDon(idHD);
    }
    public List<AdminHoaDonTimeLineRes> HoaDonTimeLine(String idHD){
        return lichSuHoaDonRepository.HoaDonTimeLine(idHD);
    }
}

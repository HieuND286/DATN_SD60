package com.example.demo.service.Client;


import com.example.demo.dto.response.AdminHoaDonTimeLineRespon;
import com.example.demo.repository.LichSuHoaDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LichSuHoaDonClientService {
    @Autowired
    LichSuHoaDonRepository lichSuHoaDonRepository;

   public List<AdminHoaDonTimeLineRespon> LichSuHoaDonClient(String  idHD){
        return lichSuHoaDonRepository.detailLichSuHoaDon(idHD);
    }
}

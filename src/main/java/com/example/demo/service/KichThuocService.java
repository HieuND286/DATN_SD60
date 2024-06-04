package com.example.demo.service;

import com.example.demo.dto.request.sanphamsearch.BangConSearch;
import com.example.demo.dto.response.sanpham.KinhThuocRespone;
import com.example.demo.entity.KichThuoc;
import com.example.demo.repository.KinhThuocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KichThuocService {
    @Autowired
    KinhThuocRepository kinhThuocRepository;
    public List<KichThuoc> getALL(){return kinhThuocRepository.findAll();}

    public List<KinhThuocRespone> getALLKT(){return kinhThuocRepository.getALLKT();}

    public List<KinhThuocRespone> getTim(BangConSearch bangConSearch){return kinhThuocRepository.timKT(bangConSearch);}
}

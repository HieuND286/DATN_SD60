package com.example.demo.service;

import com.example.demo.dto.request.CongThucRequest;
import com.example.demo.entity.CongThuc;
import com.example.demo.repository.CongThucRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CongThucService {
    @Autowired
    CongThucRepository congThucRepository;

    public CongThuc addCT(CongThucRequest request){
        CongThuc ct=request.map(new CongThuc());
        ct.setTrangThai(0);
        List<CongThuc> list=congThucRepository.findAll();
        list.stream().forEach(o->{
            o.setTrangThai(1);
        congThucRepository.save(o);
        });
        return congThucRepository.save(ct);
    }
}

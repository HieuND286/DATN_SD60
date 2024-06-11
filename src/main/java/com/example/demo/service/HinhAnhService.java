package com.example.demo.service;

import com.example.demo.dto.request.sanpham.AddAnhRequest;
import com.example.demo.dto.request.sanpham.HinhAnhRequest;
import com.example.demo.entity.HinhAnh;
import com.example.demo.repository.HinhAnhRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HinhAnhService {
    @Autowired
    HinhAnhRepository hinhAnhRepository;

    public HinhAnh add (HinhAnhRequest request){
        HinhAnh ha = request.map(new HinhAnh());
        return hinhAnhRepository.save(ha);
    }

    public String addAnhMoi (AddAnhRequest request){
        HinhAnh ha = request.map(new HinhAnh());
        hinhAnhRepository.save(ha);
        return "Done";
    }

    public String deleteAnh (String idCTSP){
        hinhAnhRepository.deleteAnhCTSP(idCTSP);
        return "Done";
    }

    public List<HinhAnh> getALL(){
        return hinhAnhRepository.findAll();
    }
}

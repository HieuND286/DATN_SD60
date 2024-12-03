package com.example.demo.service;

import com.example.demo.dto.request.sanpham.KichThuocRequest;
import com.example.demo.dto.request.sanphamsearch.BangConSearch;
import com.example.demo.dto.response.sanpham.KichThuocRespone;
import com.example.demo.entity.KichThuoc;
import com.example.demo.repository.KichThuocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KichThuocService {
    @Autowired
    KichThuocRepository kichThuocRepository;
    public List<KichThuoc> getALL(){
        return kichThuocRepository.findAll();
    }
    public List<KichThuocRespone> getALLKT(){
        return kichThuocRepository.getALLKT();
    }
    public KichThuoc update(String id, KichThuocRequest request) {
        KichThuoc ktGoc = kichThuocRepository.findById(id).get();
        KichThuoc kt = request.mapKT(new KichThuoc());
        kt.setNgayTao(ktGoc.getNgayTao());
        kt.setId(id);
        return kichThuocRepository.save(kt);
    }

    public KichThuoc detailKichThuoc(String id){return kichThuocRepository.findById(id).get();}

    public List<KichThuocRespone> getTim(BangConSearch bangConSearch) {
        return kichThuocRepository.timKT(bangConSearch);
    }
    public String addKT(KichThuocRequest kt){
        KichThuoc kichThuoc = KichThuoc.builder()
                .ma(kt.getMa())
                .ten(kt.getTen())
                .ngayTao(kt.getNgayTao())
                .trangThai(0)
                .build();
        kichThuocRepository.save(kichThuoc);
        return "Done";
    }
}

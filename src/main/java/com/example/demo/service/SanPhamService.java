package com.example.demo.service;

import com.example.demo.dto.request.sanphamsearch.SanPhamSearch;
import com.example.demo.dto.response.sanpham.SanPhamResponse;
import com.example.demo.entity.SanPham;
import com.example.demo.repository.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SanPhamService {
    @Autowired
    SanPhamRepository sanPhamRepository;
    public List<SanPham> getAll(){
        Sort sortByNgayTao =Sort.by(Sort.Direction.DESC, "ngayTao");
        return sanPhamRepository.findAll(sortByNgayTao);
    }
    public List<SanPhamResponse> getALLSP(){return sanPhamRepository.getALLSP();}

    public List<SanPhamResponse>getTim(SanPhamSearch sanPhamSearch){return sanPhamRepository.tim(sanPhamSearch);}

    public List<String> getSPByCTSP(String id){return sanPhamRepository.getIDSPbyCTSP(id);}
}

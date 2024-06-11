package com.example.demo.service;

import com.example.demo.dto.request.sanphamsearch.SanPhamSearch;
import com.example.demo.dto.response.sanpham.SanPhamRespone;
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
    public List<SanPham> getALL(){
        Sort sortByNgayTao = Sort.by(Sort.Direction.DESC, "ngayTao");
        return sanPhamRepository.findAll(sortByNgayTao);
    }
    public List<SanPhamRespone> getALLSP(){
        return sanPhamRepository.getALLSP();
    }

    public List<SanPhamRespone> getTim(SanPhamSearch sanPhamSearch) {
        return sanPhamRepository.tim(sanPhamSearch);
    }

    public boolean existByID(String id){
        return sanPhamRepository.existsById(id);
    }

    public void deleteByID(String id){
         sanPhamRepository.deleteById(id);
    }

    public SanPham addSP(SanPham sp){return sanPhamRepository.save(sp);}

    public List<String>  getSPByCTSP(String id){
        return sanPhamRepository.getIDSPbyCTSP(id);
    }

    public List<String>  getListMauSacBySanPhamID(String id){
        return sanPhamRepository.getListMauSacBySanPhamId(id);
    }

    public List<String>  getListKichThuocBySanPhamID(String id){
        return sanPhamRepository.getListKichThuocBySanPhamId(id);
    }
}

package com.example.demo.service;

import com.example.demo.entity.KhuyenMaiSanPham;
import com.example.demo.repository.CTSPRepository;
import com.example.demo.repository.KhuyenMaiRepository;
import com.example.demo.repository.KhuyenMaiSanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KhuyenMaiSanPhamService {
    @Autowired
    KhuyenMaiSanPhamRepository khuyenMaiSanPhamRepository;
    @Autowired
    KhuyenMaiRepository khuyenMaiRepository;
    @Autowired
    CTSPRepository chiTietSanPhamRespone;

    public List<String> getAllPromotionsByProduct (String id){
        return  khuyenMaiSanPhamRepository.getAllProductByPromotion(id);
    }
    public KhuyenMaiSanPham add(KhuyenMaiSanPham kmsp){
        return khuyenMaiSanPhamRepository.save(kmsp);
    }

    public KhuyenMaiSanPham updateTrangThai(String idKM,String idCTSP,int trangThai){
        KhuyenMaiSanPham kmsp = khuyenMaiSanPhamRepository.findKhuyenMaiSanPham(idKM,idCTSP);
        kmsp.setTrangThai(trangThai);
        return khuyenMaiSanPhamRepository.save(kmsp);
    }

    public List<KhuyenMaiSanPham> getAll(){
        return khuyenMaiSanPhamRepository.getAll();
    }

    public KhuyenMaiSanPham find(String idKM, String idCTSP){
        return  khuyenMaiSanPhamRepository.findKhuyenMaiSanPham(idKM,idCTSP);
    }

    public List<KhuyenMaiSanPham> getListCTSPByKM(String id){
        return  khuyenMaiSanPhamRepository.getListCTSPByKM(id);
    }

    public void delete(KhuyenMaiSanPham kmsp){
        khuyenMaiSanPhamRepository.delete(kmsp);
    }
}

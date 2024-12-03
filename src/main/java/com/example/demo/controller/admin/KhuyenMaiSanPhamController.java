package com.example.demo.controller.admin;

import com.example.demo.entity.ChiTietSanPham;
import com.example.demo.entity.KhuyenMai;
import com.example.demo.entity.KhuyenMaiSanPham;
import com.example.demo.service.CTSPService;
import com.example.demo.service.KhuyenMaiSanPhamService;
import com.example.demo.service.KhuyenMaiService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@CrossOrigin("http://localhost:3000/")
@RestController
@RequestMapping("/admin/khuyen-mai-san-pham")
@RequiredArgsConstructor
public class KhuyenMaiSanPhamController {
    @Autowired
    KhuyenMaiSanPhamService khuyenMaiSanPhamService;
    @Autowired
    CTSPService ctspService;
    @Autowired
    KhuyenMaiService khuyenMaiService;
    @PostMapping("/them/{idKM}/{idCTSP}")
    public ResponseEntity<?> them(@PathVariable("idKM")String idKM , @PathVariable("idCTSP")String idCTSP){
        LocalDateTime now = LocalDateTime.now();
        KhuyenMaiSanPham kmsp = new KhuyenMaiSanPham();
        int len = khuyenMaiSanPhamService.getAll().size();
        ChiTietSanPham ctsp = ctspService.findChiTietSanPhamByID(idCTSP);
        KhuyenMai km = khuyenMaiService.detailKhuyenMai(idKM);
        if (km.getNgay_bat_dau().isBefore(now)){
            ctsp.setKhuyenMai(km);
            ctspService.updateCTSP(ctsp);
            kmsp.setTrangThai(1);
        } else {
            kmsp.setTrangThai(0);
        }
        kmsp.setKhuyenMai(km);
        kmsp.setChiTietSanPham(ctsp);
        kmsp.setMa("KMSP"+len+1);
        return ResponseEntity.ok(khuyenMaiSanPhamService.add(kmsp));
    }

    @PutMapping("/cap-nhat/{idKM}/{idCTSP}")
    public ResponseEntity<?> capNhat(@PathVariable("idKM")String idKM, @PathVariable("idCTSP")String idCTSP){
        LocalDateTime now = LocalDateTime.now();
        int len = khuyenMaiSanPhamService.getAll().size();
        ChiTietSanPham ctsp = ctspService.findChiTietSanPhamByID(idCTSP);
        KhuyenMai km = khuyenMaiService.detailKhuyenMai(idKM);
        KhuyenMaiSanPham kmsp = khuyenMaiSanPhamService.find(idKM,idCTSP);
        if (kmsp == null){
            kmsp.setMa("KMSP"+len+1);
            kmsp.setKhuyenMai(km);
            kmsp.setChiTietSanPham(ctsp);
            if (km.getNgay_bat_dau().isBefore(now)){
                ctsp.setKhuyenMai(km);
                ctspService.updateCTSP(ctsp);
                kmsp.setTrangThai(1);
            } else {
                kmsp.setTrangThai(0);
            }
            return ResponseEntity.ok(khuyenMaiSanPhamService.add(kmsp));
        }
        else {
            if (km.getNgay_bat_dau().isBefore(now)){
                ctsp.setKhuyenMai(km);
                ctspService.updateCTSP(ctsp);
                kmsp.setTrangThai(1);
            } else {
                kmsp.setTrangThai(0);
            }
            return ResponseEntity.ok(khuyenMaiSanPhamService.add(kmsp));
        }
    }
}

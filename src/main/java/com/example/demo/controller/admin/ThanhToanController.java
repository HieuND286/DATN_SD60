package com.example.demo.controller.admin;

import com.example.demo.dto.request.ThanhToanRequest;
import com.example.demo.entity.HoaDon;
import com.example.demo.entity.NguoiDung;
import com.example.demo.repository.HoaDonRepository;
import com.example.demo.service.KhachHangService;
import com.example.demo.service.NguoiDungService;
import com.example.demo.service.ThanhToanService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@CrossOrigin("http://localhost:3000/")
@RestController
@RequestMapping("/admin/thanh-toan")
@RequiredArgsConstructor
public class ThanhToanController {
    @Autowired
    ThanhToanService thanhToanService;
    @Autowired
    HoaDonRepository hoaDonService;
    @Autowired
    KhachHangService khachHangService;
    @Autowired
    NguoiDungService nguoiDungService;
    @PostMapping("/thanh-toan-tien-mat")
    public ResponseEntity<?> thanhToan(@RequestBody ThanhToanRequest request){

        HoaDon hd = hoaDonService.getHDByMa(request.getHoaDon());
        request.setHoaDon(hd.getId());
        NguoiDung nguoiDung = nguoiDungService.findByID(request.getNguoiTao());
        request.setNguoiTao(nguoiDung.getMa());
        request.setNgayTao(LocalDateTime.now());
        request.setPhuongThuc(0);
        request.setTrangThai(0);
        return ResponseEntity.ok(thanhToanService.thanhToanAdmin(request));
    }

    @PostMapping("/thanh-toan-chuyen-khoan")
    public ResponseEntity<?> thanhToanCK(@RequestBody ThanhToanRequest request){

        NguoiDung nguoiDung = nguoiDungService.findByID(request.getNguoiTao());
        HoaDon hd = hoaDonService.getHDByMa(request.getHoaDon());
        request.setHoaDon(hd.getId());
        request.setNguoiTao(nguoiDung.getMa());
        request.setNgayTao(LocalDateTime.now());
        request.setPhuongThuc(1);
        request.setTrangThai(0);
        return ResponseEntity.ok(thanhToanService.thanhToanAdmin(request));
    }


    @GetMapping("/{idHD}")
    public  ResponseEntity<?> getALlLichSuThanhToan(@PathVariable String idHD){
        return ResponseEntity.ok(thanhToanService.getALLLLichSuThanhToanByIDHD(idHD));
    }

    @GetMapping("/hoa-don/{maHD}")
    public ResponseEntity<?> getTTByMa (@PathVariable("maHD") String maHD){
        HoaDon hd = hoaDonService.getHDByMa(maHD);
        if (hd == null) return null;
        return ResponseEntity.ok(thanhToanService.getThanhToanByIdHD(hd.getId()));
    }

    @DeleteMapping("/hoa-don/xoa/{maHD}/{phuongThuc}")
    public void xoaTT (@PathVariable("maHD") String maHD,@PathVariable("phuongThuc") int phuongThuc){
       thanhToanService.xoaThanhToanAdmin(maHD,phuongThuc);
    }
}

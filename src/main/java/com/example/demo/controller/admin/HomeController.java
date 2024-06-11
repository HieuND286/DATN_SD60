package com.example.demo.controller.admin;

import com.example.demo.dto.request.sanphamsearch.TimSanPhamTheoMang;
import com.example.demo.service.HangService;
import com.example.demo.service.HomeService;
import com.example.demo.service.KichThuocService;
import com.example.demo.service.MauSacService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("http://localhost:3000/")
@RestController
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {
    @Autowired
    HomeService homeService;
    @Autowired
    HangService hangService;
    @Autowired
    MauSacService mauSacService;
    @Autowired
    KichThuocService kichThuocService;

    @GetMapping()
    public ResponseEntity<?> getALL() {
        return ResponseEntity.ok(homeService.getAllSanPham());
    }

    @GetMapping("/new")
    public ResponseEntity<?> getALLNew() {
        return ResponseEntity.ok(homeService.getNewSanPham());
    }

    @GetMapping("/hot")
    public ResponseEntity<?> getHotSale() {
        return ResponseEntity.ok(homeService.getHotSale());
    }

    @PostMapping("/searchMang")
    public ResponseEntity<?> getLocSanPham(@RequestBody TimSanPhamTheoMang request) {
        return ResponseEntity.ok(homeService.getSearchListSanPham(request));
    }

    @GetMapping("/hang")
    public ResponseEntity<?> getHang() {
        return ResponseEntity.ok(hangService.getALL());
    }

    @GetMapping("/mau-sac")
    public ResponseEntity<?> getMauSac() {
        return ResponseEntity.ok(mauSacService.getALL());
    }

    @GetMapping("/kich-thuoc")
    public ResponseEntity<?> getKichThuoc() {
        return ResponseEntity.ok(kichThuocService.getALL());
    }

    @GetMapping("/tim-kiem/{tenTim}")
    public ResponseEntity<?> getTimSanPham(@PathVariable("tenTim") String tenTim) {
        return ResponseEntity.ok(homeService.getTim(tenTim));
    }
}

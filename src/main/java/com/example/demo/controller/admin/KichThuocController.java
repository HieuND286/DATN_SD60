package com.example.demo.controller.admin;


import com.example.demo.dto.request.sanpham.KichThuocRequest;
import com.example.demo.dto.request.sanphamsearch.BangConSearch;
import com.example.demo.service.KichThuocService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@CrossOrigin("http://localhost:3000/")
@RestController
@RequestMapping("/admin/kich-thuoc")
@RequiredArgsConstructor
public class KichThuocController {
    @Autowired
    KichThuocService kichThuocService;

    @GetMapping
    public ResponseEntity<?> getALLKT() {
        return  ResponseEntity.ok(kichThuocService.getALLKT());
    }

    @PostMapping("/tim-kiem")
    public ResponseEntity<?> search(@RequestBody BangConSearch bangConSearch){
        return ResponseEntity.ok(kichThuocService.getTim(bangConSearch));
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detail(@PathVariable("id") String id){
        return ResponseEntity.ok(kichThuocService.detailKichThuoc(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody KichThuocRequest request){
        return ResponseEntity.ok(kichThuocService.update(id,request));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody KichThuocRequest kt) {
        int ktThem = kichThuocService.getALL().size();
        kt.setMa("KT" + "-" + (ktThem + 1));
        kt.setNgayTao(LocalDateTime.now());
        return ResponseEntity.ok(kichThuocService.addKT(kt));
    }
}

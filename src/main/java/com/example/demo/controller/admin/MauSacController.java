package com.example.demo.controller.admin;


import com.example.demo.dto.request.sanpham.MauSacRequest;
import com.example.demo.dto.request.sanphamsearch.BangConSearch;
import com.example.demo.service.MauSacService;
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

@CrossOrigin("*")
@RestController
@RequestMapping("/admin/mau-sac")
@RequiredArgsConstructor
public class MauSacController {
    @Autowired
    MauSacService mauSacService;

    @GetMapping
    public ResponseEntity<?> getALLMS() {
        return  ResponseEntity.ok(mauSacService.getALLMS());
    }

    @GetMapping("/detail/{idMS}")
    public ResponseEntity<?> detail(@PathVariable("idMS") String id){
        return ResponseEntity.ok(mauSacService.detailMS(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody MauSacRequest request){
        return ResponseEntity.ok(mauSacService.update(id,request));
    }

    @PostMapping("/tim-kiem")
    public ResponseEntity<?> search(@RequestBody BangConSearch bangConSearch){
        return ResponseEntity.ok(mauSacService.getTim(bangConSearch));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody MauSacRequest v) {
        int msThem = mauSacService.getALL().size();
        v.setNgayTao(LocalDateTime.now());
        return ResponseEntity.ok(mauSacService.addMS(v));
    }
}

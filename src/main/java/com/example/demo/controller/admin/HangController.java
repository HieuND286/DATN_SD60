package com.example.demo.controller.admin;


import com.example.demo.dto.request.sanpham.HangRequest;
import com.example.demo.dto.request.sanphamsearch.BangConSearch;
import com.example.demo.service.HangService;
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
@RequestMapping("/admin/hang")
@RequiredArgsConstructor
public class HangController {
    @Autowired
    HangService hangService;

    @GetMapping
    public ResponseEntity<?> getALLH() {
        return  ResponseEntity.ok(hangService.getALLH());
    }

    @PostMapping("/tim-kiem")
    public ResponseEntity<?> search(@RequestBody BangConSearch bangConSearch){
        return ResponseEntity.ok(hangService.getTim(bangConSearch));
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detail(@PathVariable("id") String id){
        return ResponseEntity.ok(hangService.detailHang(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody HangRequest request){
        return ResponseEntity.ok(hangService.update(id,request));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody HangRequest h) {
        int hThem = hangService.getALL().size();
        h.setMa("H" + "-" + (hThem + 1));
        h.setNgayTao(LocalDateTime.now());
        return ResponseEntity.ok(hangService.addH(h));
    }
}

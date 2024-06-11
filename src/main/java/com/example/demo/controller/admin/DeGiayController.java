package com.example.demo.controller.admin;


import com.example.demo.dto.request.sanpham.DeGiayRequest;
import com.example.demo.dto.request.sanphamsearch.BangConSearch;
import com.example.demo.service.DeGiayService;
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
@RequestMapping("/admin/de-giay")
@RequiredArgsConstructor
public class DeGiayController {
    @Autowired
    private DeGiayService deGiayService;

    @GetMapping
    public ResponseEntity<?> getALLDC() {
        return  ResponseEntity.ok(deGiayService.getALLDC());
    }

    @PostMapping("/tim-kiem")
    public ResponseEntity<?> search(@RequestBody BangConSearch bangConSearch){
        return ResponseEntity.ok(deGiayService.getTim(bangConSearch));
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detail(@PathVariable("id") String id){
        return ResponseEntity.ok(deGiayService.detailDG(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody DeGiayRequest request){
        return ResponseEntity.ok(deGiayService.update(id,request));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody DeGiayRequest dg) {
        int dcThem = deGiayService.getALL().size();
        dg.setMa("DG" + "-" + (dcThem + 1));
        dg.setNgayTao(LocalDateTime.now());
        return ResponseEntity.ok(deGiayService.addDC(dg));
    }
}

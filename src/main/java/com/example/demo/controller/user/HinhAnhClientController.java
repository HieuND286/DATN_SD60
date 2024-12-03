package com.example.demo.controller.user;

import com.example.demo.dto.request.sanpham.AddAnhRequest;
import com.example.demo.repository.HinhAnhRepository;
import com.example.demo.service.HinhAnhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@CrossOrigin("http://localhost:3000/")
@RestController
@RequestMapping("/hinhanh")
public class HinhAnhClientController {
    @Autowired
    HinhAnhRepository hinhAnhRepository;
    @Autowired
    HinhAnhService hinhAnhService;
    @GetMapping("/{ten}/{idSP}")
    public ResponseEntity<?> detail(@PathVariable("ten") String ten, @PathVariable("idSP") String idSP){
        System.out.println(idSP);
        System.out.println(ten);
        return ResponseEntity.ok(hinhAnhRepository.getAnhCTSP(ten,idSP));
    }
    @PostMapping("/add-anh")
    public ResponseEntity<?> upAnh(@RequestBody AddAnhRequest ha){
        int maAnh = hinhAnhService.getALL().size();
        ha.setTrangThai(0);
        ha.setNgayTao(LocalDateTime.now());
        ha.setMa("HA-" + (maAnh + 1));
        hinhAnhService.addAnhMoi(ha);
        return ResponseEntity.ok("Done");
    }
}

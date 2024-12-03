package com.example.demo.controller.admin;

import com.example.demo.dto.request.sanpham.HinhAnhRequest;
import com.example.demo.repository.HinhAnhRepository;
import com.example.demo.service.HinhAnhService;
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
@RequestMapping("/admin/hinhanh")
public class HinhAnhController {
    @Autowired
    HinhAnhRepository hinhAnhRepository;
    @Autowired
    HinhAnhService hinhAnhService;
    @GetMapping("/{ten}/{idSP}")
    public ResponseEntity<?> detail(@PathVariable("ten") String ten,@PathVariable("idSP") String idSP){

        return ResponseEntity.ok(hinhAnhRepository.getAnhCTSP(ten,idSP));
    }
    @PostMapping("/add-anh/{idSP}")
    public ResponseEntity<?> upAnh(@RequestBody HinhAnhRequest ha, @PathVariable("idSP") String idSP){
        int maAnh = hinhAnhService.getALL().size();
        ha.setChiTietSanPham(idSP);
        ha.setTrangThai(0);
        ha.setNgayTao(LocalDateTime.now());
        ha.setMa("HA-" + (maAnh + 1));
        return ResponseEntity.ok(hinhAnhService.add(ha));
    }

    @DeleteMapping("/delete-anh/{idCTSP}")
    public ResponseEntity<?> deleteAnh(@PathVariable("idCTSP") String idCTSP){
        hinhAnhService.deleteAnh(idCTSP);
        return ResponseEntity.ok("Done");
    }
}

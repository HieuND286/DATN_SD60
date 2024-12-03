package com.example.demo.controller.user;

import com.example.demo.dto.request.GioHangChiTietRequest;
import com.example.demo.service.CTSPService;
import com.example.demo.service.GioHangChiTietService;
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

@CrossOrigin("http://localhost:3000/")
@RestController
@RequestMapping("/gio-hang-chi-tiet")
@RequiredArgsConstructor
public class GioHangChiTietController {
    @Autowired
    GioHangChiTietService gioHangChiTietService;
    @Autowired
    CTSPService ctspService;
    @GetMapping("/getAll/{idGH}")
    public ResponseEntity<?> getAllGHCT(@PathVariable("idGH")String idGH){
        return ResponseEntity.ok(gioHangChiTietService.getAllGHCT(idGH));
    }
    @PostMapping("/addGHCT")
    public ResponseEntity<?> addGHCT(@RequestBody GioHangChiTietRequest request){
        return ResponseEntity.ok(gioHangChiTietService.addGHCT(request));
    }
    @PostMapping("/updateSLGHCT")
    public ResponseEntity<?> updateSLGHCT(@RequestBody GioHangChiTietRequest request){
        return ResponseEntity.ok(gioHangChiTietService.updateSLGHCT(request));
    }
    @PostMapping("/updateGHCT")
    public ResponseEntity<?> updateGHCT(@RequestBody GioHangChiTietRequest request){
        return ResponseEntity.ok(gioHangChiTietService.updateGHCT(request));
    }
    @GetMapping("/detailCTSP/{idCT}") // truyền vào ictsp lấy ra detail
    public ResponseEntity<?> getDetail(@PathVariable("idCT") String id) {
        return  ResponseEntity.ok(ctspService.detailCTSPGioHang(id));
    }
    @DeleteMapping("/deleteGHCT/{id}")
    public ResponseEntity<?> deleteGHCT(@PathVariable("id") String id){
        return ResponseEntity.ok(gioHangChiTietService.deleteGHCT(id));
    }

    @GetMapping("/so-luong-san-pham-trong-gio-hang/{idGH}")
    public Integer soLuongSPtrongGH(@PathVariable("idGH")String idGH){
        return gioHangChiTietService.soLuongSanPhamTrongGioHang(idGH);
    }
}

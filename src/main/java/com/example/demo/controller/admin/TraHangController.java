package com.example.demo.controller.admin;

import com.example.demo.dto.request.TraHangRequest;
import com.example.demo.service.TraHangService;
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
@RequestMapping("/admin/tra-hang")
@RequiredArgsConstructor
public class TraHangController {
    @Autowired
    TraHangService traHangService;

    @GetMapping("/hoa-don/{ma}")
    public ResponseEntity<?> getThongTinHoaDon(@PathVariable("ma") String ma){
        return ResponseEntity.ok(traHangService.getHoaDonByMa(ma));
    }

    @GetMapping("/hoa-don-chi-tiet/{ma}")
    public ResponseEntity<?> getHoaDon(@PathVariable("ma")String ma){
        return ResponseEntity.ok(traHangService.getAllHDCTByHoaDon(ma));
    }
    @PostMapping("/add-tra-hang")
    public ResponseEntity<?> addTraHang(@RequestBody TraHangRequest request){
        return ResponseEntity.ok(traHangService.addTraHang(request));
    }

}

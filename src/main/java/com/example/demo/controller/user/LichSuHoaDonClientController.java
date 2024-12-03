package com.example.demo.controller.user;

import com.example.demo.service.Client.LichSuHoaDonClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("http://localhost:3000/")
@RestController
@RequestMapping("/client/lich-su-hoa-don")
public class LichSuHoaDonClientController {
    @Autowired
    LichSuHoaDonClientService lichSuHoaDonClientService;
    @GetMapping("/{idHD}")
    public ResponseEntity<?> getLichSuHoaDon(@PathVariable("idHD") String idHD){
        return ResponseEntity.ok(lichSuHoaDonClientService.LichSuHoaDonClient(idHD));
    }
}

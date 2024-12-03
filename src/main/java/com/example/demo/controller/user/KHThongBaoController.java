package com.example.demo.controller.user;

import com.example.demo.service.ThongBaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("http://localhost:3000/")
@RestController
@RequestMapping("/KH/thong-bao")
@RequiredArgsConstructor
public class KHThongBaoController {

    @Autowired
    private ThongBaoService thongBaoService;


    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(@RequestParam String token){
        return ResponseEntity.ok(thongBaoService.getAllKH(token));
    }

    @GetMapping("/count")
    public ResponseEntity<?> count(@RequestParam String token){
        return ResponseEntity.ok(thongBaoService.countKH(token));
    }
}

package com.example.demo.controller.admin;

import com.example.demo.service.ThongBaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("http://localhost:3000/")
@RestController
@RequestMapping("/admin/thong-bao")
@RequiredArgsConstructor
public class ThongBaoController {
    @Autowired
    private ThongBaoService thongBaoService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(thongBaoService.getAllAdmin());
    }

    @GetMapping("/count")
    public ResponseEntity<?> count() {
        return ResponseEntity.ok(thongBaoService.countAdmin());
    }

    @PutMapping("/da-xem/{id}")
    public ResponseEntity<?> daXem(@PathVariable String id) {
        return ResponseEntity.ok(thongBaoService.daxem(id));
    }
}

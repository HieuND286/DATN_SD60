package com.example.demo.controller.admin;

import com.example.demo.dto.request.CongThucRequest;
import com.example.demo.service.CongThucService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("http://localhost:3000/")
@RestController
@RequestMapping("/admin/cong-thuc")
@RequiredArgsConstructor
public class CongThucController {
    @Autowired
    CongThucService congThucService;
    @PostMapping()
    public ResponseEntity<?> addCT(@RequestBody CongThucRequest request){
        return ResponseEntity.ok(congThucService.addCT(request));
    }
}

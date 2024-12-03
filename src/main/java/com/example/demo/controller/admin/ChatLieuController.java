package com.example.demo.controller.admin;


import com.example.demo.dto.request.sanpham.ChatLieuRequest;
import com.example.demo.dto.request.sanphamsearch.BangConSearch;
import com.example.demo.service.ChatLieuService;
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
@RequestMapping("/admin/chat-lieu")
@RequiredArgsConstructor    
public class ChatLieuController {
    @Autowired
    ChatLieuService chatLieuService;

    @GetMapping
    public ResponseEntity<?> getALLCL(){
        return  ResponseEntity.ok(chatLieuService.getALLCL());
    }

    @PostMapping("/tim-kiem")
    public ResponseEntity<?> search(@RequestBody BangConSearch bangConSearch){
        return ResponseEntity.ok(chatLieuService.getTim(bangConSearch));
    }

    @GetMapping("/detail/{idCL}")
    public ResponseEntity<?> detail(@PathVariable("idCL") String id){
        return ResponseEntity.ok(chatLieuService.detailCL(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody ChatLieuRequest request){
        return ResponseEntity.ok(chatLieuService.update(id,request));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody ChatLieuRequest v){
        int clThem = chatLieuService.getALL().size();
        v.setMa("CL" + "-" + (clThem + 1));
        v.setNgayTao(LocalDateTime.now());
        return  ResponseEntity.ok(chatLieuService.addCL(v));
    }
}

package com.example.demo.controller.admin;


import com.example.demo.dto.request.DoiMatKhauRequest;
import com.example.demo.service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("http://localhost:3000/")
@RestController
@RequestMapping("/admin/nguoi-dung")
public class NguoiDungController {

    @Autowired
    NguoiDungService nguoiDungService;

    @GetMapping("")
    public ResponseEntity<?> getALLNguoiDung(){
        return ResponseEntity.ok(nguoiDungService.getAll());
    }
    @PutMapping("/doi-mat-khau/{idNV}")
    public ResponseEntity<?> doiMatKhau(@RequestBody DoiMatKhauRequest doiMatKhauRequest, @PathVariable("idNV") String idNV){
        return ResponseEntity.ok(nguoiDungService.doiMatKhau(idNV,doiMatKhauRequest));
    }
    @GetMapping("/detail/{idNV}")
    public ResponseEntity<?> detailMK(@PathVariable("idNV") String idNV){
        return ResponseEntity.ok(nguoiDungService.findByID(idNV));
    }
}

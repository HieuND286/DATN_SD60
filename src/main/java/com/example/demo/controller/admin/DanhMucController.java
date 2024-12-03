package com.example.demo.controller.admin;


import com.example.demo.dto.request.sanpham.DanhMucRequest;
import com.example.demo.dto.request.sanphamsearch.BangConSearch;
import com.example.demo.service.DanhMucService;
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
@RequestMapping("/admin/danh-muc")
@RequiredArgsConstructor
public class DanhMucController {
    @Autowired
    private DanhMucService danhMucService;
    @GetMapping
    public ResponseEntity<?> getALLDM(){
        return  ResponseEntity.ok(danhMucService.getALLDM());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody DanhMucRequest request){

        return ResponseEntity.ok(danhMucService.update(id,request));
    }

    @GetMapping("/detail/{idDM}")
    public ResponseEntity<?> detail(@PathVariable("idDM") String id){
        return ResponseEntity.ok(danhMucService.detailDM(id));
    }

    @PostMapping("/tim-kiem")
    public ResponseEntity<?> search(@RequestBody BangConSearch bangConSearch){
        return ResponseEntity.ok(danhMucService.getTim(bangConSearch));
    }
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody DanhMucRequest v){
        int dmThem = danhMucService.getALL().size();
        v.setMa("DM" + "-" + (dmThem + 1));
        v.setNgayTao(LocalDateTime.now());
        return  ResponseEntity.ok(danhMucService.addDM(v));
    }
}

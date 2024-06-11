package com.example.demo.controller.admin;

import com.example.demo.dto.request.NguoiDungSeacrh;
import com.example.demo.dto.request.NhanVienRequest;
import com.example.demo.service.NhanVienService;
import com.google.gson.Gson;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("http://localhost:3000/")
@RestController
@RequestMapping("/admin/nhan-vien")
@RequiredArgsConstructor
public class NhanVienController {
    @Autowired
    NhanVienService nhanVienService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return  ResponseEntity.ok(nhanVienService.getAll());
    }

    @PostMapping()
    public ResponseEntity<?> add(@RequestParam("request") String request,
                                 @RequestParam(value = "file",required = false) MultipartFile file){

        Gson gson = new Gson();

        NhanVienRequest nhanVienRequest=gson.fromJson(request, NhanVienRequest.class);

            return  ResponseEntity.ok(nhanVienService.add(nhanVienRequest,file));


    }
    @PutMapping()
    public ResponseEntity<?> update(@RequestParam("request") String request,
                                    @RequestParam(value = "file", required = false) MultipartFile file) {

        Gson gson = new Gson();
        NhanVienRequest zzzzzzzzz = gson.fromJson(request, NhanVienRequest.class);
        return ResponseEntity.ok(nhanVienService.update(zzzzzzzzz, file));

    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(nhanVienService.getByID(id));

    }

    @PostMapping ("/search")
    public ResponseEntity<?> search(@RequestBody NguoiDungSeacrh nguoiDungSeacrh){
        return ResponseEntity.ok(nhanVienService.getSearchNhanVien(nguoiDungSeacrh));
    }
}

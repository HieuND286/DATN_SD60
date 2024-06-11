package com.example.demo.controller.admin;

import com.example.demo.dto.request.VoucherRequest;
import com.example.demo.dto.request.VoucherSearch;
import com.example.demo.entity.Voucher;
import com.example.demo.service.NguoiDungVoucherService;
import com.example.demo.service.VoucherService;
import com.example.demo.util.Status;
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

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

@CrossOrigin("http://localhost:3000/")
@RestController
@RequestMapping("/admin/voucher")
@RequiredArgsConstructor
public class VoucherController {
    @Autowired
    VoucherService vs;
    @Autowired
    NguoiDungVoucherService nguoiDungVoucherService;
    @GetMapping()
    public ResponseEntity<?> getALL(){
//        vs.checkHan();
        return ResponseEntity.ok(vs.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody VoucherRequest request){
        LocalDateTime ngayBD =  vs.convertTime(request.getNgayBatDau());
        LocalDateTime ngayKT = vs.convertTime(request.getNgayKetThuc());
        request.setNgayBatDau(ngayBD);
        request.setNgayKetThuc(ngayKT);
        LocalDateTime lc= LocalDateTime.now();
        if(ngayBD.compareTo(lc)>0){
            request.setTrangThai(Status.SAP_DIEN_RA);
        }else{
            request.setTrangThai(Status.DANG_HOAT_DONG);
        }
        request.setNgayTao(new java.util.Date());
        return  ResponseEntity.ok(vs.addVoucher(request));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id,@RequestBody VoucherRequest request){
        request.setId(id);
        LocalDateTime ngayBD =  vs.convertTime(request.getNgayBatDau());
        LocalDateTime ngayKT = vs.convertTime(request.getNgayKetThuc());
        request.setNgayBatDau(ngayBD);
        request.setNgayKetThuc(ngayKT);

        LocalDateTime lc= LocalDateTime.now();

        if(request.getNgayBatDau().compareTo(lc)>0){
            request.setTrangThai(Status.SAP_DIEN_RA);
        }else if(request.getNgayBatDau().compareTo(lc)<=0 && request.getNgayKetThuc().compareTo(lc)>0){
            request.setTrangThai(Status.DANG_HOAT_DONG);
        }else{
            request.setTrangThai(Status.NGUNG_HOAT_DONG);
        }
        request.setNgaySua(new java.util.Date());
        return  ResponseEntity.ok(vs.addVoucher(request));
    }
    @GetMapping("/detail/{idV}")
    public ResponseEntity<?> detail(@PathVariable("idV") String id){
        return ResponseEntity.ok(vs.detailVoucher(id));

    }
    @GetMapping("/tim-voucher/{key}/{ngayBD}/{ngayKT}")
    public ResponseEntity<?> tim(@PathVariable("key")String key,
                                 @PathVariable("ngayBD")String ngayBD,
                                 @PathVariable("ngayKT")String ngayKT) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date d1=sdf.parse(ngayBD);
        java.util.Date d2=sdf.parse(ngayKT);
        Date dBD=new Date(d1.getTime());
        Date dKT=new Date(d2.getTime());
        return  ResponseEntity.ok(vs.getTim(key,dBD,dKT));
    }
    @PostMapping("/search-voucher")
    public ResponseEntity<?> search(@RequestBody VoucherSearch voucherSearch){

        return ResponseEntity.ok(vs.getSearch(voucherSearch));
    }
    @PutMapping("/updateTTHD/{id}")
    public ResponseEntity<?> updateTTHD(@PathVariable("id")String id,@RequestBody VoucherRequest request){
        Voucher v=request.map(new Voucher());
        v.setId(id);
        v.setTrangThai(Status.DANG_HOAT_DONG);
        LocalDateTime ngayBD =  vs.convertTime(request.getNgayBatDau());
        LocalDateTime ngayKT = vs.convertTime(request.getNgayKetThuc());
        v.setNgayBatDau(ngayBD);
        v.setNgayKetThuc(ngayKT);
        return ResponseEntity.ok(vs.add(v));
    }
    @PutMapping("/updateTTNgung/{id}")
    public ResponseEntity<?> updateTTNgung(@PathVariable("id")String id,@RequestBody VoucherRequest request){
        LocalDateTime ngayBD =  vs.convertTime(request.getNgayBatDau());
        LocalDateTime ngayKT = vs.convertTime(request.getNgayKetThuc());
        request.setNgayBatDau(ngayBD);
        request.setNgayKetThuc(ngayKT);
        return ResponseEntity.ok(vs.updateTTNgung(id,request));
    }
    @PutMapping("/updateTTSap/{id}")
    public ResponseEntity<?> updateTTSap(@PathVariable("id")String id,@RequestBody VoucherRequest request){
        LocalDateTime ngayBD =  vs.convertTime(request.getNgayBatDau());
        LocalDateTime ngayKT = vs.convertTime(request.getNgayKetThuc());
        request.setNgayBatDau(ngayBD);
        request.setNgayKetThuc(ngayKT);
        return ResponseEntity.ok(vs.updateTTSap(id,request));
    }
    @PutMapping("/updateTTTamDung/{id}")
    public ResponseEntity<?> updateTTamDung(@PathVariable("id")String id,@RequestBody VoucherRequest request){
        return ResponseEntity.ok(vs.updateTTTamDung(id,request));
    }

}

package com.example.demo.controller.user;


import com.example.demo.dto.request.HoaDonCLient.SearchHDByMaAndSdtRequest;
import com.example.demo.dto.request.HoaDonCLient.TrangThaiRequest;
import com.example.demo.dto.request.LichSuHoaDonRequest;
import com.example.demo.entity.HoaDon;
import com.example.demo.service.Client.HoaDonClientService;
import com.example.demo.service.HoaDonChiTietService;
import com.example.demo.service.HoaDonServicee;
import com.example.demo.service.LichSuHoaDonService;
import com.example.demo.service.ThongBaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/client-hoa-don")
@RequiredArgsConstructor
public class HoaDonClientController {

    @Autowired
    HoaDonClientService hoaDonClientService;
    @Autowired
    HoaDonServicee hoaDonService;
    @Autowired
    LichSuHoaDonService lichSuHoaDonService;
    @Autowired
    ThongBaoService thongBaoService;
    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;
    @PostMapping("")
    public ResponseEntity<?> getALLHoaDonOL(@RequestBody TrangThaiRequest request) {
        return ResponseEntity.ok(hoaDonClientService.getALLHDClientByIDKH(request));
    }

    @GetMapping("/hoa-don/{id}")
    public ResponseEntity<?> getALLHoaDonOLByIDHD(@PathVariable("id") String id) {
        return ResponseEntity.ok(hoaDonClientService.detailHDSanPhamClient(id));
    }

    @GetMapping("/detail-hoa-don/{idHD}")
    public ResponseEntity<?> detailHD(@PathVariable("idHD") String id) {
        return ResponseEntity.ok(hoaDonClientService.detailHoaDonClienByIdHD(id));
    }
    @PostMapping("/search")
    public ResponseEntity<?> detailHD(@RequestBody SearchHDByMaAndSdtRequest request ) {
        return ResponseEntity.ok(hoaDonClientService.search(request));
    }
    @PutMapping("/xoa-hoa-don/{id}/{maNV}")
    public ResponseEntity<?> HuyHoaDonQuanLyHoaDon(@PathVariable("id") String id, @RequestBody LichSuHoaDonRequest ls, @PathVariable("maNV") String maNV) {
        HoaDon hoaDon=hoaDonService.findHoaDonbyID(id);
        hoaDon.setNgaySua(LocalDateTime.now());
        ls.setNgayTao(LocalDateTime.now());
        ls.setIdHD(id);
        ls.setNguoiTao(maNV);
        ls.setMoTaHoatDong(ls.getMoTaHoatDong());
        ls.setTrangThai(-1);
        lichSuHoaDonService.addLichSuHoaDon(ls);
        this.thongBaoService.thanhToan(id);
        return  ResponseEntity.ok(hoaDonService.deleteHoaDon(id));
    }
    @GetMapping("/detail-lich-su-hoa-don/{idHD}")
    public ResponseEntity<?> detailLSHD(@PathVariable("idHD") String id){
        return  ResponseEntity.ok(lichSuHoaDonService.getLichHoaDon(id));
    }
    @GetMapping("/hoa-don-san-pham/{idHD}")
    public ResponseEntity<?> SanPhamHoaDon(@PathVariable("idHD") String id){
        return  ResponseEntity.ok(hoaDonService.detailHDSanPham(id));
    }
    @DeleteMapping("/delete-hoa-don-chi-tiet/{idCTSP}/{id}")
    public void  deleteHoaDonChiTiet (@PathVariable("idCTSP") String idCTSP,@PathVariable("id")String id) {
        hoaDonChiTietService.huyDonHang(idCTSP,id); //  roll backed
    }

}

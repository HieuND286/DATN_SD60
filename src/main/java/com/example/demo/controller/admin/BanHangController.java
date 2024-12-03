package com.example.demo.controller.admin;

import com.example.demo.dto.request.HoaDonChiTietRequest;
import com.example.demo.dto.request.HoaDonRequest;
import com.example.demo.dto.request.LichSuHoaDonRequest;
import com.example.demo.dto.request.ThanhToanRequest;
import com.example.demo.dto.response.ChiTietSanPhamForBanHang;
import com.example.demo.dto.response.VoucherRespone;
import com.example.demo.entity.CongThuc;
import com.example.demo.entity.HoaDon;
import com.example.demo.entity.HoaDonChiTiet;
import com.example.demo.entity.LichSuHoaDon;
import com.example.demo.entity.NguoiDung;
import com.example.demo.entity.ThanhToan;
import com.example.demo.entity.Voucher;
import com.example.demo.repository.CTSPRepository;
import com.example.demo.repository.CongThucRepository;
import com.example.demo.service.BanHangService;
import com.example.demo.service.CTSPService;
import com.example.demo.service.HoaDonChiTietService;
import com.example.demo.service.HoaDonServicee;
import com.example.demo.service.LichSuHoaDonService;
import com.example.demo.service.NguoiDungService;
import com.example.demo.service.ThanhToanService;
import com.example.demo.service.VoucherService;
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

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin("http://localhost:3000/")
@RestController
@RequestMapping("/admin/ban-hang")
@RequiredArgsConstructor
public class BanHangController {
    @Autowired
    BanHangService banHangService;
    @Autowired
    HoaDonChiTietService hoaDonChiTietService;
    @Autowired
    CTSPRepository ctspRepository;
    @Autowired
    CongThucRepository congThucRepository;
    @Autowired
    VoucherService voucherService;
    @Autowired
    ThanhToanService thanhToanService;
    @Autowired
    HoaDonServicee hoaDonServicee;
    @Autowired
    NguoiDungService nguoiDungService;
    @Autowired
    LichSuHoaDonService lichSuHoaDonService;
    @Autowired
    CTSPService ctspService;


    @GetMapping("/getHoaDonChoTaiQuay")
    public ResponseEntity<?> getHoaDonChoTaiQuay(){
        return ResponseEntity.ok(hoaDonServicee.getHoaDonChoTaiQuay());
    }
    @GetMapping("/getALLCTSP")
    public ResponseEntity<?> getALLctsp(){
        List<ChiTietSanPhamForBanHang> list=banHangService.getALLCTSPBanHang();
        return ResponseEntity.ok(list);
    }
    @PostMapping("/add-hoa-don")
    public  ResponseEntity<?> addHD(@RequestBody HoaDonRequest hoaDonRequest){
        CongThuc ct=congThucRepository.getCongThucByTrangThai(0);
        hoaDonRequest.setLoaiHoaDon(1);
        hoaDonRequest.setNgayTao(LocalDateTime.now());
        hoaDonRequest.setTrangThai(0);
        HoaDon hd = hoaDonRequest.map(new HoaDon());
      //  hoaDonRequest.setGiaTriDiem(Integer.valueOf(hoaDonRequest.getThanhTien().intValue()/ct.getTiSo().intValue()));
        banHangService.addHoaDon(hd);
        LichSuHoaDon lichSuHoaDon= new LichSuHoaDon();
        lichSuHoaDon.setHoaDon(hd);
        lichSuHoaDon.setNguoiTao(hoaDonRequest.getNhanVien());
        lichSuHoaDon.setTrangThai(0);
        lichSuHoaDon.setNgayTao(LocalDateTime.now());
        return  ResponseEntity.ok(lichSuHoaDonService.save(lichSuHoaDon));

    }
    @PostMapping("/addHDCT")
    public ResponseEntity<?> addHDCT(@RequestBody HoaDonChiTietRequest request){
        return ResponseEntity.ok(hoaDonChiTietService.addHDCT(request));
    }
    @PostMapping("/update-so-luong-hdct")
    public ResponseEntity<?> updateSLHDCT(@RequestBody HoaDonChiTietRequest request){
        return ResponseEntity.ok(hoaDonChiTietService.updateTruSl(request));
    }

    @PostMapping("/update-so-luong-hdct1")
    public ResponseEntity<?> updateSLHDCT1(@RequestBody HoaDonChiTietRequest request){
        return ResponseEntity.ok(hoaDonChiTietService.updateTruSl(request));
    }

    @PostMapping("/delete-hdct")
    public ResponseEntity<?> deleteHDCT(@RequestBody HoaDonChiTietRequest request){
        return ResponseEntity.ok(hoaDonChiTietService.deleteHDCT(request));
    }
    @DeleteMapping("/delete-hoa-don-chi-tiet/{idCTSP}/{ma}")
    public void  deleteHoaDonChiTiet (@PathVariable("idCTSP") String idCTSP,@PathVariable("ma")String ma) {
        hoaDonChiTietService.deleteHDCTAndRollBackInSell(idCTSP,ma); //  roll backed
    }

    @DeleteMapping("/delete/{idCTSP}/{ma}")
    public void  delete (@PathVariable("idCTSP") String idCTSP,@PathVariable("ma")String ma) {
        hoaDonChiTietService.deleteHDCTAndRollBackInSell(idCTSP,ma);
        hoaDonServicee.deleteHoaDon(hoaDonServicee.getHDByMa(ma).getId());//  roll backed
    }
    @DeleteMapping("/delete-hoa-don-chi-tiet/{idCTSP}/{ma}/{thanhTien}")
    public void  deleteHoaDonChiTiet (@PathVariable("idCTSP") String idCTSP,@PathVariable("ma")String ma , @PathVariable("thanhTien") BigDecimal thanhTien) {
        hoaDonChiTietService.deleteHDCTAndRollBackInSell1(idCTSP,ma,thanhTien); //  roll backed

    }

    @PostMapping("/thanh-toan")
    public ResponseEntity<?> thanhToan(@PathVariable HoaDonRequest hoaDonRequest){
        hoaDonRequest.setTrangThai(1);
        hoaDonRequest.setNgayMua(LocalDateTime.now());
        HoaDon hd = hoaDonRequest.map(new HoaDon());
        hd.setNgaySua(LocalDateTime.now());
        return ResponseEntity.ok(banHangService.addHoaDon(hd));
    }
    @GetMapping("/hien-thi-hdct/{ma}")
    public ResponseEntity<?> getHDCTByHD(@PathVariable("ma") String ma){
        return ResponseEntity.ok(hoaDonChiTietService.getAllHDCTByHD(ma));
    }
    @GetMapping("/hoa-don-chi-tiet/{idHD}/{idCTSP}")
    public ResponseEntity<?> getOneHDCT(@PathVariable("idHD")String idHD,
                                        @PathVariable("idCTSP")String idCTSP){
        return ResponseEntity.ok(hoaDonChiTietService.getOneHDCT(idHD,idCTSP));
    }
    @PutMapping("/hoa-don/update-thanh-tien/{idHD}")
    public ResponseEntity<?> updateThanhTien(String idHD) {
        return ResponseEntity.ok(hoaDonServicee.updateThanhTien(idHD));
    }
    @GetMapping("/hoa-don/hoa-don-cho")
    public ResponseEntity<?> getAllBill(){
        return ResponseEntity.ok(hoaDonServicee.getAllBill());
    }

    @GetMapping("/hoa-don/hoa-don-cho-hom-nay")
    public ResponseEntity<?> getAllBillToday(){
        return ResponseEntity.ok(hoaDonServicee.getAllBillToday());
    }
    @GetMapping("/voucher/{idND}")
    public ResponseEntity<?> getAllVoucherWithIDKH(@PathVariable("idND")String idND){
        return ResponseEntity.ok(voucherService.getVoucherBanHang(idND));
    }


    @GetMapping("/voucher/no-limited")
    public ResponseEntity<?> getAllVoucherNoLimited(){
        return ResponseEntity.ok(voucherService.noLimited());
    }

    @PutMapping("/hoa-don/updateSL/{idCTSP}/{ma}/{value}")
    public ResponseEntity<?> updateSL (@PathVariable("idCTSP")String idCTSP,@PathVariable("ma") String ma,@PathVariable("value") int value) {

        return ResponseEntity.ok(hoaDonChiTietService.updateSL(idCTSP,ma,value));
    }

    @PutMapping("/hoa-don/updateSL1/{idCTSP}/{idHD}")
    public ResponseEntity<?> updateSL1 (@PathVariable("idCTSP")String idCTSP,@PathVariable("idHD") String idHD) {

        return ResponseEntity.ok(hoaDonChiTietService.updateSL1(idCTSP,idHD));
    }

    @PutMapping("/hoa-don/update-van-chuyen/{ma}")
    public ResponseEntity<?> updateVanChuyen (@PathVariable("ma")String ma, @RequestBody HoaDon hd){

        return ResponseEntity.ok(hoaDonServicee.update1(hd,ma));
    }

    @PutMapping("/hoa-don/delete-van-chuyen/{ma}")
    public ResponseEntity<?> updateVanChuyen (@PathVariable("ma")String ma) {
        return ResponseEntity.ok(hoaDonServicee.deleteVanChuyen(ma));
    }


    @PutMapping("/hoa-don/them-voucher/{idHD}/{idVoucher}")
    public ResponseEntity<?> updateVoucherToHD(@PathVariable("idHD") String idHD, @PathVariable("idVoucher") String idVoucher) {
        return ResponseEntity.ok(hoaDonServicee.addVoucherToHD(idHD,idVoucher));
    }

    @GetMapping("/voucher-hop-le/{total}")
    public ResponseEntity<?> getVoucherHopLe(@PathVariable("total")String total){
        BigDecimal tien=BigDecimal.valueOf(Double.valueOf(total));
        return ResponseEntity.ok(voucherService.getVoucherHopLe(tien));
    }
    @PostMapping("/voucher/updateTruSLVoucher/{id}")
    public ResponseEntity<?> updateTruSLVoucher(@PathVariable("id")String id){
        return ResponseEntity.ok(voucherService.updateTruSL(id));
    }
    @PostMapping("/voucher/updateCongSLVoucher/{id}")
    public ResponseEntity<?> updateCongSLVoucher(@PathVariable("id")String id){
        return ResponseEntity.ok(voucherService.updateCongSL(id));
    }


    @PutMapping("/nguoi-dung/update-nguoi-dung/{ma}/{idND}")
    public  ResponseEntity<?> updateNguoiDung(@PathVariable("ma")String ma,@PathVariable("idND")String idND) {
        return ResponseEntity.ok(hoaDonServicee.updateKH(ma,idND));
    }

    @PutMapping("/nguoi-dung/update-khach-le/{ma}")
    public  ResponseEntity<?> updateNguoiDung(@PathVariable("ma")String ma) {
        return ResponseEntity.ok(hoaDonServicee.updateReturnKhachLe(ma));
    }



    @PostMapping("/lich-su-hoa-don/them")
    public ResponseEntity<?> themLichSuHoaDon (@RequestBody LichSuHoaDonRequest lichSuHoaDonRequest){
        return ResponseEntity.ok(lichSuHoaDonService.addLichSuHoaDon(lichSuHoaDonRequest));
    }

    @PutMapping("/thanh-toan/hoa-don/{ma}/{idNV}/{idVoucher}")
    public ResponseEntity<?> thanhToanHoaDon (@PathVariable("ma") String ma,@PathVariable("idNV") String idNV,@PathVariable("idVoucher")String idVoucher) {

        HoaDon hoaDon=hoaDonServicee.findHoaDonByMa(ma);

        NguoiDung nguoiDung = nguoiDungService.findByID(idNV);
        if (idVoucher != null || idVoucher != "null") {
            Voucher voucher = voucherService.detailVoucher(idVoucher);

            hoaDon.setVoucher(voucher);
            BigDecimal giamToiDa = voucher.getGiamToiDa();
            BigDecimal giam = voucher.getLoaiVoucher().equals("Tiền mặt") ?
                    ( BigDecimal.valueOf(voucher.getMucDo()).compareTo(giamToiDa) < 0 ?  BigDecimal.valueOf(voucher.getMucDo()) : giamToiDa ) :
                    ((hoaDon.getThanhTien().multiply(BigDecimal.valueOf(voucher.getMucDo())).divide(new BigDecimal(100))).compareTo(giamToiDa) < 0 ? (hoaDon.getThanhTien().multiply(BigDecimal.valueOf(voucher.getMucDo())).divide(new BigDecimal(100))) : giamToiDa);
            hoaDon.setGiaGiamGia(giam);
            hoaDon.setThanhTien(hoaDon.getGiaGoc().subtract(giam));

        }
        if(hoaDon.getTraSau() == 0) {
            if (hoaDon.getDiaChi() != null){
                hoaDon.setTrangThai(2);
                LichSuHoaDon lichSuHoaDon= new LichSuHoaDon();
                lichSuHoaDon.setHoaDon(hoaDon);
                lichSuHoaDon.setNguoiTao(nguoiDung.getMa());
                lichSuHoaDon.setTrangThai(4);
                lichSuHoaDon.setNgayTao(LocalDateTime.now());
                lichSuHoaDonService.save(lichSuHoaDon);
                LichSuHoaDon lichSuHoaDon1= new LichSuHoaDon();
                lichSuHoaDon1.setHoaDon(hoaDon);
                lichSuHoaDon1.setNguoiTao(nguoiDung.getMa());
                lichSuHoaDon1.setTrangThai(2);
                lichSuHoaDon1.setNgayTao(LocalDateTime.now());
                lichSuHoaDonService.save(lichSuHoaDon1);
            } else {
                hoaDon.setTrangThai(5);
                LichSuHoaDon lichSuHoaDon= new LichSuHoaDon();
                lichSuHoaDon.setHoaDon(hoaDon);
                lichSuHoaDon.setNguoiTao(nguoiDung.getMa());
                lichSuHoaDon.setTrangThai(5);
                lichSuHoaDon.setNgayTao(LocalDateTime.now());
                lichSuHoaDonService.save(lichSuHoaDon);
            }

        }  else {
            hoaDon.setTrangThai(2);
            LichSuHoaDon lichSuHoaDon= new LichSuHoaDon();
            lichSuHoaDon.setHoaDon(hoaDon);
            lichSuHoaDon.setNguoiTao(nguoiDung.getMa());
            lichSuHoaDon.setTrangThai(5);
            lichSuHoaDon.setNgayTao(LocalDateTime.now());
            lichSuHoaDonService.save(lichSuHoaDon);
            LichSuHoaDon lichSuHoaDon1= new LichSuHoaDon();
            lichSuHoaDon1.setHoaDon(hoaDon);
            lichSuHoaDon1.setNguoiTao(nguoiDung.getMa());
            lichSuHoaDon1.setTrangThai(2);
            lichSuHoaDon1.setNgayTao(LocalDateTime.now());
            lichSuHoaDonService.save(lichSuHoaDon1);
        }
        hoaDon.setNgaySua(LocalDateTime.now());
        hoaDonServicee.updateTrangThaiHoaDon(hoaDon);
        List<HoaDonChiTiet> listHDCT = hoaDonChiTietService.getAllHDCTByIDHD(hoaDon.getId());
        for (HoaDonChiTiet h : listHDCT) {
            h.setTrangThai(1);
            hoaDonChiTietService.saveHDCT(h);
        }

        List<ThanhToan> listTT = thanhToanService.getThanhToanByIdHD(hoaDon.getId());
        for (ThanhToan tt : listTT){
            tt.setTrangThai(1);
            thanhToanService.save(tt);
        }


        return ResponseEntity.ok(hoaDonServicee.thanhToanHoaDon(ma));

    }


    @PutMapping("/thanh-toan/hoa-don/{ma}/{idNV}")
    public ResponseEntity<?> thanhToanHoaDonKhongVoucher (@PathVariable("ma") String ma,@PathVariable("idNV") String idNV) {

        HoaDon hoaDon=hoaDonServicee.findHoaDonByMa(ma);
        NguoiDung nguoiDung = nguoiDungService.findByID(idNV);
        if (hoaDon == null) return null;

        if(hoaDon.getTraSau() == 0) {
            if (hoaDon.getDiaChi() != null){
                hoaDon.setTrangThai(2);
                LichSuHoaDon lichSuHoaDon= new LichSuHoaDon();
                lichSuHoaDon.setHoaDon(hoaDon);
                lichSuHoaDon.setNguoiTao(nguoiDung.getMa());
                lichSuHoaDon.setTrangThai(4);
                lichSuHoaDon.setNgayTao(LocalDateTime.now());
                lichSuHoaDonService.save(lichSuHoaDon);
                LichSuHoaDon lichSuHoaDon1= new LichSuHoaDon();
                lichSuHoaDon1.setHoaDon(hoaDon);
                lichSuHoaDon1.setNguoiTao(nguoiDung.getMa());
                lichSuHoaDon1.setTrangThai(2);
                lichSuHoaDon1.setNgayTao(LocalDateTime.now());
                lichSuHoaDonService.save(lichSuHoaDon1);
            } else {
                hoaDon.setTrangThai(5);
                LichSuHoaDon lichSuHoaDon= new LichSuHoaDon();
                lichSuHoaDon.setHoaDon(hoaDon);
                lichSuHoaDon.setNguoiTao(nguoiDung.getMa());
                lichSuHoaDon.setTrangThai(5);
                lichSuHoaDon.setNgayTao(LocalDateTime.now());
                lichSuHoaDonService.save(lichSuHoaDon);
            }

        }  else {
            hoaDon.setTrangThai(2);
            LichSuHoaDon lichSuHoaDon= new LichSuHoaDon();
            lichSuHoaDon.setHoaDon(hoaDon);
            lichSuHoaDon.setNguoiTao(nguoiDung.getMa());
            lichSuHoaDon.setTrangThai(5);
            lichSuHoaDon.setNgayTao(LocalDateTime.now());
            lichSuHoaDonService.save(lichSuHoaDon);
            LichSuHoaDon lichSuHoaDon1= new LichSuHoaDon();
            lichSuHoaDon1.setHoaDon(hoaDon);
            lichSuHoaDon1.setNguoiTao(nguoiDung.getMa());
            lichSuHoaDon1.setTrangThai(2);
            lichSuHoaDon1.setNgayTao(LocalDateTime.now());
            lichSuHoaDonService.save(lichSuHoaDon1);
        }
        hoaDon.setNgaySua(LocalDateTime.now());
        hoaDonServicee.updateTrangThaiHoaDon(hoaDon);
        List<HoaDonChiTiet> listHDCT = hoaDonChiTietService.getAllHDCTByIDHD(hoaDon.getId());
        for (HoaDonChiTiet h : listHDCT) {
            h.setTrangThai(1);
            hoaDonChiTietService.saveHDCT(h);
        }

        List<ThanhToan> listTT = thanhToanService.getThanhToanByIdHD(hoaDon.getId());
        for (ThanhToan tt : listTT){
            tt.setTrangThai(1);
            thanhToanService.save(tt);
        }


        return ResponseEntity.ok(hoaDonServicee.thanhToanHoaDon(ma));

    }

    @PutMapping("/tra-sau/hoa-don/{ma}/{idNV}/{tien}")
    public ResponseEntity<?> traSauHoaDon (@PathVariable("ma") String ma,@PathVariable("idNV") String idNV,@PathVariable("tien")BigDecimal tien) {
        HoaDon hd = hoaDonServicee.getHDByMa(ma);
        ThanhToanRequest request = new ThanhToanRequest();
        request.setHoaDon(hd.getId());
        request.setTienMat(tien);
        request.setTongTien(tien);
        //NguoiDung nguoiDung = nguoiDungService.findByID(idNV);
        request.setNguoiTao(idNV);
        request.setNgayTao(LocalDateTime.now());
        request.setPhuongThuc(0);
        request.setTrangThai(0);

        thanhToanService.thanhToanAdmin(request);
        return ResponseEntity.ok(  hoaDonServicee.updateTraSau(ma,idNV));

    }

    @PutMapping("/tra-sau/hoa-don/{ma}/{idNV}/{tien}/{idVoucher}")
    public ResponseEntity<?> traSauHoaDonCoVoucher (@PathVariable("ma") String ma,@PathVariable("idNV") String idNV,@PathVariable("tien")BigDecimal tien,@PathVariable("idVoucher")String idVoucher) {
        HoaDon hd = hoaDonServicee.getHDByMa(ma);
        ThanhToanRequest request = new ThanhToanRequest();
        request.setHoaDon(hd.getId());
        request.setTienMat(tien);
        request.setTongTien(tien);
        //NguoiDung nguoiDung = nguoiDungService.findByID(idNV);
        request.setNguoiTao(idNV);
        request.setNgayTao(LocalDateTime.now());
        request.setPhuongThuc(0);
        request.setTrangThai(0);

        thanhToanService.thanhToanAdmin(request);
        return ResponseEntity.ok(  hoaDonServicee.updateTraSauCoVoucher(ma,idNV,idVoucher));

    }

    @GetMapping("/hoa-don/san-pham/so-luong/{idSP}/{ma}")
    public ResponseEntity<?> soLuongSanPham(@PathVariable("idSP") String idSP, @PathVariable("ma")String ma){
        return ResponseEntity.ok(ctspService.getSLVaSLT(idSP,ma));
    }
    @PutMapping("/hoa-don/update-tien-van-chuyen/{ma}/{tien}")
    public ResponseEntity<?> updateTienVanChuyen(@PathVariable("ma") String ma,@PathVariable("tien")String tien){
        HoaDon hd = hoaDonServicee.findHoaDonByMa(ma);
        if (hd == null) return null;
        hd.setTienVanChuyen(new BigDecimal(tien));
        return ResponseEntity.ok(hoaDonServicee.updateTrangThaiHoaDon(hd));
    }

    @GetMapping("/hoa-don/so-tien/{ma}")
    public ResponseEntity<?> getThanhTienByIDHD(@PathVariable("ma") String ma){
        if (hoaDonServicee.findHoaDonByMa(ma) == null) return null;
        return ResponseEntity.ok(hoaDonServicee.findHoaDonByMa(ma).getThanhTien());
    }

    @GetMapping("/hoa-don/voucher-tot-nhat/{idKH}/{money}")
    public ResponseEntity<?> getVoucherTotNhat(@PathVariable("money")String money,@PathVariable("idKH")String idKH){
        String idV = "";
        BigDecimal tienDuocKM = new BigDecimal("0");
        if (!idKH.isEmpty()){
           // NguoiDung kh = nguoiDungService.findByID(idKH);
            List<VoucherRespone> listV = voucherService.getVoucherBanHang(idKH);
            for (VoucherRespone v : listV){
                BigDecimal mucDoKM = v.getLoaiVoucher().equalsIgnoreCase("Tiền mặt") ? new BigDecimal(v.getMucDo()) : new BigDecimal(money).multiply(new BigDecimal((v.getMucDo()))).divide(new BigDecimal("100"));
                if (mucDoKM.compareTo(v.getGiamToiDa()) > 0){
                    mucDoKM = v.getGiamToiDa();
                }
                if (new BigDecimal(money).compareTo(v.getDieuKien())>=0 && mucDoKM.compareTo(tienDuocKM)>0) {
                    tienDuocKM = mucDoKM;
                    idV = v.getId();
                }
            }
        } else {
            List<VoucherRespone> list = voucherService.noLimited();
            for (VoucherRespone v : list){

                BigDecimal mucDoKM = v.getLoaiVoucher().equalsIgnoreCase("Tiền mặt") ? new BigDecimal(v.getMucDo()) : new BigDecimal(money).multiply(new BigDecimal((v.getMucDo()))).divide(new BigDecimal("100"));
                if (mucDoKM.compareTo(v.getGiamToiDa()) > 0){
                    mucDoKM = v.getGiamToiDa();
                }
                if (new BigDecimal(money).compareTo(v.getDieuKien())>=0 && mucDoKM.compareTo(tienDuocKM)>0) {
                    tienDuocKM = mucDoKM;
                    idV = v.getId();
                }
            }
        }
        if(idV.equals("")) return null;
        return ResponseEntity.ok(voucherService.detailVoucher(idV));
    }

    @GetMapping("/hoa-don/khuyen-mai-sap-dat-duoc/{idKH}/{money}/{idV}")
    public List<BigDecimal> khuyenMaiSapDatDuoc (@PathVariable("idKH")String idKH, @PathVariable("money")String money,@PathVariable("idV")String idV){
        BigDecimal soTienConThieu = new BigDecimal("0");
        BigDecimal soTienDuocGiam = new BigDecimal("0");
        Voucher vc = new Voucher();
        if(idV != null) {
             vc = voucherService.getVoucherByID(idV);
        }

        BigDecimal soTienDangDuocGiam = new BigDecimal("0");
        if (vc != null) {
            soTienDangDuocGiam = vc.getLoaiVoucher().equalsIgnoreCase("Tiền mặt") ?
                    new BigDecimal(vc.getMucDo()) :
                    (new BigDecimal(String.valueOf(vc.getMucDo()* Float.valueOf(money) / 100)).compareTo(vc.getGiamToiDa()) < 0)
                    ? new BigDecimal(String.valueOf(vc.getMucDo()* Float.valueOf(money) / 100)) : vc.getGiamToiDa();
        }
        if (!idKH.equalsIgnoreCase("null")){
            List<VoucherRespone> listV = voucherService.getVoucherBanHang(idKH);
            for (VoucherRespone v : listV){
                if (v.getDieuKien().compareTo(new BigDecimal(money)) <= 0) continue;  // loại bỏ những voucher đã hợp yêu cầu
                // giả sử vừa đủ điều kiện => giá được giảm
                BigDecimal mucDoKM = v.getLoaiVoucher().equalsIgnoreCase("Tiền mặt") ? new BigDecimal(v.getMucDo()) : v.getDieuKien().multiply(new BigDecimal((v.getMucDo()))).divide(new BigDecimal("100"));
                if (mucDoKM.compareTo(v.getGiamToiDa()) > 0){
                    mucDoKM = v.getGiamToiDa(); // trường hợp lớn hơn giá giảm tối đa
                }
                if (soTienConThieu.compareTo(new BigDecimal("0")) == 0 && soTienDuocGiam.compareTo(new BigDecimal("0")) ==0 && mucDoKM.compareTo(soTienDangDuocGiam) > 0) {
                    soTienConThieu = v.getDieuKien().subtract(new BigDecimal(money));
                    soTienDuocGiam = mucDoKM;
                } else if (soTienConThieu.compareTo(v.getDieuKien().subtract(new BigDecimal(money))) >= 0 && soTienDuocGiam.compareTo(mucDoKM) <= 0 && mucDoKM.compareTo(soTienDangDuocGiam) > 0) {
                    soTienConThieu = v.getDieuKien().subtract(new BigDecimal(money));
                    soTienDuocGiam = mucDoKM;
                }

            }
        } else {
            List<VoucherRespone> list = voucherService.noLimited();
            for (VoucherRespone v : list){
                if (v.getDieuKien().compareTo(new BigDecimal(money)) <= 0) continue;  // loại bỏ những voucher đã hợp yêu cầu
                // giả sử vừa đủ điều kiện => giá được giảm
                BigDecimal mucDoKM = v.getLoaiVoucher().equalsIgnoreCase("Tiền mặt") ? new BigDecimal(v.getMucDo()) : v.getDieuKien().multiply(new BigDecimal((v.getMucDo()))).divide(new BigDecimal("100"));
                if (mucDoKM.compareTo(v.getGiamToiDa()) > 0){
                    mucDoKM = v.getGiamToiDa(); // trường hợp lớn hơn giá giảm tối đa
                }

                if ((soTienConThieu.compareTo(new BigDecimal("0")) == 0) && (soTienDuocGiam.compareTo(new BigDecimal("0")) ==0) && (mucDoKM.compareTo(soTienDuocGiam) > 0)) {
                    soTienConThieu = v.getDieuKien().subtract(new BigDecimal(money));
                    soTienDuocGiam = mucDoKM;

                } else if (soTienConThieu.compareTo(v.getDieuKien().subtract(new BigDecimal(money))) >= 0 && soTienDuocGiam.compareTo(mucDoKM) <= 0 && mucDoKM.compareTo(soTienDangDuocGiam) > 0)  {
                    soTienConThieu = v.getDieuKien().subtract(new BigDecimal(money));
                    soTienDuocGiam = mucDoKM;

                 }
            }
        }
        List<BigDecimal> list = new ArrayList<>();
        list.add(soTienConThieu);
        list.add(soTienDuocGiam);
        return list;
    }

    @GetMapping("/detail-hoa-don/{ma}")
    public ResponseEntity<?> detailHD(@PathVariable("ma") String ma){
        return ResponseEntity.ok(hoaDonServicee.findHoaDonByMa(ma));
    }
}

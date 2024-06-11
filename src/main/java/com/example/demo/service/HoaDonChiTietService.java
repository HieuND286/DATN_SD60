package com.example.demo.service;

import com.example.demo.dto.request.HoaDonChiTietRequest;
import com.example.demo.dto.response.HoaDonChiTietBanHangRespone;
import com.example.demo.dto.response.HoaDonChiTietRespone;
import com.example.demo.entity.ChiTietSanPham;
import com.example.demo.entity.GioHangChiTiet;
import com.example.demo.entity.HoaDon;
import com.example.demo.entity.HoaDonChiTiet;
import com.example.demo.repository.CTSPRepository;
import com.example.demo.repository.GioHangChiTietRepository;
import com.example.demo.repository.HoaDonChiTietRepository;
import com.example.demo.repository.HoaDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class HoaDonChiTietService {
    @Autowired
    HoaDonChiTietRepository hoaDonChiTietRepository;
    @Autowired
    GioHangChiTietRepository gioHangChiTietRepository;
    @Autowired
    CTSPRepository ctspRepository;
    @Autowired
    HoaDonRepository hoaDonRepository;
    @Autowired
    ThongBaoService thongBaoService;
    public List<HoaDonChiTietBanHangRespone> getAllHDCTByHD(String ma){
        System.out.println("Ma"+ma);
        if (hoaDonRepository.getHDByMa(ma) == null) return  null;
        String id = hoaDonRepository.getHDByMa(ma).getId();
        return hoaDonChiTietRepository.getAllHDCTByHD(id);
    }
    public HoaDonChiTietRespone getOneHDCT(String idHD, String idCTSP){
        return hoaDonChiTietRepository.getOneHDCT(idHD,idCTSP);
    }
    public HoaDonChiTiet addHDCT(HoaDonChiTietRequest request){
        System.out.println("Hóa đơn chi tiết request"+request);
        String idCTSP= request.getChiTietSanPham();
        HoaDon hoaDon = hoaDonRepository.getHDByMa(request.getHoaDon());
        if (hoaDon == null) return null;
        request.setHoaDon(hoaDon.getId());
        HoaDonChiTiet hdct=request.map(new HoaDonChiTiet());
        BigDecimal giaHienTai = hoaDon.getGiaGoc() == null ? new BigDecimal("0") : hoaDon.getGiaGoc();
        BigDecimal giaThem = request.getGiaSauGiam();
        hoaDon.setGiaGoc(giaHienTai.add(giaThem));
        hoaDon.setThanhTien(giaHienTai.add(giaThem));
        ChiTietSanPham ctsp=ctspRepository.findById(idCTSP).get();
        HoaDonChiTiet checkHDCT = hoaDonChiTietRepository.getHDCTByCTSPAndHD(idCTSP,hoaDon.getId());
        if(checkHDCT != null){
            System.out.println("ID Hóa đơn"+hoaDon.getId());
            return updateSL1(idCTSP,hoaDon.getId());
        }
        hdct.setNgayTao(LocalDateTime.now());
        hdct.setTrangThai(0);
        ctsp.setSoLuong(ctsp.getSoLuong()- request.getSoLuong());
        ctspRepository.save(ctsp);
        hoaDonRepository.save(hoaDon);
        return  hoaDonChiTietRepository.save(hdct);
    }


    public HoaDonChiTiet updateSL1(String idCTSP,String idHD) {
        HoaDonChiTiet hdct = hoaDonChiTietRepository.getHDCTByCTSPAndHD(idCTSP, idHD);
        ChiTietSanPham ctsp = ctspRepository.getReferenceById(idCTSP);
        int slt = ctsp.getSoLuong();
        int slh = hdct.getSoLuong();
        int sltd = slh + 1;
        hdct.setSoLuong(sltd);
        ctsp.setSoLuong(slt - 1);
        System.out.println("HDCT sau update" + hdct);
        System.out.println("CTSP sau update" + ctsp);
        HoaDon hoaDon = hoaDonRepository.getHoaDonByIDHD(idHD);
        BigDecimal tong = new BigDecimal("0");
        List<HoaDonChiTiet> list = hoaDonChiTietRepository.getAllHDCTByIDHD(idHD);
        for (HoaDonChiTiet x : list) {
            if (x.getChiTietSanPham().getId().equals(idCTSP)) {
                tong = tong.add(x.getGiaSauGiam().multiply(BigDecimal.valueOf(sltd)));

            } else {
                tong = tong.add(x.getGiaSauGiam().multiply(BigDecimal.valueOf(x.getSoLuong())));
            }
        }
        BigDecimal giaGiam = hoaDon.getGiaGiamGia() == null ? new BigDecimal("0") : hoaDon.getGiaGiamGia();
        hoaDon.setGiaGoc(tong);
        hoaDon.setThanhTien(tong.subtract(giaGiam));
        hoaDonRepository.save(hoaDon);
        ctspRepository.save(ctsp);
        return hoaDonChiTietRepository.save(hdct);
    }
    public HoaDonChiTiet addHDCTClient(HoaDonChiTietRequest request){
        HoaDonChiTiet hdct=request.map(new HoaDonChiTiet());
        return  hoaDonChiTietRepository.save(hdct);

    }
    public HoaDonChiTiet updateTruSl(HoaDonChiTietRequest request){
        HoaDonChiTiet hdct=request.map(new HoaDonChiTiet());
        HoaDonChiTiet hdctTonTai=hoaDonChiTietRepository.findById(request.getId()).get();
        hdctTonTai.setSoLuong(hdctTonTai.getSoLuong()-(hdct.getSoLuong()- hdctTonTai.getSoLuong()));
        String idCTSP= request.getChiTietSanPham();
        ChiTietSanPham ctsp=ctspRepository.findById(idCTSP).get();
        ctsp.setSoLuong(ctsp.getSoLuong()+ (hdct.getSoLuong()- hdctTonTai.getSoLuong()));
        ctspRepository.save(ctsp);
        hdctTonTai.setNgaySua(LocalDateTime.now());

        return  hoaDonChiTietRepository.save(hdctTonTai);
    }
    public HoaDonChiTiet deleteHDCT(HoaDonChiTietRequest request){
        HoaDonChiTiet hdctTonTai=hoaDonChiTietRepository.findById(request.getId()).get();
        ChiTietSanPham ctsp=ctspRepository.findById(request.getId()).get();
        ctsp.setSoLuong(ctsp.getSoLuong()+request.getSoLuong());
        hdctTonTai.setNgaySua(LocalDateTime.now());
        hdctTonTai.setTrangThai(2);
        return  hoaDonChiTietRepository.save(hdctTonTai);
    }

    public void deleteHDCTAndRollBackInSell(String idCTSP,String ma){
        String idHD = hoaDonRepository.getHDByMa(ma).getId();
        HoaDonChiTiet hdct = hoaDonChiTietRepository.getHDCTByCTSPAndHD(idCTSP,idHD);
        System.out.println("Hóa đơn chi tiết"+hdct);
        ChiTietSanPham ctsp = ctspRepository.getReferenceById(idCTSP);
        int slt = ctsp.getSoLuong();
        int slh = hdct.getSoLuong();
        ctsp.setSoLuong(slt+slh);
        ctspRepository.save(ctsp);
        BigDecimal tong = new BigDecimal("0");
        HoaDon hoaDon = hoaDonRepository.getHoaDonByIDHD(idHD);
        List<HoaDonChiTiet> list = hoaDonChiTietRepository.getAllHDCTByIDHD(idHD);
        for (HoaDonChiTiet x : list) {
            if (x.getChiTietSanPham().getId().equals(idCTSP)){
               // tong = tong.add(x.getGiaSauGiam().multiply(BigDecimal.valueOf(soLuongCapNhat)));
                continue;
            }
            tong = tong.add(x.getGiaSauGiam().multiply(BigDecimal.valueOf(x.getSoLuong())));
        }
        BigDecimal giaGiam = hoaDon.getGiaGiamGia() == null ? new BigDecimal("0") : hoaDon.getGiaGiamGia();
        hoaDon.setGiaGoc(tong);
        hoaDon.setThanhTien(tong.subtract(giaGiam));
        hoaDonChiTietRepository.delete(hdct);
        hoaDonRepository.save(hoaDon);
    }

    public void deleteHDCTAndRollBackInSell1(String idCTSP,String ma , BigDecimal thanhTien){
        String idHD = hoaDonRepository.getHDByMa(ma).getId();
        HoaDonChiTiet hdct = hoaDonChiTietRepository.getHDCTByCTSPAndHDAndThanhTien(idCTSP,idHD,thanhTien);
        System.out.println("Hóa đơn chi tiết"+hdct);
        ChiTietSanPham ctsp = ctspRepository.getReferenceById(idCTSP);
        int slt = ctsp.getSoLuong();
        int slh = hdct.getSoLuong();
        ctsp.setSoLuong(slt+slh);
        ctspRepository.save(ctsp);
        BigDecimal tong = new BigDecimal("0");
        HoaDon hoaDon = hoaDonRepository.getHoaDonByIDHD(idHD);
        List<HoaDonChiTiet> list = hoaDonChiTietRepository.getAllHDCTByIDHD(idHD);
        for (HoaDonChiTiet x : list) {
            if (x.getChiTietSanPham().getId().equals(idCTSP)){
                // tong = tong.add(x.getGiaSauGiam().multiply(BigDecimal.valueOf(soLuongCapNhat)));
                continue;
            }
            tong = tong.add(x.getGiaSauGiam().multiply(BigDecimal.valueOf(x.getSoLuong())));
        }
        BigDecimal giaGiam = hoaDon.getGiaGiamGia() == null ? new BigDecimal("0") : hoaDon.getGiaGiamGia();
        hoaDon.setGiaGoc(tong);
        //hoaDon.setTrangThai(-1);
        hoaDon.setThanhTien(tong.subtract(giaGiam));
        hoaDonChiTietRepository.delete(hdct);
        hoaDonRepository.save(hoaDon);

    }

    public void huyDonHang(String idCTSP,String idHD){
        HoaDonChiTiet hdct = hoaDonChiTietRepository.getHDCTByCTSPAndHD(idCTSP,idHD);
        System.out.println("Hóa đơn chi tiết"+hdct);
        ChiTietSanPham ctsp = ctspRepository.getReferenceById(idCTSP);
        int slt = ctsp.getSoLuong();
        int slh = hdct.getSoLuong();
        ctsp.setSoLuong(slt+slh);
        ctspRepository.save(ctsp);
    }
    public void updateGia(String idCTSP, BigDecimal giaGiam , BigDecimal giaSauGiam){
        List<HoaDonChiTiet> list = hoaDonChiTietRepository.getAllHDCTByCTSP(idCTSP);
        List<GioHangChiTiet> listGH=gioHangChiTietRepository.getAllGHCTByCTSP(idCTSP);
        for (HoaDonChiTiet h : list){
            if (h.getTrangThai() == 0) {
                BigDecimal before = h.getGiaSauGiam().subtract(h.getGiaGiam());
                BigDecimal after = giaSauGiam.subtract(giaGiam);
                HoaDon hd = hoaDonRepository.getHoaDonByIDHD(h.getHoaDon().getId());
                hd.setGiaGoc(hd.getGiaGoc().subtract(h.getGiaSauGiam()).add(giaSauGiam));
                hd.setThanhTien(hd.getThanhTien().subtract(h.getGiaSauGiam()).add(giaSauGiam));
                hoaDonRepository.save(hd);
                h.setGiaGiam(giaGiam);
                h.setGiaSauGiam(giaSauGiam);
                hoaDonChiTietRepository.save(h);
            }
            System.out.println( hoaDonChiTietRepository.save(h));
        }
        if(listGH.size()>0) {
        for(GioHangChiTiet gh :listGH){
            System.out.println("giỏ hàng"+gh);
            gh.setThanhTien(giaSauGiam.multiply(BigDecimal.valueOf(gh.getSoLuong())));
            gioHangChiTietRepository.save(gh);
        }
        }
        thongBaoService.socketLoadSanPham(idCTSP);
    }

    public HoaDonChiTiet updateSL(String idCTSP,String ma,int soLuongCapNhat){
        String idHD = hoaDonRepository.getHDByMa(ma).getId();
        HoaDonChiTiet hdct = hoaDonChiTietRepository.getHDCTByCTSPAndHD(idCTSP,idHD);
        ChiTietSanPham ctsp = ctspRepository.getReferenceById(idCTSP);
        int slt = ctsp.getSoLuong();
        int slh = hdct.getSoLuong();
        int sltd = soLuongCapNhat-slh;
        hdct.setSoLuong(soLuongCapNhat);
        ctsp.setSoLuong(slt-sltd);
        System.out.println("HDCT sau update"+hdct);
        System.out.println("CTSP sau update"+ctsp);
        HoaDon hoaDon = hoaDonRepository.getHoaDonByIDHD(idHD);
        BigDecimal tong = new BigDecimal("0");
        List<HoaDonChiTiet> list = hoaDonChiTietRepository.getAllHDCTByIDHD(idHD);
        for (HoaDonChiTiet x : list) {
            if (x.getChiTietSanPham().getId().equals(idCTSP)){
                tong = tong.add(x.getGiaSauGiam().multiply(BigDecimal.valueOf(soLuongCapNhat)));

            } else {
                tong = tong.add(x.getGiaSauGiam().multiply(BigDecimal.valueOf(x.getSoLuong())));
            }
        }
        BigDecimal giaGiam = hoaDon.getGiaGiamGia() == null ? new BigDecimal("0") : hoaDon.getGiaGiamGia();
        hoaDon.setGiaGoc(tong);
        hoaDon.setThanhTien(tong.subtract(giaGiam));
        hoaDonRepository.save(hoaDon);
        ctspRepository.save(ctsp);
       return hoaDonChiTietRepository.save(hdct);
    }

    public List<HoaDonChiTiet> getAllHDCTByIDHD(String idHD){
        List<HoaDonChiTiet> list = hoaDonChiTietRepository.findHoaDonChiTietByHoaDon_Id(idHD);
        return list;
    }

    public HoaDonChiTiet saveHDCT(HoaDonChiTiet hdct){
       return hoaDonChiTietRepository.save(hdct);
    }

    public List<HoaDonChiTiet> getAllHDCTByIDCTSP(String idCTSP){
        List<HoaDonChiTiet> list = hoaDonChiTietRepository.getAllHDCTByCTSP(idCTSP);
        return list;
    }

    public HoaDonChiTiet getHDCTByID(String idHDCT){
        return hoaDonChiTietRepository.findById(idHDCT).get();
    }
}

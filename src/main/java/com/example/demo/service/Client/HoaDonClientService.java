package com.example.demo.service.Client;

import com.example.demo.dto.request.HoaDonCLient.SearchHDByMaAndSdtRequest;
import com.example.demo.dto.request.HoaDonCLient.TrangThaiRequest;
import com.example.demo.dto.response.HoaDonCLient.DetailHoaDonClientByIdHDRespon;
import com.example.demo.dto.response.HoaDonCLient.HoaDonClientHistory;
import com.example.demo.model.AdminHoaDonSanPham;
import com.example.demo.repository.HoaDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoaDonClientService {
    @Autowired
    HoaDonRepository hoaDonRepository;
    public List<HoaDonClientHistory> getALLHDClientByIDKH(TrangThaiRequest req){
        return hoaDonRepository.getALLHDClientByIDKH( req);
    }
    public List<AdminHoaDonSanPham> detailHDSanPhamClient(String key){
        return  hoaDonRepository.detailHDSanPhamClient(key);
    }
    public DetailHoaDonClientByIdHDRespon detailHoaDonClienByIdHD(String idHD){
        return hoaDonRepository.detailHoaDonClienByIdHD(idHD);
    }
    public DetailHoaDonClientByIdHDRespon search(SearchHDByMaAndSdtRequest req){
        return hoaDonRepository.searchHDClient(req);
    }
}

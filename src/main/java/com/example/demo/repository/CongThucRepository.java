package com.example.demo.repository;

import com.example.demo.entity.CongThuc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CongThucRepository extends JpaRepository<CongThuc,String> {
    CongThuc getCongThucByTrangThai(int trangThai);
}

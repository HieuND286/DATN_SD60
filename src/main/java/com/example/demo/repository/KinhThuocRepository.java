package com.example.demo.repository;

import com.example.demo.dto.request.sanphamsearch.BangConSearch;
import com.example.demo.dto.response.sanpham.KinhThuocRespone;
import com.example.demo.entity.KichThuoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KinhThuocRepository extends JpaRepository<KichThuoc, String> {
    @Query(value = """
    SELECT o.id as id,o.ma as ma ,o.ten as ten, o.trang_thai as trangThai 
    FROM kich_thuoc o ORDER BY o.ma ASC
            """, nativeQuery = true)
    List<KinhThuocRespone> getALLKT();

    @Query(value = """
    SELECT o.id as id,o.ma as ma ,o.ten as ten, o.trang_thai as trangThai FROM kich_thuoc o WHERE 
     (:#{#bangConSearch.ten} IS NULL OR o.ma LIKE (%:#{#bangConSearch.ten}%) OR o.ten LIKE (%:#{#bangConSearch.ten}%) ) AND
     ( :#{#bangConSearch.trangThai} IS NULL OR o.trang_thai=:#{#bangConSearch.trangThai})
    ORDER BY o.ma DESC
            """, nativeQuery = true)
    List<KinhThuocRespone> timKT(BangConSearch bangConSearch);
}

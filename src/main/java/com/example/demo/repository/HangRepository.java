package com.example.demo.repository;

import com.example.demo.dto.request.sanphamsearch.BangConSearch;
import com.example.demo.dto.response.HangRespone;
import com.example.demo.entity.Hang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HangRepository extends JpaRepository<Hang, String> {
    @Query(value = """
    SELECT o.id as id,o.ma as ma ,o.ten as ten, o.trang_thai as trangThai FROM hang o ORDER BY o.ngay_tao DESC
            """, nativeQuery = true)
    List<HangRespone> getALLH();

    @Query(value = """
    SELECT o.id as id,o.ma as ma ,o.ten as ten, o.trang_thai as trangThai FROM hang o WHERE 
     (:#{#bangConSearch.ten} IS NULL OR o.ma LIKE (%:#{#bangConSearch.ten}%) OR o.ten LIKE (%:#{#bangConSearch.ten}%) ) AND
     ( :#{#bangConSearch.trangThai} IS NULL OR o.trang_thai=:#{#bangConSearch.trangThai})
    ORDER BY o.ma DESC
            """, nativeQuery = true)
    List<HangRespone> timH(BangConSearch bangConSearch);
}

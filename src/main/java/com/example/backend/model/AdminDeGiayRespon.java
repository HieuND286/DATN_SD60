package com.example.backend.model;

import org.springframework.beans.factory.annotation.Value;

public interface AdminDeGiayRespon {
    @Value("#{target.ma}")
    String getMa();

    @Value("#{target.ten}")
    String getTen();

    @Value("#{target.ma}")
    String getTrangThai();
}

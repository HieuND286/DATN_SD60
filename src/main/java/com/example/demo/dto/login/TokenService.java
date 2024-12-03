package com.example.demo.dto.login;

public interface TokenService {
    String genToken(String username);
    String getUserNameByToken(String token);
}

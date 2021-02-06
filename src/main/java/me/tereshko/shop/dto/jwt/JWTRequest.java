package me.tereshko.shop.dto.jwt;

import lombok.Data;

@Data
public class JWTRequest {
    private String username;
    private String password;
}

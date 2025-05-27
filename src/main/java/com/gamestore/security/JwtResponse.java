package com.gamestore.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private String rol;

    public JwtResponse(String accessToken,String rol) {
        this.accessToken = accessToken;
        this.tokenType = "Bearer";
        this.rol= rol;
    }
}

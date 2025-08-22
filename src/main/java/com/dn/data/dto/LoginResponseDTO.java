package com.dn.data.dto;


import java.io.Serializable;

public class LoginResponseDTO implements Serializable
{
    private String token;

    public LoginResponseDTO( String token )
    {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

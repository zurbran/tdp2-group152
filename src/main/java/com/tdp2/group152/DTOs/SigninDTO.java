package com.tdp2.group152.DTOs;

public class SigninDTO {
    private String email;
    private String authToken;
    private Long passengerId;

    public SigninDTO(String email, String authToken, Long passengerId) {
        this.email = email;
        this.authToken = authToken;
        this.passengerId = passengerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public Long getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Long passengerId) {
        this.passengerId = passengerId;
    }
}

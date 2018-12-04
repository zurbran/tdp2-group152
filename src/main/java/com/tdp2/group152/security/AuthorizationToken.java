package com.tdp2.group152.security;

import javax.persistence.*;

@Entity
@Table(name = "auth_token")
public class AuthorizationToken {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "email")
    private String email;

    @Column(name = "token")
    private String token;

    protected AuthorizationToken() {
    }

    public AuthorizationToken(String email, String token) {
        this.email = email;
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }

    public long getId() {
        return id;
    }
}

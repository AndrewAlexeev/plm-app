package com.mai.projects.plm.security.jwt.model;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String userName;
    private String password;
}
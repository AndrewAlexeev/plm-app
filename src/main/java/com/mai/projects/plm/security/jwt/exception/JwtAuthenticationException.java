package com.mai.projects.plm.security.jwt.exception;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationException extends AuthenticationException {
    public JwtAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }

    public JwtAuthenticationException(String msg) {
        super(msg);
    }
}

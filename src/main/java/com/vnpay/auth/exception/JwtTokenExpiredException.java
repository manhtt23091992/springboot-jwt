package com.vnpay.auth.exception;

import org.springframework.security.core.AuthenticationException;

public class JwtTokenExpiredException extends AuthenticationException {


    public JwtTokenExpiredException() {
        super("Token expired.");
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpay.auth.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author tienmanh
 */
public class JwtTokenGenerator {

    public static String generateToken(JwtUser u, String secret, int jwt_expiration) {
        Claims claims = Jwts.claims().setSubject(u.getUsername());
        claims.put("role", u.getRole());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, secret)
                .setExpiration(DateTime.now().plusSeconds(jwt_expiration).toDate())
                .compact();
    }
}

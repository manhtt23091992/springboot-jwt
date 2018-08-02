/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpay.auth.util;

/**
 *
 * @author tienmanh
 */
public class JwtUser {

    private String username;
    private String role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static JwtUser buildFromAuthentication(String role, String username) {
        JwtUser jwtUserDto = new JwtUser();
        jwtUserDto.setRole(role);
        jwtUserDto.setUsername(username);
        return jwtUserDto;
    }
}

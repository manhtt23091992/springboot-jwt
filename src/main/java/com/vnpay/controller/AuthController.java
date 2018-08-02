/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpay.controller;

import com.google.gson.Gson;
import com.vnpay.auth.util.JwtTokenGenerator;
import com.vnpay.auth.util.JwtUser;
import com.vnpay.beans.ReqLogin;
import com.vnpay.beans.RspLogin;
import com.vnpay.beans.RspObj;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tienmanh
 */
@RestController
public class AuthController {

    private static final Logger log = LogManager.getLogger("springauth");

    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expiration}")
    private int jwt_expiration;

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public RspObj Login(@RequestBody(required = true) ReqLogin reqObject) {
        RspLogin rspLogin = new RspLogin();
        RspObj rspObj = new RspObj();
        Gson gson = new Gson();
        try {
            //Tienmanh
            //We can use database to store user profile and authenticate
            //In example, I just fake this infomation
            if ("admin".equals(reqObject.getUsername()) && "123456".equals(reqObject.getPassword())) {
                JwtUser jwtUser = JwtUser.buildFromAuthentication("ADMIN", "tienmanh");
                rspLogin.setUsername(reqObject.getUsername());
                rspLogin.setToken(JwtTokenGenerator.generateToken(jwtUser, jwtSecret, jwt_expiration));
                rspLogin.setExpiration(jwt_expiration);

                rspObj.setCode("00");
                rspObj.setMessage("Login success");
                rspObj.setData(rspLogin);
                return rspObj;
            } else {
                rspObj.setCode("99");
                rspObj.setMessage("Login Fail!");
                return rspObj;
            }

        } catch (Exception e) {
            rspObj.setCode("99");
            rspObj.setMessage("Login Fail!");
            return rspObj;
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpay.beans;

/**
 *
 * @author tienmanh
 */
public class RspObj {

    private String code;
    private String message;
    private Object data;

    public RspObj() {
    }

    public RspObj(String code, String message, String data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public RspObj(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}

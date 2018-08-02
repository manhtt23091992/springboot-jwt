/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpay.controller;

import com.vnpay.beans.RspObj;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tienmanh
 */
@RestController
@RequestMapping("/api")
public class TxnController {

    private static final Logger log = LogManager.getLogger("springauth");

    @RequestMapping(value = "/txn", method = RequestMethod.GET)
    public RspObj Home() {
        RspObj objResult = new RspObj();
        objResult.setCode("00");
        objResult.setMessage("Welcome Txn");
        return objResult;
    }
}

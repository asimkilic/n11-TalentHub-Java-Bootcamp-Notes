package com.asimkilic.springboot.springboottraining.controller;


import com.asimkilic.springboot.springboottraining.transactional.ts1.Ts1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactional")
public class TransactionalController {
    @Autowired
    private Ts1Service ts1Service;

    /**
     * 1: transactional olmayan yerde kayıt  işlemi
     */
    @PostMapping("/ts1")
    public void ts1() {
        ts1Service.save();
    }

    /**
     *
     *  2: transactional olan yerde kayıt işlemi
     *  3: transactional olan yerden olmayan yere geçme
     *  4: transactional olmayan yerden olan yere geçme
     */
}

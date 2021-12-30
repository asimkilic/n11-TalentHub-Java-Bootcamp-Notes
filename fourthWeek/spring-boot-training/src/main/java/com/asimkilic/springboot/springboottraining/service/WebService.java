package com.asimkilic.springboot.springboottraining.service;

import com.asimkilic.springboot.springboottraining.converter.JsonResponseConverter;
import com.asimkilic.springboot.springboottraining.converter.ResponseConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class WebService {

    @Autowired
    @Qualifier("xml")
    private ResponseConverter responseConverter;

    public void convertResponse() {
        responseConverter.convert();
    }
}

package com.asimkilic.springboot.springboottraining.converter;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("json")
public class JsonResponseConverter implements ResponseConverter {
    @Override
    public void convert() {
        System.out.println("JSON");
    }
}

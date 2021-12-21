package com.asimkilic.springboot.springboottraining.converter;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("xml")
public class XmlResponseConverter implements ResponseConverter {
    @Override
    public void convert() {
        System.out.println("XML");
    }
}

package com.asimkilic.springboot.springboottraining.controller;

import com.asimkilic.springboot.springboottraining.entity.Urun;
import com.asimkilic.springboot.springboottraining.service.entityservice.UrunEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UrunController {

    @Autowired
    private UrunEntityService urunEntityService;

    @GetMapping("/merhaba")
    public String merhaba() {
        return "Merhaba Dünya";
    }

    @GetMapping("/urunler")
    public List<Urun> findAllUrunList() {
        return urunEntityService.findAll();
    }

    @GetMapping("/urunler/{id}")
    public Urun findUrunById(@PathVariable Long id) {
        return urunEntityService.findById(id);
    }

    @GetMapping("/urunler/dto/{id}")
    public Urun findUrunDtoById(@PathVariable Long id) {
        return urunEntityService.findById(id);
    }
}

package com.asimkilic.springboot.springboottraining.controller;

import com.asimkilic.springboot.springboottraining.converter.KategoriConverter;
import com.asimkilic.springboot.springboottraining.dto.KategoriDto;
import com.asimkilic.springboot.springboottraining.entity.Kategori;
import com.asimkilic.springboot.springboottraining.service.entityservice.KategoriEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/kategoriler")
public class KategoriController {
    @Autowired
    private KategoriEntityService kategoriEntityService;

    @GetMapping
    public List<KategoriDto> findAll() {
        List<Kategori> kategoriList = kategoriEntityService.findAll();

     /*   List<KategoriDto> kategoriDtoList = new ArrayList<>();
        for (Kategori kategori : kategoriList) {
            KategoriDto kategoriDto = KategoriConverter.INSTANCE.convertKategoriToKategoriDto(kategori);
            kategoriDtoList.add(kategoriDto);
        }*/

        List<KategoriDto> kategoriDtoList = KategoriConverter.INSTANCE.convertAllKategoriListToKategoriDtoList(kategoriList);
        return kategoriDtoList;
    }

    @GetMapping("/{id}")
    public Kategori findById(@PathVariable Long id) {
        Kategori kategori = kategoriEntityService.findById(id);
        return kategori;
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody KategoriDto kategoriDtoInput) { //TODO: Input değeri dto tipinde olmalı
        Kategori kategori = KategoriConverter.INSTANCE.convertKategoriDtoToKategori(kategoriDtoInput);
        if (kategori.getUstKategori() != null && kategori.getUstKategori().getId() == null) {
            kategori.setUstKategori(null);
        }
        kategori = kategoriEntityService.save(kategori);

        kategori = kategoriEntityService.save(kategori);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(kategori.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }

    @PutMapping
    public KategoriDto update(@RequestBody KategoriDto kategoriDtoInput) {
        Kategori kategori = KategoriConverter.INSTANCE.convertKategoriDtoToKategori(kategoriDtoInput);
        if (kategori.getUstKategori() != null && kategori.getUstKategori().getId() == null) {
            kategori.setUstKategori(null);
        }
        kategori = kategoriEntityService.save(kategori);
        KategoriDto kategoriDtoResult = KategoriConverter.INSTANCE.convertKategoriToKategoriDto(kategori);

        return kategoriDtoResult;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        kategoriEntityService.deleteById(id);
    }

}


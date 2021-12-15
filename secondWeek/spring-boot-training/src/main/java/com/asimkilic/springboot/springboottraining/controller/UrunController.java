package com.asimkilic.springboot.springboottraining.controller;

import com.asimkilic.springboot.springboottraining.dto.UrunDetayDto;
import com.asimkilic.springboot.springboottraining.dto.UrunDto;
import com.asimkilic.springboot.springboottraining.entity.Kategori;
import com.asimkilic.springboot.springboottraining.entity.Urun;
import com.asimkilic.springboot.springboottraining.exception.UrunNotFoundException;
import com.asimkilic.springboot.springboottraining.service.entityservice.KategoriEntityService;
import com.asimkilic.springboot.springboottraining.service.entityservice.UrunEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/urunler/")
public class UrunController {

    @Autowired
    private UrunEntityService urunEntityService;

    @Autowired
    private KategoriEntityService kategoriEntityService;


    @GetMapping("")
    public List<Urun> findAllUrunList() {
        return urunEntityService.findAll();
    }

    @GetMapping("/{id}")
    public EntityModel<Urun> findUrunById(@PathVariable Long id) {
        Urun urun = urunEntityService.findById(id);
        if (urun == null) {
            throw new UrunNotFoundException("Urun not found. id: " + id);
        }
        WebMvcLinkBuilder linkToUrun = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(this.getClass())
                        .findAllUrunList()
        );
        EntityModel entityModel = EntityModel.of(urun);
        entityModel.add(linkToUrun.withRel("tum-urunler"));



        return entityModel;
    }

    @PostMapping("")
    public ResponseEntity<Object> saveUrun(@RequestBody UrunDto urunDto) {
        Urun urun = convertUrunDtoToUrun(urunDto);
        urun = urunEntityService.save(urun);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("{id}").buildAndExpand(urun.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    private Urun convertUrunDtoToUrun(UrunDto urunDto) {
        Kategori kategori = kategoriEntityService.findById(urunDto.getKategoriId());
        Urun urun = new Urun();
        urun.setAdi(urunDto.getAdi());
        urun.setFiyat(urunDto.getFiyat());
        urun.setKayitTarihi(urunDto.getKayitTarihi());
        urun.setKategori(kategori);
        return urun;
    }

    @DeleteMapping("{id}")
    public void deleteUrun(@PathVariable Long id) {
        Urun urun = urunEntityService.findById(id);
        if (urun == null) {
            throw new UrunNotFoundException("Urun not found. id: " + id);
        }
        urunEntityService.deleteById(id);
    }

    @GetMapping("/dto/{id}")
    public UrunDetayDto findUrunDtoById(@PathVariable Long id) {
        Urun urun = urunEntityService.findById(id);
        UrunDetayDto urunDetayDto = convertUrunToUrunDetayDto(urun);
        return urunDetayDto;
    }

    private UrunDetayDto convertUrunToUrunDetayDto(Urun urun) {

        Kategori kategori = kategoriEntityService.findById(urun.getKategori().getId());
        UrunDetayDto urunDetayDto = new UrunDetayDto();
        urunDetayDto.setUrunAdi(urun.getAdi());
        urunDetayDto.setUrunFiyati(urun.getFiyat());
        urunDetayDto.setKategoriAdi(kategori.getAdi());

        return urunDetayDto;
    }
}

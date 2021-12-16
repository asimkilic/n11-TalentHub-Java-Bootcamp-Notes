package com.asimkilic.springboot.springboottraining.controller;

import com.asimkilic.springboot.springboottraining.converter.UrunConverter;
import com.asimkilic.springboot.springboottraining.dto.UrunDetayDto;
import com.asimkilic.springboot.springboottraining.dto.UrunDto;
import com.asimkilic.springboot.springboottraining.entity.Kategori;
import com.asimkilic.springboot.springboottraining.entity.Urun;
import com.asimkilic.springboot.springboottraining.exception.UrunNotFoundException;
import com.asimkilic.springboot.springboottraining.service.entityservice.KategoriEntityService;
import com.asimkilic.springboot.springboottraining.service.entityservice.UrunEntityService;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/urunler")
public class UrunController {

    @Autowired
    private UrunEntityService urunEntityService;

    @Autowired
    private KategoriEntityService kategoriEntityService;


    @GetMapping("")
    public MappingJacksonValue findAllUrunList() {
        List<Urun> urunList = urunEntityService.findAll();
        String filterName = "UrunFilter";
        SimpleBeanPropertyFilter filter = getUrunFilter();
        SimpleFilterProvider filters = new SimpleFilterProvider().addFilter(filterName, filter);
        MappingJacksonValue mapping = new MappingJacksonValue(urunList);
        mapping.setFilters(filters);

        return mapping;
    }

    @GetMapping("/{id}")
    public MappingJacksonValue findUrunById(@PathVariable Long id) {
        Urun urun = urunEntityService.findById(id);
        if (urun == null) {
            throw new UrunNotFoundException("Urun not found. id: " + id);
        }
        WebMvcLinkBuilder linkToUrun = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(this.getClass())
                        .findAllUrunList()
        );
        UrunDto urunDto = UrunConverter.INSTANCE.convertUrunToUrunDto(urun);
        String filterName = "UrunDtoFilter";

        SimpleFilterProvider filters = getUrunFilterProvider(filterName);


        EntityModel entityModel = EntityModel.of(urunDto);
        entityModel.add(linkToUrun.withRel("tum-urunler"));

        MappingJacksonValue mapping = new MappingJacksonValue(entityModel);
        mapping.setFilters(filters);

        return mapping;
    }


    @PostMapping("")
    public ResponseEntity<Object> saveUrun(@RequestBody UrunDto urunDto) {
        Urun urun = UrunConverter.INSTANCE.convertUrunDtoToUrun(urunDto);
        // Urun urun = convertUrunDtoToUrun(urunDto);
        urun = urunEntityService.save(urun);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(urun.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


    @DeleteMapping("{id}")
    public void deleteUrun(@PathVariable Long id) {
        Urun urun = urunEntityService.findById(id);
        if (urun == null) {
            throw new UrunNotFoundException("Urun not found. id: " + id);
        }
        urunEntityService.deleteById(id);
    }

    @GetMapping("/detail/{id}")
    public UrunDetayDto findUrunDtoById(@PathVariable Long id) {
        Urun urun = urunEntityService.findById(id);
        if (urun == null) {
            throw new UrunNotFoundException("Urun not found. id: " + id);
        }
        UrunDetayDto urunDetayDto = UrunConverter.INSTANCE.convertUrunToUrunDetayDto(urun);

        return urunDetayDto;
    }

    private SimpleFilterProvider getUrunFilterProvider(String filterName) {
        SimpleBeanPropertyFilter filter = getUrunFilter();
        SimpleFilterProvider filters = new SimpleFilterProvider().addFilter(filterName, filter);
        return filters;
    }

    private SimpleBeanPropertyFilter getUrunFilter() {
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "adi", "fiyat", "kayitTarihi");
        return filter;
    }

}

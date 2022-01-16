package com.asimkilic.n11.n11bootcamp.prd.controller;

import com.asimkilic.n11.n11bootcamp.prd.dto.PrdCategoryForMenuDto;
import com.asimkilic.n11.n11bootcamp.prd.dto.PrdCategorySaveRequestDto;
import com.asimkilic.n11.n11bootcamp.prd.entity.PrdCategory;
import com.asimkilic.n11.n11bootcamp.prd.service.PrdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
@CrossOrigin
public class PrdCategoryController {

    private final PrdService prdService;


    @PostMapping
    public ResponseEntity<Object> save(@RequestBody PrdCategorySaveRequestDto prdCategorySaveRequestDto) {
        PrdCategory prdCategory = prdService.saveCategory(prdCategorySaveRequestDto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(prdCategory.getId())
                .toUri();

        return ResponseEntity.created(uri).build();

    }
    @GetMapping
    public ResponseEntity findAll(){
        List<PrdCategorySaveRequestDto> prdCategories = prdService.findAll();
        return ResponseEntity.ok(prdCategories);
    }
    @GetMapping("/{id}")
    public ResponseEntity getPrdCategoryById(@PathVariable Long id){
        PrdCategorySaveRequestDto prdCategory = prdService.getPrdCategoryById(id);
        return ResponseEntity.ok(prdCategory);
    }

    @GetMapping("/super/{superCategoryId}")
    public ResponseEntity findBySuperCategoryId(@PathVariable Long superCategoryId){
        List<PrdCategorySaveRequestDto> prdCategoryList = prdService.findBySuperCategoryId(superCategoryId);
        return ResponseEntity.ok(prdCategoryList);
    }
    @GetMapping("/menu")
    public ResponseEntity findAllForMenu(){
        List<PrdCategoryForMenuDto> prdCategories = prdService.findAllForMenu();

        return ResponseEntity.ok(prdCategories);
    }
}

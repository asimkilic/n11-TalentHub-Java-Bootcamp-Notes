package com.asimkilic.n11.n11bootcamp.prd.controller;

import com.asimkilic.n11.n11bootcamp.prd.dto.PrdCategorySaveRequestDto;
import com.asimkilic.n11.n11bootcamp.prd.entity.PrdCategory;
import com.asimkilic.n11.n11bootcamp.prd.service.PrdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
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

    @GetMapping("/{superCategoryId}")
    public List<PrdCategory> findBySuperCategoryId(@PathVariable Long superCategoryId){
        return prdService.findBySuperCategoryId(superCategoryId);
    }

}

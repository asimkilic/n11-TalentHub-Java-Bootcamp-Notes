package com.asimkilic.n11.n11bootcamp.prd.service;

import com.asimkilic.n11.n11bootcamp.gen.entity.BaseEntity;
import com.asimkilic.n11.n11bootcamp.prd.converter.PrdCategoryConverter;
import com.asimkilic.n11.n11bootcamp.prd.dto.PrdCategoryForMenuDto;
import com.asimkilic.n11.n11bootcamp.prd.dto.PrdCategorySaveRequestDto;
import com.asimkilic.n11.n11bootcamp.prd.entity.PrdCategory;
import com.asimkilic.n11.n11bootcamp.prd.entity.PrdProduct;
import com.asimkilic.n11.n11bootcamp.prd.service.entityservice.PrdCategoryEntityService;
import com.asimkilic.n11.n11bootcamp.prd.service.entityservice.PrdProductEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.asimkilic.n11.n11bootcamp.prd.converter.PrdCategorMapper.INSTANCE;

@Service
@RequiredArgsConstructor
public class PrdService {
    private final PrdProductEntityService prdProductEntityService;
    private final PrdCategoryEntityService prdCategoryEntityService;
    private final PrdCategoryConverter prdCategoryConverter;

    public void test() {
        List<PrdProduct> productList = prdProductEntityService.findAll();
        List<PrdCategory> categoryList = prdCategoryEntityService.findAll();

        List<BaseEntity> baseEntityList = new ArrayList<>();
        baseEntityList.addAll(productList);
        baseEntityList.addAll(categoryList);
        for (BaseEntity baseEntity : baseEntityList) {
            System.out.println(baseEntity.getId());
        }
    }

    public PrdCategory saveCategory(PrdCategorySaveRequestDto prdCategorySaveRequestDto) {
        PrdCategory prdCategory = INSTANCE.convertToPrdCategory(prdCategorySaveRequestDto);
        prdCategory = prdCategoryEntityService.save(prdCategory);
        return prdCategory;
    }

    public List<PrdCategorySaveRequestDto> findBySuperCategoryId(Long superCategoryId) {
        List<PrdCategory> prdCategoryList = prdCategoryEntityService.findBySuperCategoryId(superCategoryId);
        return INSTANCE.convertToPrdCategorySaveRequestDtoList(prdCategoryList);

    }

    public PrdCategorySaveRequestDto getPrdCategoryById(Long id) {
        Optional<PrdCategory> optionalPrdCategory = prdCategoryEntityService.findById(id);
        if (optionalPrdCategory.isPresent()) {
            PrdCategory prdCategory = optionalPrdCategory.get();
            return INSTANCE.convertToPrdCategorySaveRequestDto(prdCategory);
        }else {
            throw new RuntimeException("Category not found!");
        }

    }
    public List<PrdCategorySaveRequestDto> findAll() {

        List<PrdCategory> categoryList = prdCategoryEntityService.findAll();
        return INSTANCE.convertToPrdCategorySaveRequestDtoList(categoryList);
    }

    public List<PrdCategoryForMenuDto> findAllForMenu() {
        List<PrdCategory> prdCategoryList = prdCategoryEntityService.findBySuperCategoryId(null);
        List<PrdCategoryForMenuDto> prdCategoryForMenuDtoList = prdCategoryConverter.convertToPrdCategoryForMenuDtoList(prdCategoryList);
        return prdCategoryForMenuDtoList;
    }
}

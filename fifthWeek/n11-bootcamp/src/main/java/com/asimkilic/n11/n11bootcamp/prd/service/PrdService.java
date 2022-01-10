package com.asimkilic.n11.n11bootcamp.prd.service;

import com.asimkilic.n11.n11bootcamp.gen.entity.BaseEntity;
import com.asimkilic.n11.n11bootcamp.prd.converter.PrdCategoryConverter;
import com.asimkilic.n11.n11bootcamp.prd.dto.PrdCategorySaveRequestDto;
import com.asimkilic.n11.n11bootcamp.prd.entity.PrdCategory;
import com.asimkilic.n11.n11bootcamp.prd.entity.PrdProduct;
import com.asimkilic.n11.n11bootcamp.prd.service.entityservice.PrdCategoryEntityService;
import com.asimkilic.n11.n11bootcamp.prd.service.entityservice.PrdProductEntityService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.asimkilic.n11.n11bootcamp.prd.converter.PrdCategoryConverter.INSTANCE;

@Service
@RequiredArgsConstructor
public class PrdService {
    private final PrdProductEntityService prdProductEntityService;
    private final PrdCategoryEntityService prdCategoryEntityService;

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
        prdCategory =  prdCategoryEntityService.save(prdCategory);
       return prdCategory;
    }

    public List<PrdCategory> findBySuperCategoryId(Long superCategoryId) {
        return prdCategoryEntityService.findBySuperCategoryId(superCategoryId);
    }
}

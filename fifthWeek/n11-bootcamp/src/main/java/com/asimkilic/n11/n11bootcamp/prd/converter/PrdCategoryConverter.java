package com.asimkilic.n11.n11bootcamp.prd.converter;

import com.asimkilic.n11.n11bootcamp.prd.dto.PrdCategoryForMenuDto;
import com.asimkilic.n11.n11bootcamp.prd.entity.PrdCategory;
import com.asimkilic.n11.n11bootcamp.prd.service.entityservice.PrdCategoryEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.asimkilic.n11.n11bootcamp.prd.converter.PrdCategorMapper.INSTANCE;

@Service
@RequiredArgsConstructor
public class PrdCategoryConverter {
    private final PrdCategoryEntityService prdCategoryEntityService;
    public PrdCategoryForMenuDto convertToPrdCategoryForMenuDto(PrdCategory prdCategory){
        PrdCategoryForMenuDto prdCategoryForMenuDto = INSTANCE.convertToPrdCategoryForMenuDto(prdCategory);
        List<PrdCategory> subPrdCategoryList = prdCategoryEntityService.findBySuperCategoryId(prdCategory.getId());

        List<PrdCategoryForMenuDto> subCategoryForMenuDtoList = convertToPrdCategoryForMenuDtoList(subPrdCategoryList);

        prdCategoryForMenuDto.setSubPrdCategoryForMenuDtoList(subCategoryForMenuDtoList);
        return prdCategoryForMenuDto;

    }
    public List<PrdCategoryForMenuDto> convertToPrdCategoryForMenuDtoList(List<PrdCategory> prdCategoryList){
        List<PrdCategoryForMenuDto> prdCategoryForMenuDtoList = new ArrayList<>();
        for (PrdCategory prdCategory : prdCategoryList) {

            PrdCategoryForMenuDto prdCategoryForMenuDto = convertToPrdCategoryForMenuDto(prdCategory);
            prdCategoryForMenuDtoList.add(prdCategoryForMenuDto);

        }
        return prdCategoryForMenuDtoList;
    }
}

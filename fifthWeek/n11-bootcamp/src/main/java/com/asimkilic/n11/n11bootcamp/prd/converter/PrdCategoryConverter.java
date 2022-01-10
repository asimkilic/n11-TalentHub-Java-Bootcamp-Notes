package com.asimkilic.n11.n11bootcamp.prd.converter;

import com.asimkilic.n11.n11bootcamp.prd.dto.PrdCategorySaveRequestDto;
import com.asimkilic.n11.n11bootcamp.prd.entity.PrdCategory;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PrdCategoryConverter {

    PrdCategoryConverter INSTANCE = Mappers.getMapper(PrdCategoryConverter.class);

    PrdCategorySaveRequestDto convertToPrdCategorySaveRequestDto(PrdCategory prdCategory);

    PrdCategory convertToPrdCategory(PrdCategorySaveRequestDto prdCategorySaveRequestDto);

}

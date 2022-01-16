package com.asimkilic.n11.n11bootcamp.prd.dto;

import lombok.Data;

import java.util.List;

@Data
public class PrdCategoryForMenuDto {

    private Long id;
    private String name;

    private String shortName;

    private Long level;

    private Long superCategoryId;
    private List<PrdCategoryForMenuDto> subPrdCategoryForMenuDtoList;
}

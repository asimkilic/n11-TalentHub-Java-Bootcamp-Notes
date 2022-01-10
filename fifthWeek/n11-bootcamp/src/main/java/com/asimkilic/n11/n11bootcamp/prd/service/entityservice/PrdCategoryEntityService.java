package com.asimkilic.n11.n11bootcamp.prd.service.entityservice;

import com.asimkilic.n11.n11bootcamp.gen.service.BaseEntityService;
import com.asimkilic.n11.n11bootcamp.prd.dao.PrdCategoryDao;
import com.asimkilic.n11.n11bootcamp.prd.entity.PrdCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@RequiredArgsConstructor
public class PrdCategoryEntityService extends BaseEntityService<PrdCategory, PrdCategoryDao> {
   // private final PrdCategoryDao prdCategoryDao;


    public PrdCategoryEntityService(PrdCategoryDao dao) {
        super(dao);
    }

    public List<PrdCategory> findBySuperCategoryId(Long superCategoryId){
        return getDao().findAllBySuperCategoryId(superCategoryId);

    }
}

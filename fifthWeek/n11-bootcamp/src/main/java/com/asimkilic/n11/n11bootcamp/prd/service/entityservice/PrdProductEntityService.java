package com.asimkilic.n11.n11bootcamp.prd.service.entityservice;

import com.asimkilic.n11.n11bootcamp.gen.service.BaseEntityService;
import com.asimkilic.n11.n11bootcamp.prd.dao.PrdProductDao;
import com.asimkilic.n11.n11bootcamp.prd.entity.PrdProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@RequiredArgsConstructor
public class PrdProductEntityService extends BaseEntityService<PrdProduct,PrdProductDao> {

    //private final PrdProductDao prdProductDao;

    public PrdProductEntityService(PrdProductDao dao) {
        super(dao);
    }
}

package com.asimkilic.n11.n11bootcamp.prd.dao;

import com.asimkilic.n11.n11bootcamp.prd.entity.PrdProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrdProductDao extends JpaRepository<PrdProduct,Long> {
}

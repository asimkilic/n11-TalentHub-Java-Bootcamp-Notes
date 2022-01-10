package com.asimkilic.n11.n11bootcamp.prd.dao;

import com.asimkilic.n11.n11bootcamp.prd.entity.PrdCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrdCategoryDao extends JpaRepository<PrdCategory, Long> {
    List<PrdCategory> findAllBySuperCategoryId(Long superCategoryId);
}

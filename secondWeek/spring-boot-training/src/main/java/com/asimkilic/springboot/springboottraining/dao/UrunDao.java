package com.asimkilic.springboot.springboottraining.dao;

import com.asimkilic.springboot.springboottraining.entity.Urun;
import org.springframework.data.repository.CrudRepository;

public interface UrunDao extends CrudRepository<Urun,Long> {
}

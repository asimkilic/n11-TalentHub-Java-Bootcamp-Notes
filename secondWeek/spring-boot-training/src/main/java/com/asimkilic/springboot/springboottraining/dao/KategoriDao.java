package com.asimkilic.springboot.springboottraining.dao;

import com.asimkilic.springboot.springboottraining.entity.Kategori;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KategoriDao extends CrudRepository<Kategori,Long> {

}

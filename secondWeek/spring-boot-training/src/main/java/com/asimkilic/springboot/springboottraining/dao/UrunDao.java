package com.asimkilic.springboot.springboottraining.dao;

import com.asimkilic.springboot.springboottraining.entity.Urun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UrunDao extends JpaRepository<Urun, Long> {
    List<Urun> findAllByKategori_IdOrderByIdDesc(Long kategoriId);
}

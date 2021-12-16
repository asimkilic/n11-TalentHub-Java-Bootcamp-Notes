package com.asimkilic.springboot.springboottraining.dao;

import com.asimkilic.springboot.springboottraining.entity.Kategori;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KategoriDao extends JpaRepository<Kategori,Long> {

    // adına göre bir şey çekeceğim db'den
    List<Kategori> findAllByUstKategoriIsNull();
    List<Kategori> findAllByUstKategoriIsNullOrderByAdi();

    @Query("select kategori from Kategori kategori where kategori.ustKategori.id=2")
    List<Kategori> findAllByUstKategoriIsTwo();

    List<Kategori>  findByAdiEndsWith(String adi);

}

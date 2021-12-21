package com.asimkilic.springboot.springboottraining.service.entityservice;


import com.asimkilic.springboot.springboottraining.dao.UrunDao;
import com.asimkilic.springboot.springboottraining.entity.Urun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UrunEntityService {

    @Autowired
    private UrunDao urunDao;

    public List<Urun> findAll() {
        return (List<Urun>) urunDao.findAll();
    }

    public Urun findById(Long id) {
        Optional<Urun> optionalUrun = urunDao.findById(id);
        Urun urun = null;
        if (optionalUrun.isPresent())
            urun = optionalUrun.get();

        return urun;
    }

    public Urun save(Urun urun) {
        urun = urunDao.save(urun);
        return urun;
    }

    public void delete(Urun urun) {
        urunDao.delete(urun);
    }

    public void deleteById(Long id) {
        urunDao.deleteById(id);
    }

    public long count() {
        return urunDao.count();
    }

    public List<Urun> findAllByKategori_IdOrderByIdDesc(Long id) {
        return urunDao.findAllByKategori_IdOrderByIdDesc(id);
    }
}

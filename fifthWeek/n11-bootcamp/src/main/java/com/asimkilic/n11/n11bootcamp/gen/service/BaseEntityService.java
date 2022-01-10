package com.asimkilic.n11.n11bootcamp.gen.service;

import com.asimkilic.n11.n11bootcamp.gen.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public abstract class BaseEntityService<E extends BaseEntity,D extends JpaRepository> {

    private D dao;
    public List<E> findAll(){
        return dao.findAll();
    }
    public Optional<E> findById(Long id){
        return dao.findById(id);
    }
    public E save(E entity){
        return (E)dao.save(entity);
    }

    public void delete(E entity){
        dao.delete(entity);
    }

    public D getDao(){
        return dao;
    }
}

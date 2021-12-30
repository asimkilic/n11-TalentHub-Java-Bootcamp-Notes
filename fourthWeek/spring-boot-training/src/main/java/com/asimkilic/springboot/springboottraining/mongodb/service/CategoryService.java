package com.asimkilic.springboot.springboottraining.mongodb.service;

import com.asimkilic.springboot.springboottraining.mongodb.entity.Category;
import com.asimkilic.springboot.springboottraining.mongodb.service.entityservice.CategoryEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService {
    List<Category> findAll() ;

    Category findById(String id) ;

    Category save(Category category) ;

    void delete(String id) ;
}



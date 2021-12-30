package com.asimkilic.springboot.springboottraining.mongodb.service;

import com.asimkilic.springboot.springboottraining.mongodb.entity.Category;

import com.asimkilic.springboot.springboottraining.mongodb.service.entityservice.CategoryEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryEntityService categoryEntityService;

    public List<Category> findAll() {
        List<Category> categoryList = categoryEntityService.findAll();
        return categoryList;
    }

    public Category findById(String id) {
        Category categoryById = categoryEntityService.findById(id);
        return categoryById;
    }

    public Category save(Category category) {
        return categoryEntityService.save(category);
    }

    public void delete(String id) {
        categoryEntityService.deleteById(id);
    }
}
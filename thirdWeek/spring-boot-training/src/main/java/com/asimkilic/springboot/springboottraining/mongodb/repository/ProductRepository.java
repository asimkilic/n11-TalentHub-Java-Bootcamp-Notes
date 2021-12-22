package com.asimkilic.springboot.springboottraining.mongodb.repository;

import com.asimkilic.springboot.springboottraining.mongodb.dto.ProductDetailDto;
import com.asimkilic.springboot.springboottraining.mongodb.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    List<Product> findAllByCategoryId(String categoryId);


}

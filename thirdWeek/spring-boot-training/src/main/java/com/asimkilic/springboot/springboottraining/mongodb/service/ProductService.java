package com.asimkilic.springboot.springboottraining.mongodb.service;

import com.asimkilic.springboot.springboottraining.mongodb.dto.ProductDetailDto;
import com.asimkilic.springboot.springboottraining.mongodb.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {
    List<Product> findAll();

    Product findById(String id);

    ProductDetailDto findProductDetailDtoById(String id);

    Product save(Product product);

    void deleteById(String id);

    List<ProductDetailDto> findAllProductByCategoryId(String categoryId);
}

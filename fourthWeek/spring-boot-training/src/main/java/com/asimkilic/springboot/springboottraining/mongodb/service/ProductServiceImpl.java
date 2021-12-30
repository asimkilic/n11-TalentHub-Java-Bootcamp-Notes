package com.asimkilic.springboot.springboottraining.mongodb.service;

import com.asimkilic.springboot.springboottraining.mongodb.converter.ProductConverter;
import com.asimkilic.springboot.springboottraining.mongodb.dto.ProductDetailDto;
import com.asimkilic.springboot.springboottraining.mongodb.entity.Product;
import com.asimkilic.springboot.springboottraining.mongodb.service.entityservice.ProductEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductEntityService productEntityService;

    @Override
    public List<Product> findAll() {
        return productEntityService.findAll();
    }

    @Override
    public Product findById(String id) {
        return productEntityService.findById(id);
    }

    @Override
    public ProductDetailDto findProductDetailDtoById(String id) {
        Product product = productEntityService.findById(id);
        ProductDetailDto productDetailDto = ProductConverter.INSTANCE.convertProductToProductDetailDto(product);

        return productDetailDto;
    }

    @Override
    public Product save(Product product) {
        return productEntityService.save(product);
    }

    @Override
    public void deleteById(String id) {
        productEntityService.delete(id);
    }

    @Override
    public List<ProductDetailDto> findAllProductByCategoryId(String categoryId) {
        List<Product> productList = productEntityService.findAllProductByCategoryId(categoryId);
        List<ProductDetailDto> productDetailDtos = ProductConverter.INSTANCE.convertAllProductListToProductDetailDtoList(productList);
        return productDetailDtos;
    }
}

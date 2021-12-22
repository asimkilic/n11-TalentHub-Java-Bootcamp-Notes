package com.asimkilic.springboot.springboottraining.mongodb.converter;

import com.asimkilic.springboot.springboottraining.mongodb.dto.ProductDetailDto;
import com.asimkilic.springboot.springboottraining.mongodb.entity.Product;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductConverter {

    ProductConverter INSTANCE = Mappers.getMapper(ProductConverter.class);

    @Mapping(source = "name", target = "productName")
    @Mapping(source = "price", target = "productPrice")
    ProductDetailDto convertProductToProductDetailDto(Product product);


    @Mapping(source = "name", target = "productName")
    @Mapping(source = "price", target = "productPrice")
    List<ProductDetailDto> convertAllProductListToProductDetailDtoList(List<Product> product);

  /*  @AfterMapping
    default void setCategoryName(@MappingTarget final ProductDetailDto productDetailDto, Product product) {
        if (product.getCategoryId() == null) {

        }
    }*/
}

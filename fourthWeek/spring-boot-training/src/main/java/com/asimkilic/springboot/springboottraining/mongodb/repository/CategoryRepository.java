package com.asimkilic.springboot.springboottraining.mongodb.repository;

import com.asimkilic.springboot.springboottraining.mongodb.entity.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<Category,String> {

}

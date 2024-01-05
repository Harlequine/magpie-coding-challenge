package com.magpie.magpiecodingchallenge.Repository;

import com.magpie.magpiecodingchallenge.Model.Product;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.validation.ObjectError;

@Repository
public interface ProductRepository extends MongoRepository <Product, ObjectId>{

    void deleteById(String id);
}

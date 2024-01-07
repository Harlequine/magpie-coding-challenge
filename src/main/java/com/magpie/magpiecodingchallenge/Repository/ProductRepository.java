package com.magpie.magpiecodingchallenge.Repository;

import com.magpie.magpiecodingchallenge.Model.Product;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository <Product, ObjectId>{

    Product deleteById(String id);

    Optional<Product> findOneByName(String name);

}

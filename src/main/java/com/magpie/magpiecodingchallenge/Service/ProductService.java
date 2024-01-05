package com.magpie.magpiecodingchallenge.Service;

import com.magpie.magpiecodingchallenge.Model.Product;
import com.magpie.magpiecodingchallenge.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public Product add(Product product){
        return productRepository.save(product);
    }
    public List<Product> findAll(){
        return productRepository.findAll();
    }
}

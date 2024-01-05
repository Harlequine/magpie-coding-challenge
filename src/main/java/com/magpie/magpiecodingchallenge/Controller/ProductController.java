package com.magpie.magpiecodingchallenge.Controller;

import com.magpie.magpiecodingchallenge.Model.Product;
import com.magpie.magpiecodingchallenge.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    ResponseEntity<?> add(@RequestBody Product product){
        return new ResponseEntity<>(productService.add(product), HttpStatus.OK);
    }
    @GetMapping("/find-all")
    ResponseEntity<List<Product>> findAll(){
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }


}

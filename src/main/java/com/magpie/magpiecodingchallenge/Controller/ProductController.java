package com.magpie.magpiecodingchallenge.Controller;

import com.magpie.magpiecodingchallenge.DTO.ProductUpdateDTO;
import com.magpie.magpiecodingchallenge.Exception.ProductException;
import com.magpie.magpiecodingchallenge.Model.Product;
import com.magpie.magpiecodingchallenge.Service.ProductService;
import jakarta.validation.Valid;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
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
    ResponseEntity<Product> add(@RequestBody @Valid Product product) throws ProductException {
        return new ResponseEntity<>(productService.add(product), HttpStatus.CREATED);
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<Product>> findAll() throws ProductException{
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/find/{name}")
    public ResponseEntity<Product> findOne(@PathVariable("name") String name) throws ProductException{
        return new ResponseEntity<>(productService.findOne(name), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> update(@PathVariable("id") ObjectId id, @RequestBody ProductUpdateDTO productUpdateDTO) throws ProductException {
        return new ResponseEntity<>(productService.update(id, productUpdateDTO),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) throws ProductException{
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<?> deleteAll() throws ProductException{
        productService.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

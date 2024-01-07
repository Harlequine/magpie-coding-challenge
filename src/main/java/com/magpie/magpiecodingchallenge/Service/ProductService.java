package com.magpie.magpiecodingchallenge.Service;

import com.magpie.magpiecodingchallenge.DTO.ProductUpdateDTO;
import com.magpie.magpiecodingchallenge.Exception.ProductException;
import com.magpie.magpiecodingchallenge.Model.Product;
import com.magpie.magpiecodingchallenge.Repository.ProductRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public Product add(Product product) throws ProductException {
        Optional<Product> prod = productRepository.findOneByName(product.getName());

        if(prod.isPresent()){
            throw new ProductException("Product name already exist.");
        }

        return productRepository.save(product);
    }
    public List<Product> findAll() throws ProductException{
        List<Product> prod = productRepository.findAll();

        if(prod.isEmpty()){
            throw new ProductException("Product does not exists");
        }

        return prod;
    }

    public Product findOne(String name) throws ProductException{
        return productRepository.findOneByName(name).orElseThrow(() -> new ProductException("Product does not exist"));
    }

    public Product update(ObjectId id, ProductUpdateDTO productUpdateDTO) throws ProductException {
        Product prod = productRepository.findById(id).orElseThrow(() -> new ProductException("Product does not exist"));

        if(productUpdateDTO.getName() != null && !productUpdateDTO.getName().isEmpty() && productUpdateDTO.getName().matches("^[a-zA-Z0-9]*$") && !Objects.equals(prod.getName(), productUpdateDTO.getName())){
            prod.setName(productUpdateDTO.getName());
        }

        if(productUpdateDTO.getDescription() != null && !productUpdateDTO.getDescription().isEmpty() && !Objects.equals(prod.getDescription(), productUpdateDTO.getDescription())){
            prod.setDescription(productUpdateDTO.getDescription());
        }

        if(productUpdateDTO.getType() != null && !productUpdateDTO.getType().isEmpty() && productUpdateDTO.getType().matches("^(Food|Sports|Household|Music|Electronic|Appliance)$") && !Objects.equals(prod.getType(), productUpdateDTO.getType())){
            prod.setType(productUpdateDTO.getType());
        }

        if(productUpdateDTO.getQuantity() != null && productUpdateDTO.getQuantity() > 0){
            prod.setQuantity(productUpdateDTO.getQuantity());
        }

        if(productUpdateDTO.getPrice() != null && productUpdateDTO.getPrice() > 0){
            prod.setPrice(productUpdateDTO.getPrice());
        }

        return productRepository.save(prod);
    }

    public void delete(String id) throws ProductException {
        productRepository.deleteById(id).orElseThrow(() -> new ProductException("Product does not exist"));
    }

    public void deleteAll() throws ProductException {
        productRepository.deleteAll();
    }
}

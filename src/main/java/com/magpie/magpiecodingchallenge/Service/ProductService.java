package com.magpie.magpiecodingchallenge.Service;

import com.magpie.magpiecodingchallenge.DTO.ProductUpdateDTO;
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


    public Product add(Product product) throws Exception{
        Optional<Product> prod = productRepository.findByName(product.getName());

        if(prod.isPresent()){
            throw new Exception();
        }

        return productRepository.save(product);
    }
    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product update(ObjectId id, ProductUpdateDTO productUpdateDTO) throws ChangeSetPersister.NotFoundException {
        Product prod = productRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);

        if(productUpdateDTO.getName() != null && !productUpdateDTO.getName().isEmpty() && !Objects.equals(prod.getName(), productUpdateDTO.getName())){
            prod.setName(productUpdateDTO.getName());
        }
        if(productUpdateDTO.getDescription() != null && !productUpdateDTO.getDescription().isEmpty() && !Objects.equals(prod.getDescription(), productUpdateDTO.getDescription())){
            prod.setDescription(productUpdateDTO.getDescription());
        }
        if(productUpdateDTO.getType() != null && !productUpdateDTO.getType().isEmpty() && !Objects.equals(prod.getType(), productUpdateDTO.getType())){
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

    public Product delete(String id){
        return productRepository.deleteById(id);
    }
}

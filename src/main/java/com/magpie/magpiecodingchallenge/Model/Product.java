package com.magpie.magpiecodingchallenge.Model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "products")
@TypeAlias("Product")
@Data
@Builder
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private String type;
    private Long quantity;
    private Long price;
}

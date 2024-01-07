package com.magpie.magpiecodingchallenge.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
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

    @NotBlank(message = "Product Name should not be blank.")
    @Pattern(
            regexp = "^[a-zA-Z0-9]*$",
            message = "Product name must not include any special characters."
    )
    private String name;

    @NotBlank(message = "Description should not be blank.")
    private String description;

    @NotBlank(message = "Type should not be blank.")
    @Pattern(
            regexp = "^(Food|Sports|Household|Music|Electronic|Appliance)$",
            message = "Type should be one of: Food, Sports, Household, Music, Electronic or Appliance"
    )
    private String type;

    @NotNull(message = "Quantity should not be null.")
    @Positive(message = "Quantity should be greater than 0.")
    @JsonProperty("quantity")
    private Long quantity;

    @NotNull(message = "Price should not be null.")
    @Positive(message = "Price should be greater than 0.")
    @JsonProperty("price")
    private Long price;
}

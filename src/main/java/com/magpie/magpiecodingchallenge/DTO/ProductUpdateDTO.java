package com.magpie.magpiecodingchallenge.DTO;

import lombok.Data;

@Data
public class ProductUpdateDTO {

    private String name;
    private String description;
    private String type;
    private Long quantity;
    private Long price;
}

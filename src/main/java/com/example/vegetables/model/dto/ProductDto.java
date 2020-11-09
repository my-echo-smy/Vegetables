package com.example.vegetables.model.dto;

import com.example.vegetables.model.Product;
import lombok.Data;

import java.util.List;
@Data
public class ProductDto {
    private String type;
    private List<Product> products;
}

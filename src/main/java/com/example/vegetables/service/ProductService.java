package com.example.vegetables.service;

import com.baomidou.mybatisplus.service.IService;
import com.example.vegetables.model.Product;
import com.example.vegetables.model.dto.ProductDto;

import java.util.List;

public interface ProductService  extends IService<Product> {
    /**
     * 名称
     */
    Product selectById(String id);
    List<ProductDto>  selectByPickUp(String id);

}

package com.example.vegetables.service;

import com.baomidou.mybatisplus.service.IService;
import com.example.vegetables.model.Product;

public interface ProductService  extends IService<Product> {
    Product selectById(String id);
}

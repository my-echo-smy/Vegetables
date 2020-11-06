package com.example.vegetables.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.vegetables.mapper.ProductMapper;
import com.example.vegetables.model.Product;
import com.example.vegetables.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl  extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Override
    public Product selectById(String id) {
        return baseMapper.selectById(id);
    }
}

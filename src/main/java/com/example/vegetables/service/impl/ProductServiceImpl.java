package com.example.vegetables.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.vegetables.mapper.ProductMapper;
import com.example.vegetables.model.Product;
import com.example.vegetables.model.dto.ProductDto;
import com.example.vegetables.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl  extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Override
    public Product selectById(String id) {
        return baseMapper.selectById(id);
    }

    @Override
    public List<ProductDto> selectByPickUp(String id) {

        return null;
    }


}

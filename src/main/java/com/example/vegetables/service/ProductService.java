package com.example.vegetables.service;

import com.baomidou.mybatisplus.service.IService;
import com.example.vegetables.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService extends IService<Product> {
    /**
     * 名称
     */
    Product selectById(String id);

    List<Product> selectByPickUp(String id);

    void save(Product product, MultipartFile file);

    void update(Product product, MultipartFile file);

    String uploadFile(MultipartFile file);
}

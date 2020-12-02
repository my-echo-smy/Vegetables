package com.example.vegetables.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.vegetables.model.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author SMy
 */
public interface ProductMapper extends BaseMapper<Product> {
    List<Product> selectByPickUp(@Param("pick_up_id") String id,@Param("name") String name);
    List<Product> getOnSaleList(@Param("pick_up_id") String id,@Param("name") String name);

    String insertProduct(Product product);
}
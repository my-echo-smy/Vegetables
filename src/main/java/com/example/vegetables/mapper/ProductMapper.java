package com.example.vegetables.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.vegetables.model.Product;
import com.example.vegetables.model.dto.ProductDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author SMy
 */
public interface ProductMapper extends BaseMapper<Product> {
    List<ProductDto> selectByPickUp(@Param("pick_up_id") String id);

}
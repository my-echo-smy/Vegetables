package com.example.vegetables.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.vegetables.model.MallOrder;
import com.example.vegetables.model.Product;
import org.apache.ibatis.annotations.Param;

/**
 * @author SMy
 */
public interface OrderMapper extends BaseMapper<MallOrder> {

    MallOrder selectByOrderNo(@Param("order_no") String orderNo);

}
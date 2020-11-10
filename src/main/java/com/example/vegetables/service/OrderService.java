package com.example.vegetables.service;

import com.baomidou.mybatisplus.service.IService;
import com.example.vegetables.model.MallOrder;
import com.example.vegetables.model.OrderItem;

import java.util.List;


public interface OrderService extends IService<MallOrder> {
    MallOrder selectById(String id);
    List<MallOrder> selectByUserId(String id);
    String save(List<OrderItem> orderItems);

    void updateOrderStatus(Integer status,String orderNo);

}

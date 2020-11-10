package com.example.vegetables.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.vegetables.mapper.OrderItemMapper;
import com.example.vegetables.mapper.OrderMapper;
import com.example.vegetables.model.MallOrder;
import com.example.vegetables.model.OrderItem;
import com.example.vegetables.service.OrderService;
import com.example.vegetables.util.OrderNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, MallOrder> implements OrderService {
    @Autowired
    OrderItemMapper orderItemMapper;

    @Override
    public MallOrder selectById(String id) {
        return baseMapper.selectById(id);
    }

    @Override
    public List<MallOrder> selectByUserId(String id) {
        Wrapper<MallOrder> orderWrapper = new EntityWrapper<>();
        orderWrapper.eq("user_id", id);
        return this.selectList(orderWrapper);
    }

    @Override
    public String save(List<OrderItem> orderItems) {
        String orderNo = OrderNoUtil.getCode(new Date());
        BigDecimal totalPrice = BigDecimal.ZERO;
        String pickUpId = null;
        for (OrderItem orderItem : orderItems) {
            orderItem.setOrderNo(orderNo);
            totalPrice.add(orderItem.getTotalPrice());
            pickUpId = orderItem.getPickUpId();
            orderItemMapper.insert(orderItem);
        }
        MallOrder order = new MallOrder();
        order.setStatus(1);
        order.setOrderNo(orderNo);
        order.setUserId("");
        order.setTotalPrice(totalPrice);
        order.setPickUpId(pickUpId);
        baseMapper.insert(order);
        return orderNo;
    }

    @Override
    public void updateOrderStatus(Integer status, String orderNo) {
        MallOrder order = baseMapper.selectByOrderNo(orderNo);
        if (order != null) {
            if (2 == status) {
                order.setStatus(status);
                order.setPayTime(new Date());
            } else if (3 == status) {
                order.setStatus(status);
                order.setPickUpTime(new Date());
            }
            baseMapper.updateById(order);
        }
    }
}

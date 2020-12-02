package com.example.vegetables.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.vegetables.mapper.OrderMapper;
import com.example.vegetables.mapper.PickUpMapper;
import com.example.vegetables.model.MallOrder;
import com.example.vegetables.model.PickUp;
import com.example.vegetables.service.OrderService;
import com.example.vegetables.service.PickUpService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PickUpServiceImpl extends ServiceImpl<PickUpMapper, PickUp> implements PickUpService {


    @Override
    public List<PickUp> getList() {
        Wrapper<PickUp> pickUpWrapper=new EntityWrapper<>();
        return baseMapper.selectList(pickUpWrapper);
    }
}

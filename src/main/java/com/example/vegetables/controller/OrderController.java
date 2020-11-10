package com.example.vegetables.controller;

import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.com.alibaba.fastjson.JSON;
import com.example.vegetables.common.ErrorResponseData;
import com.example.vegetables.common.ResponseData;
import com.example.vegetables.common.SuccessResponseData;
import com.example.vegetables.model.OrderItem;
import com.example.vegetables.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author SMy
 */
@Api(tags = {"OrderController"}, description = "订单相关API")
@Slf4j
@RestController
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @ResponseBody
    @ApiOperation(value = "获取订单详情", notes = "")
    @GetMapping(value = "getOrder")
    public ResponseData getOrder(@RequestParam("id") String orderId) {
        try {
            return new SuccessResponseData(orderService.selectById(orderId));
        } catch (Exception e) {
            return new ErrorResponseData("获取订单详情异常");
        }
    }

    @ResponseBody
    @ApiOperation(value = "获取该用户下所有订单", notes = " id 用户id")
    @GetMapping(value = "getOrderList")
    public ResponseData getOrderList(@RequestParam("id") String userId) {
        try {
            return new SuccessResponseData(orderService.selectByUserId(userId));
        } catch (Exception e) {
            return new ErrorResponseData("获取自取店下所有商品数据异常");
        }
    }

    @ResponseBody
    @ApiOperation(value = "添加订单", notes = "")
    @PostMapping(value = "saveOrder")
    public ResponseData saveOrder(HttpServletRequest request, @RequestParam("body") String json) {
        List<OrderItem> orderItems = JSON.parseArray(json, OrderItem.class);
        return new SuccessResponseData(orderService.save(orderItems));
    }

    @ResponseBody
    @ApiOperation(value = "修改订单状态", notes = "status =2 待提货 status =3 已提货 orderNo 订单号")
    @PostMapping(value = "updateOrderStatus")
    public ResponseData updateOrderStatus(HttpServletRequest request, @RequestBody Map<String, Object> params) {
        String orderNo = (String) params.get("orderNo");
        Integer status = (Integer) params.get("status");
        orderService.updateOrderStatus(status, orderNo);
        return new SuccessResponseData();
    }


}
package com.example.vegetables.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 订单表
 */
@Data
@TableName("mall_order")
public class MallOrder {
    @TableId(type = IdType.UUID)
    private String id;
    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 用户id
     */
    private String userId;
    /**
     * 支付状态
     * 1：待付款
     * 2：待提货
     * 3：已提货
     */
    private Integer status;
    /**
     * 创建日期
     */
    private Date createTime;
    /**
     * 付款日期
     */
    private Date payTime;
    /**
     * 提货日期
     */
    private Date pickUpTime;
    /**
     * 自提点id
     */
    private String pickUpId;
    /**
     * 订单总价
     */
    private BigDecimal totalPrice;

}

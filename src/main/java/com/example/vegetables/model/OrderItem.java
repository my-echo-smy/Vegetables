package com.example.vegetables.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.models.auth.In;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 订单详情表
 */
@Data
@TableName("user")
public class OrderItem {
    @TableId(type = IdType.UUID)
    private Integer id;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 商品id
     */
    private String productId;
    /**
     * 购买数量
     */
    private Integer number;
    /**
     * 总价
     */
    private BigDecimal totalPrice;
    /**
     * 创建日期
     */
    private Date createTime;

}

package com.example.vegetables.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品
 */
@Data
@TableName("product")
public class Product implements Serializable {
    @TableId(type = IdType.UUID)
    private String id;
    /**
     * 名称
     */
    private String name;
    /**
     * 副标题
     */
    private String title;
    /**
     * 商品描述
     */
    private String content;
    /**
     * 价格
     */
    private Double price;
    /**
     * 库存
     */
    private Integer number;
    /**
     * 分类 1：蔬菜水果
     * 2：酒水饮料
     * 3：副食产品
     */
    private Integer type;
    /**
     * 商品图片
     */
    private String picture;
    /**
     * 自提点id
     */
    private String pickUpId;
    /**
     * 1:上架
     * 2：下架
     */
    private Integer status;
    /**
     * 已售
     */
    private String soldOutNumber;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
package com.example.vegetables.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.util.Date;
/**
 * 自提店信息
 */
@Data
@TableName("pick_up")
public class PickUp {
    @TableId(type = IdType.UUID)
    private String id;
    /**
     * 社区名称
     */
    private String name;
    /**
     * 电话
     */
    private String mobile;
    /**
     * 详细地址
     */
    private String address;
    /**
     * 创建日期
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

}
